package kr.or.ddit2.post.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit2.board.model.BoardVo;
import kr.or.ddit2.board.service.BoardService;
import kr.or.ddit2.board.service.BoardServiceInf;
import kr.or.ddit2.post.model.PostVo;
import kr.or.ddit2.post.service.PostService;
import kr.or.ddit2.post.service.PostServiceInf;
import kr.or.ddit2.student.model.StudentVo;

/**
 * Servlet implementation class PostListServlet
 */
@WebServlet("/postList")
public class PostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int bd_no = Integer.parseInt(request.getParameter("bd_no"));
		
		HttpSession sess = request.getSession();
		sess.setAttribute("bd_no", bd_no);
		
		BoardServiceInf boardService = new BoardService();
		BoardVo boardvo = boardService.getBdNoVo(bd_no);
		request.setAttribute("boardvo", boardvo);
		
		PostServiceInf postService = new PostService();
		
		//int id = Integer.parseInt(request.getParameter("id"));
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize");		 
		
		int page = pageStr == null ? 1 :Integer.parseInt(pageStr);
		int pageSize = pageSizeStr == null ? 10 : Integer.parseInt(pageSizeStr);
		
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("page", page);
		paramMap.put("pageSize", pageSize);
		paramMap.put("bd_no", bd_no);
		
		//System.out.println(std_id);
		Map<String, Object> resultMap = postService.getPostPageList(paramMap);
		
		//System.out.println(resultMap.get("postPageList"));
		
		List<PostVo> postPageList = (List<PostVo>) resultMap.get("postPageList");
		request.setAttribute("postPageList", postPageList);
		
		// 페이지 네비게이션 문자열
		String pageNavi = (String) resultMap.get("pageNavi");
		request.setAttribute("pageNavi", pageNavi);

		// request에 저장된 값을 다음 페이지에도 볼 수 있도록 주소에 forward해줌
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/post/postList.jsp");
		rd.forward(request, response);
		
	}
}
