package kr.or.ddit2.post.web;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit2.file.model.FileVo;
import kr.or.ddit2.file.service.FileTableService;
import kr.or.ddit2.file.service.FileTableServiceTest;
import kr.or.ddit2.file.service.FileTableServiceInf;
import kr.or.ddit2.file.web.FileUtil;
import kr.or.ddit2.post.model.PostVo;
import kr.or.ddit2.post.service.PostService;
import kr.or.ddit2.post.service.PostServiceInf;

/**
 * Servlet implementation class PostChildServlet
 */
@WebServlet("/postChild")
@MultipartConfig(maxFileSize = 1024 * 1000 * 5, maxRequestSize = 1024 * 1000 * 16)
public class PostChildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pt_no = Integer.parseInt(request.getParameter("pt_no"));
		request.setAttribute("pt_no", pt_no);
		
		int bd_no = Integer.parseInt(request.getParameter("bd_no"));
		request.setAttribute("bd_no", bd_no);
		
		int pt_group = Integer.parseInt(request.getParameter("pt_group"));
		request.setAttribute("pt_group", pt_group);
		
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("/SE2/postChild.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// bd_no, id, pt_seq, pt_group, pt_content, pt_title
		int id = Integer.parseInt(request.getParameter("id"));
		int pt_no = Integer.parseInt(request.getParameter("pt_no"));
		int bd_no = Integer.parseInt(request.getParameter("bd_no"));
		int pt_group = Integer.parseInt(request.getParameter("pt_group"));
		String title = request.getParameter("title");
		String content = request.getParameter("smarteditor");
		
		PostVo pv = new PostVo();
		
		pv.setBd_no(bd_no);
		pv.setId(id);
		pv.setPt_seq(pt_no);
		pv.setPt_group(pt_group+1);
		pv.setPt_title(title);
		pv.setPt_content(content);
		
		PostServiceInf postService = new PostService();
		int resultCnt = postService.postChild(pv);
		
		int PosttotCnt = 0;

		// 파일저장
		if (resultCnt > 0) {
			FileTableServiceInf fs = new FileTableService();
			int cnt = Integer.parseInt(request.getParameter("file_cnt"));
			
			for(int i=0; i<=cnt; i++){
				String param = "uploadFile"+i;
				
				Part part = request.getPart(param);
				
				if (part.getSize() > 0) {
					String contentDisposition = part.getHeader("Content-Disposition");
					String fileName = FileUtil.getFileName(contentDisposition); // 파일명
					String path = FileUtil.fileUploadPath;
					String upload_name = UUID.randomUUID().toString();
					
					part.write(path + File.separator + UUID.randomUUID().toString());
					part.delete();
					PosttotCnt = postService.getPostTotCnt(bd_no);
					
					FileVo fv = new FileVo();
					fv.setFile_name(fileName);
					fv.setFile_route(path);
					fv.setUpload_name(upload_name);
					fv.setPt_no(PosttotCnt);
					
					int fileUpCnt = fs.insertFile(fv);
					
					if (fileUpCnt > 0) {
						System.out.println("파일 업로드 성공");
					}
				}
			}
		}
		
		int totCnt = postService.getPostTotCnt(bd_no); 
		
		response.sendRedirect("/postDetail?pt_no="+totCnt);
	}
}
