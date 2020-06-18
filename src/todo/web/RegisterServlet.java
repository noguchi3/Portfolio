package todo.web;
 
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import todo.dao.TodoDAO;
import todo.dto.Todo;
 
/**
 * �o�^�������s���B
 */
@WebServlet("/todo/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
 
		// ���N�G�X�g�p�����[�^���󂯎��ADTO�Ɋi�[���鏀��������B
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String task = request.getParameter("task");
		String inputLimitdate = request.getParameter("limitdate");
		String userid = request.getParameter("userid");
		int status = Integer.parseInt(request.getParameter("status"));
 
		// DTO�֊i�[����B�o�^��������(limit)��Todo�N���X�ł�inputLimit�ɂȂ�B
		Todo dto = new Todo();
		dto.setId(id);
		dto.setTitle(title);
		dto.setTask(task);
		dto.setInputLimitdate(inputLimitdate);
		dto.setUserid(userid);
		dto.setStatus(status);
 
		// ���̓`�F�b�N���s���B
		boolean checkResult = dto.valueCheck();
 
		// �������̓`�F�b�N�G���[���������ꍇ�́A�G���[���b�Z�[�W��\�����A�ē��͂����邽�ߌ��̏ڍ׉�ʂ֖߂�
		if (!checkResult) {
			request.setAttribute("errorMessages", dto.getErrorMessages());
			// �^�X�N�P����vo�����N�G�X�g�����փo�C���h
			request.setAttribute("dto", dto);
 
			// �ڍ׉�ʂ�\������
			RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
			rd.forward(request, response);
			return;
		}
 
		String message = "";
		try (TodoDAO dao = new TodoDAO()) {
			// �X�V�܂��͓o�^�������s��
			// id��0�̂Ƃ��͐V�K�o�^�Aid>=1�̂Ƃ��͍X�V
			if (id == 0) {
				dao.registerInsert(dto);
				message = "�^�X�N�̐V�K�o�^�������������܂����B";
			} else {
				dao.registerUpdate(dto);
				message = "�^�X�N[ " + id + " ]�̍X�V�������������܂����B";
			}
			setMessage(request, message);
		} catch (Exception e) {
			throw new ServletException(e);
		}
 
		// �o�^�������ꗗ��ʂ�\������
		RequestDispatcher rd = request.getRequestDispatcher("/todo/search");
		rd.forward(request, response);
	}
 
	/**
	 * JSP�ŕ\�����郁�b�Z�[�W��ݒ肷��B
	 *
	 * @param request
	 *            �T�[�u���b�g���N�G�X�g
	 * @param message
	 *            ���b�Z�[�W������
	 */
	protected void setMessage(HttpServletRequest request, String message) {
		request.setAttribute("message", message);
	}
 
}