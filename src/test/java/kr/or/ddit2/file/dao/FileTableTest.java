package kr.or.ddit2.file.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import kr.or.ddit2.file.model.FileVo;

import org.junit.Before;
import org.junit.Test;

public class FileTableTest {

	private FIleTableDaoInf fitdi;
	private FileVo fv;
	
	@Before
	public void setup() {
		fitdi = new FileTableDao();
		fv = new FileVo();
		fv.setPt_no(5);
		fv.setFile_no(36);
		fv.setFile_name("attachImage_2444970688.jpeg");
		fv.setFile_route("D:\\A_TeachingMaterial\\7.JspSpring\\fileUpload");
		fv.setUpload_name("8b12bf9b-ee90-4ce9-9f42-d65ead26c1f6");
		fv.setFile_date(new Date());
	}
	
	/** 
	 * Method : getFileListTest
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시물 파일리스트 반환
	 */
	@Test
	public void getFileListTest(){
		/***Given***/
		int pt_no = 5;
		
		/***When***/
		List<FileVo> fileList = fitdi.getFileList(pt_no);

		/***Then***/
		assertEquals(5, fileList.get(0).getPt_no());
	}
	
	
	/** 
	 * Method : insertFile
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 파일 저장
	 */
	@Test
	public void insertFile(){
		/***Given***/
		
		/***When***/
		int ck = fitdi.insertFile(fv);

		/***Then***/
		assertEquals(1, ck);
	}
	
	/** 
	 * Method : getFileCnt
	 * 최초작성일 : 2018. 7. 24. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판의 파일 수 반환
	 */
	@Test
	public void getFileCnt(){
		/***Given***/
		
		/***When***/
		int cnt = fitdi.getFileCnt(fv.getPt_no());
		
		/***Then***/
		assertEquals(3, cnt);
	}

}
