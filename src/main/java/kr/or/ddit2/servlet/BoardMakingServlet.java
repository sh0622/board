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

/**
 * Servlet implementation class BoardMakingServlet
 */
@WebServlet("/boardMakingServlet")
public class BoardMakingServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession sess = request.getSession();
		
		String bd_name = request.getParameter("bd_name");
		int id = Integer.parseInt(request.getParameter("id"));
		
		BoardVo boardVo = new BoardVo();
		boardVo.setBd_name(bd_name);
		boardVo.setId(id);
		
		BoardServiceInf boardService = new BoardService();
		boardService.insertBoard(boardVo);
		
		List<BoardVo> barodList = boardService.getMemBoardList();
		sess.setAttribute("boardList", barodList);
		
		request.getRequestDispatcher("/jsp/board/boardList.jsp").forward(request, response);
	}

}
