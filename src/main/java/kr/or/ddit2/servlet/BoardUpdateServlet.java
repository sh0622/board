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
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/boardUpdateServlet")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bd_ck = Integer.parseInt(request.getParameter("bd_ck"));
		int bd_no = Integer.parseInt(request.getParameter("bd_no"));
		
		int id = Integer.parseInt(request.getParameter("id"));
		String bd_name = request.getParameter("name");
		
		HttpSession sess = request.getSession();
		
		BoardVo boardVo = new BoardVo();
		boardVo.setBd_ck(bd_ck);
		boardVo.setBd_no(bd_no);
		boardVo.setBd_name(bd_name);
		
		BoardServiceInf boardService = new BoardService();
		int cnt = boardService.offBoard(boardVo);
		
		if(cnt>0){
			System.out.println("off 성공");
			
		}else{
			System.out.println("on 성공");
		}
		
		List<BoardVo> barodList = boardService.getMemBoardList();
		sess.setAttribute("boardList", barodList);
		
		response.sendRedirect("/boardList");
	}
}
