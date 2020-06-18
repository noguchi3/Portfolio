/**
 *
 */
package todo.dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import todo.dto.Todo;
 
public class TodoDAO {
	private Connection con;
	public TodoDAO() throws SQLException{
		String url= "jdbc:mysql://localhost:3306/todo?serverTimezone=UTC";
		String user = "root";
		String pass = "root";
		con = DriverManager.getConnection(url, user, pass);
		System.out.println("Connection success!");
}
	
	
	public List<Todo> todoList() throws Exception {
		List<Todo> returnList = new ArrayList<Todo>();
 
		String sql = "SELECT id , title , task , limitdate , lastupdate , userid , label , td.status "
				+ "FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status";
 
		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = con.prepareStatement(sql);
		
		//　SQLを実行してその結果を取得する。
		ResultSet rs = statement.executeQuery();
 
		// 検索結果の行数分フェッチを行い、取得結果をDTOへ格納する
		while (rs.next()) {
			Todo dto = new Todo();
 
			// クエリ―結果をDTOへ格納（あらかじめクエリ―結果とDTOの変数名は一致させている）
			dto.setId(rs.getInt("id"));
			dto.setTitle(rs.getString("title"));
			dto.setTask(rs.getString("task"));
			dto.setLimitdate(rs.getTimestamp("limitdate"));
			dto.setLastupdate(rs.getTimestamp("lastupdate"));
			dto.setUserid(rs.getString("userid"));
			dto.setLabel(rs.getString("label"));
 
			returnList.add(dto);
		}
 
		return returnList;
	}
 
	/**
	 * 新規登録の画面を表示する。新規登録は空のDTOをJSPへ渡す。
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Todo insert() {
 
		Todo dto = new Todo();
		// 新規登録であることを判別するためid=0としている。
		dto.setId(0);
 
		return dto;
	}
 
	/**
	 * 表示するタスクの番号を指定して、タスク詳細を返す。
	 * 
	 * @param id
	 *            表示対象のタスクID
	 * @return
	 * @throws Exception
	 */
	public Todo detail(int id) throws Exception {
		Todo dto = new Todo();
 
		String sql = "SELECT id , title , task , limitdate , lastupdate , userid , label , td.status "
				+ "FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status where id = ?";
 
		// プリペアステートメントを取得し、実行SQLを渡す
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
 
		// SQLを実行してその結果を取得する。
		ResultSet rs = statement.executeQuery();
 
		// 検索結果の行数分フェッチを行い、取得結果をDTOへ格納する
		while (rs.next()) {
			// クエリ―結果をDTOへ格納（あらかじめクエリ―結果とDTOの変数名は一致させている）
			dto.setId(rs.getInt("id"));
			dto.setTitle(rs.getString("title"));
			dto.setTask(rs.getString("task"));
			dto.setLimitdate(rs.getTimestamp("limitdate"));
			dto.setLastupdate(rs.getTimestamp("lastupdate"));
			dto.setUserid(rs.getString("userid"));
			dto.setLabel(rs.getString("label"));
			dto.setStatus(rs.getInt("status"));
		}
		return dto;
	}
 
	/**
	 * 削除処理を行う。指定されたidのタスクを削除する。
	 * 
	 * @param id
	 * @return 削除件数
	 * @throws Exception
	 */
	public int delete(int id) throws Exception {
		String sql = "DELETE FROM todo_list where id = ?";
 
		// SQLを実行してその結果を取得する。
		int result = 0;
		try {
			// プリペアステートメントを取得し、実行SQLを渡す
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
 
			result = statement.executeUpdate();
 
			// コミットを行う
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
 
		return result;
	}
 
	/**
	 * 新規登録処理を行う。
	 * タスクIDはAutoIncrementのキー項目なので、INSERT文のSQLに含めなくても自動的に最新のIDが登録される。
	 * 
	 * @param dto
	 *            入力されたタスク内容。
	 * @return 追加された件数
	 * @throws Exception
	 */
	public int registerInsert(Todo dto) throws Exception {
 
		String sql = "INSERT INTO todo_list (title,task,limitdate,lastupdate,userid,status) "
				+ "VALUES (?,?,?,now(),?,0)";
 
		int result = 0;
		// プリペアステートメントを取得し、実行SQLを渡す
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, dto.getTitle());
			statement.setString(2, dto.getTask());
			statement.setString(3, dto.getInputLimitdate());
			statement.setString(4, dto.getUserid());
 
			result = statement.executeUpdate();
 
			// コミットを行う
			con.commit();
		} catch (Exception e) {
			// エラー詳細の表示
			e.printStackTrace();
		}
 
		return result;
	}
 
	/**
	 *　更新処理を行う。
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int registerUpdate(Todo dto) throws Exception {
		String sql = "UPDATE todo_list SET title = ? , task = ? , limitdate = ? , lastupdate=now() , userid = ? , status = ? WHERE id = ?";
 
		// プリペアステートメントを取得し、実行SQLを渡す
		int result = 0;
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, dto.getTitle());
			statement.setString(2, dto.getTask());
			statement.setString(3, dto.getInputLimitdate());
			statement.setString(4, dto.getUserid());
			statement.setInt(5, dto.getStatus());
			statement.setInt(6, dto.getId());
 
			result = statement.executeUpdate();
 
			// コミットを行う
			con.commit();
		} catch (Exception e) {
			// エラー詳細の表示
			e.printStackTrace();
		}
 
		return result;
	}
 
}