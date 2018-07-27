package kr.or.ddit2.servlet;

import java.io.IOException;
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
import kr.or.ddit2.student.model.StudentVo;

/**
 * Servlet implementation class BoardMakeServlet
 */
@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		
		request.setCharacterEncoding("utf-8");
		
		List<BoardVo> boardList = (List<BoardVo>) sess.getAttribute("boardList");
		sess.setAttribute("boardList", boardList);
		
		request.getRequestDispatcher("/jsp/board/boardList.jsp").forward(request, response);
	}

	
}
