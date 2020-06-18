package todo.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 
public class DAO implements AutoCloseable {
 
	private Connection connection = null;
 
	public DAO() {
	}
 
	/**
	 * �f�[�^�x�[�X�Ƃ̐ڑ����擾����B �����擾���Ă����ꍇ�ɂ͊����̐ڑ��𗘗p���A �擾���Ă��Ȃ��ꍇ�͐V���ɃR���e�i����擾����B
	 *
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
 
		// NamingException, SQLException���X���[�����
		try {
			if (connection == null || connection.isClosed()) {
				InitialContext initCtx = new InitialContext();
				DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/MariaDB");
				// �f�[�^�x�[�X�ڑ����擾����
				connection = ds.getConnection();
			}
		} catch (NamingException | SQLException e) {
			// �����ڑ��擾�ŗ�O���o���ꍇ��connection=null�ɂ��A
			// ����������O�͂��̂܂ܑ��o����B
			e.printStackTrace();
			connection = null;
			throw e;
		}
 
		return connection;
	}
 
	/**
	 * �ڑ������B�m���ɐڑ���������邽��finally��connection=null���s���B
	 */
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection = null;
		}
	}
 
	/**
	 * PreparedStatement��Ԃ��B
	 *
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public PreparedStatement getPreparedStatement(String sql) throws Exception {
		return getConnection().prepareStatement(sql);
	}
 
	/**
	 * �g�����U�N�V�����̃R�~�b�g���s���B
	 *
	 * @throws SQLException
	 */
	public void commit() throws SQLException {
		connection.commit();
	}
 
	/**
	 * �g�����U�N�V�����̃��[���o�b�N���s���B
	 *
	 * @throws SQLException
	 */
	public void rollback() throws SQLException {
		connection.rollback();
	}
 
	/**
	 * �ڑ������B
	 */
	public void close() {
 
		System.out.println("close connection ------------------------------------>");
 
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection = null;
		}
	}
}