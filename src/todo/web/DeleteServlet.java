package todo.web;
 
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import todo.dao.TodoDAO;
 
/**
 * �폜�������s���B
 */
@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ���N�G�X�g�p�����[�^����I�������^�X�Nid���擾����
		String paramId = request.getParameter("id");
 
		try(TodoDAO dao = new TodoDAO()) {
			// int�֕ϊ���NumberFormatException����������\������B�`�F�b�N�ΏہB
			int id = Integer.parseInt(paramId);
 
			// String����int�֕ϊ����Adao�ŏ������s���B�Ώۂ̃^�X�N���P���폜���A��������ƂP���Ԃ����B
			int result = dao.delete(id);
		} catch (Exception e) {
			throw new ServletException(e);
		}
 
		setMessage(request, "�^�X�N[ " + paramId + " ]�̍폜�������������܂����B");
 
		// ��ʂ�Ԃ�
		// ������ʂ�\������
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