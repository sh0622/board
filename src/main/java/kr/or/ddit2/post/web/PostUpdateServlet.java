package kr.or.ddit2.post.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
 * Servlet implementation class PostUpdateServlet
 */
@WebServlet("/postUpdate")
@MultipartConfig(maxFileSize = 1024 * 1000 * 5, maxRequestSize = 1024 * 1000 * 16)
public class PostUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pt_no = Integer.parseInt(request.getParameter("pt_no"));
		request.setAttribute("pt_no", pt_no);
		
		PostServiceInf postService = new PostService();
		
		PostVo postVo = postService.getPostDetailList(pt_no);
		request.setAttribute("postVo", postVo);
		
		FileTableServiceInf fileService = new FileTableService();
		List<FileVo> fileList = fileService.getFileList(pt_no);
		request.setAttribute("fileList", fileList);
		
		// 게시물에 있는 파일 수를 set해서 저장해준다.
		int fileCnt= fileService.getFileCnt(pt_no);
		request.setAttribute("fileCnt", fileCnt);
		
		request.getRequestDispatcher("/SE2/postUpdate.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession sess = request.getSession();
		
		String title = request.getParameter("title");
		String content = request.getParameter("smarteditor");
		
		int pt_no = Integer.parseInt(request.getParameter("pt_no"));
		int bd_no = (int) sess.getAttribute("bd_no");
		
		PostVo postvo = new PostVo();
		postvo.setPt_title(title);
		postvo.setPt_content(content);
		postvo.setPt_no(pt_no);
		
		PostServiceInf postService = new PostService();
		int updateCnt = postService.postUpdate(postvo);
		int posttotCnt = 0;
		
		// 글 수정이 성공하면
		if(updateCnt>0){
			
			FileTableServiceInf fs = new FileTableService();
			// 파일 갯수
			//int fileCnt = fs.getFileCnt(pt_no);
			//request.setAttribute("fileCnt", fileCnt);
			
			// 파일 갯수 파라미터
			int cnt = Integer.parseInt(request.getParameter("file_cnt"));
			
			for(int i=0; i<cnt; i++){
				String param = "uploadFile"+i;
				// 0
				Part part = request.getPart(param);
				
				if(part.getSize() > 0){
					String contentDisposition = part.getHeader("Content-Disposition");
					String fileName = FileUtil.getFileName(contentDisposition);
					String path = FileUtil.fileUploadPath;
					String upload_name = UUID.randomUUID().toString();

					part.write(path + File.separator + UUID.randomUUID().toString());
					part.delete();
					posttotCnt = postService.getPostTotCnt(bd_no);
					
					FileVo fv = new FileVo();
					fv.setFile_name(fileName);
					fv.setFile_route(path);
					fv.setUpload_name(upload_name);
					fv.setPt_no(posttotCnt);
					
					int fileUpCnt = fs.insertFile(fv);
					
					if (fileUpCnt > 0) {
						System.out.println("파일 업로드 성공");
					}
				}
			}
			
		}
		
		
		response.sendRedirect("/postDetail?pt_no="+pt_no);
	}

}
