package kr.or.ddit2.recomment.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit2.recomment.model.RecommentVo;
import kr.or.ddit2.recomment.service.RecommentService;
import kr.or.ddit2.recomment.service.RecommentServiceInf;

/**
 * Servlet implementation class RecommentRemoveServlet
 */
@WebServlet("/recommentRemove")
public class RecommentRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RecommentServiceInf recommentService = new RecommentService();
		
		RecommentVo recvo = new RecommentVo();
		
		// 댓글번호, 글번호
		int rn_no = Integer.parseInt(request.getParameter("rn_no"));
		int pt_no =Integer.parseInt(request.getParameter("pt_no"));
		
		recommentService.removeRecom(rn_no);
		
		response.sendRedirect("/postDetail?pt_no="+pt_no);
		
	}

}
