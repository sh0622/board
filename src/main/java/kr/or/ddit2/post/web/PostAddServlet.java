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

import kr.or.ddit2.board.model.BoardVo;
import kr.or.ddit2.board.service.BoardService;
import kr.or.ddit2.board.service.BoardServiceInf;
import kr.or.ddit2.file.model.FileVo;
import kr.or.ddit2.file.service.FileTableService;
import kr.or.ddit2.file.service.FileTableServiceTest;
import kr.or.ddit2.file.service.FileTableServiceInf;
import kr.or.ddit2.file.web.FileUtil;
import kr.or.ddit2.post.model.PostVo;
import kr.or.ddit2.post.service.PostService;
import kr.or.ddit2.post.service.PostServiceInf;
import kr.or.ddit2.student.model.StudentVo;

/**
 * Servlet implementation class PostAddServlet
 */
@WebServlet("/postAdd")
@MultipartConfig(maxFileSize = 1024 * 1000 * 5, maxRequestSize = 1024 * 1000 * 16)
public class PostAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int bd_no = Integer.parseInt(request.getParameter("bd_no"));
		request.setAttribute("bd_no", bd_no);

		String bd_name = request.getParameter("bd_name");
		request.setAttribute("bd_name", bd_name);

		request.getRequestDispatcher("/SE2/index.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession sess = request.getSession();
		StudentVo sv = (StudentVo) sess.getAttribute("studentVo");

		// System.out.println(request.getParameter("bd_name"));
		String bd_name = request.getParameter("bd_name");

		int bd_no = Integer.parseInt(request.getParameter("bd_no"));

		// 제목, 내용, 아이디, 게시물번호
		String title = request.getParameter("title");
		String smarteditor = request.getParameter("smarteditor");

		PostVo postvo = new PostVo();
		postvo.setPt_content(smarteditor);
		postvo.setPt_title(title);
		postvo.setId(sv.getId());
		postvo.setBd_no(bd_no);

		PostServiceInf postSrvice = new PostService();
		int resultCnt = postSrvice.insertPost(postvo);

		BoardServiceInf bs = new BoardService();
		BoardVo bv = bs.getBdNoVo(bd_no);

		int PosttotCnt = 0;

		// 파일저장
		if (resultCnt > 0) {
			FileTableServiceInf fs = new FileTableService();
			int cnt = Integer.parseInt(request.getParameter("file_cnt"));
			
			//List<Part> uploadFilePart = new ArrayList<Part>();

			for(int i=0; i<=cnt; i++){
				// 파일 갯수
//				uploadFilePart.add(request.getPart("uploadFile"+i)); // 전체 경로
//				System.out.println(uploadFilePart.get(i).getSize());
				String param = "uploadFile"+i;
				
				Part part = request.getPart(param);
				
				if (part.getSize() > 0) {
					String contentDisposition = part.getHeader("Content-Disposition");
					String fileName = FileUtil.getFileName(contentDisposition); // 파일명
					String path = FileUtil.fileUploadPath;
					String upload_name = UUID.randomUUID().toString();
					
					part.write(path + File.separator + fileName);
					part.delete();
					PosttotCnt = postSrvice.getPostTotCnt(bd_no);
					
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
		
		PosttotCnt = postSrvice.getPostTotCnt(bd_no);
		request.setAttribute("postVo", postvo);

		response.sendRedirect("/postDetail?pt_no=" + PosttotCnt);

	}
}
