package kr.or.ddit2.file.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String file_name = request.getParameter("file_name");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + "\""); // file 정보 저장 
		response.setContentType("application/octet-stream");
		
		// FileUtil.fileUploadPath : sally.png
		//파라미터로 파일명이 제공됨
		
		// http://localhost:8180/fileDownload?fileName=sally.png ==> 파일다운됨
		
		// D:\A_TeachingMaterial\7.JspSpring\fileUpload\sally.png 만들기
		String file = FileUtil.fileUploadPath + File.separator + file_name; //파일 경로 가져옴
		
		// file 입출력을 위한 준비
		ServletOutputStream sos = response.getOutputStream(); 
		
		File f = new File(file);
		FileInputStream fis = new FileInputStream(f); //파일 읽어오기
		//fis에 담기 위해 byte형태의 arry를 선언
		byte[] b = new byte[512];
		int len = 0;
		
		// fis.read(b) .read() 한 바이트씩 읽어서 len에 저장, 마지막 바이트 나머지를 len에 담아줌
		// -1이 아니면 정상적으로 읽을 데이터가 있고 아니면 읽어올 수 있음
		while((len = fis.read(b)) != -1 ){
			sos.write(b, 0, len);
		}
		
		sos.close();
		fis.close();
		
	}

}
