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
 
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = con.prepareStatement(sql);
 
		// SQL�����s���Ă��̌��ʂ��擾����B
		ResultSet rs = statement.executeQuery();
 
		// �������ʂ̍s�����t�F�b�`���s���A�擾���ʂ�DTO�֊i�[����
		while (rs.next()) {
			Todo dto = new Todo();
 
			// �N�G���[���ʂ�DTO�֊i�[(���炩���߃N�G���[���ʂ�DTO�̕ϐ����͈�v�����Ă���)
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
	 * �V�K�o�^�̉�ʂ�\������B�V�K�o�^�͋��DTO��JSP�֓n���B
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Todo insert() {
 
		Todo dto = new Todo();
		// �V�K�o�^�ł��邱�Ƃ𔻕ʂ��邽��id=0�Ƃ��Ă���B
		dto.setId(0);
 
		return dto;
	}
 
	/**
	 * �\������^�X�N�̔ԍ����w�肵�āA�^�X�N�ڍׂ�Ԃ��B
	 * 
	 * @param id
	 *            �\���Ώۂ̃^�X�NID
	 * @return
	 * @throws Exception
	 */
	public Todo detail(int id) throws Exception {
		Todo dto = new Todo();
 
		String sql = "SELECT id , title , task , limitdate , lastupdate , userid , label , td.status "
				+ "FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status where id = ?";
 
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
 
		// SQL�����s���Ă��̌��ʂ��擾����B
		ResultSet rs = statement.executeQuery();
 
		// �������ʂ̍s�����t�F�b�`���s���A�擾���ʂ�DTO�֊i�[����
		while (rs.next()) {
			// �N�G���[���ʂ�DTO�֊i�[(���炩���߃N�G���[���ʂ�DTO�̕ϐ����͈�v�����Ă���)
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
	 * �폜�������s���B�w�肳�ꂽid�̃^�X�N���폜����B
	 * 
	 * @param id
	 * @return �폜����
	 * @throws Exception
	 */
	public int delete(int id) throws Exception {
		String sql = "DELETE FROM todo_list where id = ?";
 
		// SQL�����s���Ă��̌��ʂ��擾����B
		int result = 0;
		try {
			// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, id);
 
			result = statement.executeUpdate();
 
			// �R�~�b�g���s��
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
 
		return result;
	}
 
	/**
	 * �V�K�o�^�������s���B
	 * �^�X�NID��AutoIncrement�̃L�[���ڂȂ̂ŁAINSERT����SQL�Ɋ܂߂Ȃ��Ă������I�ɍŐV��ID���o�^�����B
	 * 
	 * @param dto
	 *            ���͂��ꂽ�^�X�N���e�B
	 * @return �ǉ����ꂽ����
	 * @throws Exception
	 */
	public int registerInsert(Todo dto) throws Exception {
 
		String sql = "INSERT INTO todo_list (title,task,limitdate,lastupdate,userid,status) "
				+ "VALUES (?,?,?,now(),?,0)";
 
		int result = 0;
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, dto.getTitle());
			statement.setString(2, dto.getTask());
			statement.setString(3, dto.getInputLimitdate());
			statement.setString(4, dto.getUserid());
 
			result = statement.executeUpdate();
 
			// �R�~�b�g���s��
			con.commit();
		} catch (Exception e) {
			// ���[���o�b�N���s���A�X���[������O��DAO����E�o����
			con.rollback();
			throw e;
		}
 
		return result;
	}
 
	/**
	 * �X�V�������s���B
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int registerUpdate(Todo dto) throws Exception {
		String sql = "UPDATE todo_list SET title = ? , task = ? , limitdate = ? , lastupdate=now() , userid = ? , status = ? WHERE id = ?";
 
		// �v���y�A�X�e�[�g�����g���擾���A���sSQL��n��
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
 
			// �R�~�b�g���s��
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw e;
		}
 
		return result;
	}
 
}