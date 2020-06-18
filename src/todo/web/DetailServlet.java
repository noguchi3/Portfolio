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
 * �^�X�N�ڍ׉�ʂ�\������T�[�u���b�g�B
 */
@WebServlet("/todo/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		// ���N�G�X�g�p�����[�^����I�������^�X�Nid���擾����
		String paramId = request.getParameter("id");
 
		// String����int�֕ϊ����Adao�ŏ������s���B�X�V�Ώۂ̃^�X�N���P���擾����B
		Todo dto;
		try (TodoDAO dao = new TodoDAO()){
			// int�֕ϊ���NumberFormatException����������\������B�`�F�b�N�ΏہB
			int id = Integer.parseInt(paramId);
 
			// �^�X�N�ڍ׌��ʂ��擾
			dto = dao.detail(id);
		} catch (Exception e) {
			throw new ServletException(e);
		}
 
		// �^�X�N�P����vo�����N�G�X�g�����փo�C���h
		request.setAttribute("dto", dto);
 
		// ��ʂ�Ԃ�
		// �����ꗗ��\������
		RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
		rd.forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
	    doGet(request,response);
	}
}