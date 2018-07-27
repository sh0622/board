package kr.or.ddit2.post.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit2.file.model.FileVo;
import kr.or.ddit2.file.service.FileTableService;
import kr.or.ddit2.file.service.FileTableServiceTest;
import kr.or.ddit2.file.service.FileTableServiceInf;
import kr.or.ddit2.post.model.PostVo;
import kr.or.ddit2.post.service.PostService;
import kr.or.ddit2.post.service.PostServiceInf;
import kr.or.ddit2.recomment.model.RecommentVo;
import kr.or.ddit2.recomment.service.RecommentService;
import kr.or.ddit2.recomment.service.RecommentServiceInf;
import kr.or.ddit2.student.model.StudentVo;

/**
 * Servlet implementation class PostDetail
 */
@WebServlet("/postDetail")
public class PostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession sess = request.getSession();
		int pt_no = Integer.parseInt(request.getParameter("pt_no"));
		
		System.out.println(" pt_no : " + pt_no);
		
		// 게시글 정보를 조회
		PostServiceInf postService = new PostService();
		PostVo postVo = postService.getPostDetailList(pt_no);
		
		request.setAttribute("postVo", postVo);
		
		String bd_name = request.getParameter("bd_name");
		request.setAttribute("bd_name", bd_name);
		
		//게시글 아이디와 접속자 아이디가 같으면 삭제 버튼이 보이게 함
		StudentVo studnetvo = (StudentVo) sess.getAttribute("studentVo");
		
		// 현재 접속자 아이디
		int id = studnetvo.getId();
		request.setAttribute("id", id);
		
		System.out.println("  studnetvo.getId() : " + id);
		// 글쓴사람 아이디
		
		int postId = postVo.getId();
		
		int ck = 0;
		
		if(id==postId){
			ck = 1;
		}
		
		request.setAttribute("id",id);
		request.setAttribute("memCheck", ck);
		
		// 댓글 조회
		RecommentServiceInf recommentService = new RecommentService();
		List<RecommentVo> recommentList = recommentService.getRecommentList(pt_no);
		request.setAttribute("recommentList", recommentList);
		
		// 파일 조회
		FileTableServiceInf fileService = new FileTableService();
		List<FileVo> fileList = fileService.getFileList(pt_no);
		request.setAttribute("fileList", fileList);
		
		request.getRequestDispatcher("/jsp/post/postDetail.jsp").forward(request, response);
		
	}

}
