package kr.or.ddit2.post.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit2.post.model.PostVo;
import kr.or.ddit2.post.service.PostService;
import kr.or.ddit2.post.service.PostServiceInf;
import kr.or.ddit2.student.model.StudentVo;

/**
 * Servlet implementation class PostDeleteServlet
 */
@WebServlet("/postDelete")
public class PostDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 글번호, 아이디, 이미 삭제여부 체크되어잇는지
		int pt_no = Integer.parseInt(request.getParameter("pt_no"));
		request.setAttribute("pt_no", pt_no);
		
		request.getRequestDispatcher("/jsp/post/postDetail.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pt_no = Integer.parseInt(request.getParameter("pt_no"));
		
		PostServiceInf postService = new PostService();
		PostVo postVo = postService.getPostDetailList(pt_no);
		
		if(postVo.getPt_remove()==0){
			int remCnt = postService.removePost(postVo);
			
			if(remCnt>0){
				System.out.println("게시물 삭제 완료");
				
			}else{
				System.out.println("게시물 삭제 실패");
			}
		}else{
			System.out.println("이미 삭제된 게시물");
		}
		
		
		response.sendRedirect("/postList?bd_no="+postVo.getBd_no());
		
	}
}
