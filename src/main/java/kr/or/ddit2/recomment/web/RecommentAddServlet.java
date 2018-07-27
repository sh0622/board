package kr.or.ddit2.recomment.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit2.recomment.model.RecommentVo;
import kr.or.ddit2.recomment.service.RecommentService;
import kr.or.ddit2.recomment.service.RecommentServiceInf;
import kr.or.ddit2.student.model.StudentVo;

/**
 * Servlet implementation class RecommentAddServlet
 */
@WebServlet("/recommentAdd")
public class RecommentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 글번호, 아이디, 댓글내용, 댓글사용유무
		request.setCharacterEncoding("utf-8");
		
		HttpSession sess = request.getSession();
		
		//StudentVo sv = (StudentVo) sess.getAttribute("studentVo");
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id :" + id);
		int pt_no = Integer.parseInt(request.getParameter("pt_no"));
		System.out.println("pt_no 댓글 :" + pt_no);
		
		String re_content = request.getParameter("recomment");
		
		RecommentVo rv = new RecommentVo();
		rv.setId(id);
		rv.setPt_no(pt_no);
		rv.setRe_contnet(re_content);
		
		RecommentServiceInf rvService = new RecommentService();
		rvService.insertRecomment(rv);
		
		response.sendRedirect("/postDetail?pt_no="+pt_no);
		
	}
}
