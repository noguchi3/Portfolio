package todo.web;
 
import java.io.IOException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import todo.dto.Todo;
 
/**
 * �V�K�o�^�̓��͉�ʂ�\������B
 */
@WebServlet("/todo/input")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		// vo�̍쐬
		Todo dto = new Todo();
 
		// �V�K�o�^�ł��邱�Ƃ𔻕ʂ��邽��id=0�Ƃ��Ă���B
		dto.setId(0);
 
		// �^�X�N�P����vo�����N�G�X�g�����փo�C���h
		request.setAttribute("dto", dto);
 
		// �ڍ׉�ʂ�\������
		RequestDispatcher rd = request.getRequestDispatcher("/detail.jsp");
		rd.forward(request, response);
	}
}