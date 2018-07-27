package kr.or.ddit2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit2.board.model.BoardVo;
import kr.or.ddit2.board.service.BoardService;
import kr.or.ddit2.board.service.BoardServiceInf;
import kr.or.ddit2.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit2.student.model.StudentVo;
import kr.or.ddit2.student.service.StudentService;
import kr.or.ddit2.student.service.StudentServiceInf;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pass = response.getWriter();
		
		String std_id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String pwKisa = KISA_SHA256.encrypt(pw);
		
		StudentServiceInf studentService = new StudentService();
		StudentVo studentVo = studentService.getStdId(std_id);
		
		BoardServiceInf boardService = new BoardService();
		List<BoardVo> boardList = boardService.getMemBoardList();
		
		if(studentVo != null && studentVo.validateUser(std_id, pwKisa)){
			
			HttpSession sess = request.getSession();
			sess.setAttribute("studentVo", studentVo);
			sess.setAttribute("boardList", boardList);

			request.getRequestDispatcher("/main.jsp").forward(request, response);
			
		} else {
			
			request.getRequestDispatcher("/login/login.jsp").forward(request,response);
			
		}
		pass.close();
	}
		

}
