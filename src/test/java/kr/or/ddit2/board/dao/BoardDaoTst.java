package kr.or.ddit2.board.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import kr.or.ddit2.board.model.BoardVo;

import org.junit.Before;
import org.junit.Test;

public class BoardDaoTst {

	private BoardDaoInf boardDao;
	private BoardVo boardvo;
	
	@Before
	public void setup() {
		boardDao = new BoardDao();
		boardvo = new BoardVo();
		boardvo.setId(1);
		boardvo.setBd_no(1);
		boardvo.setBd_name("자유게시판");
		boardvo.setBd_date(new Date());
		boardvo.setBd_ck(0);
	}
	
	/** 
	 * Method : getMemBoardListTest
	 * 최초작성일 : 2018. 7. 24. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판 리스트 반환
	 */
	@Test
	public void getMemBoardListTest(){
		/***Given***/
		
		/***When***/
		List<BoardVo> boardList = boardDao.getMemBoardList();

		/***Then***/
		assertEquals("자유게시판", boardList.get(0).getBd_name());
	};
	
	/** 
	 * Method : insertBoard
	 * 최초작성일 : 2018. 7. 24. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판 생성
	 */
	@Test
	public void insertBoardTest(){
		/***Given***/
		
		/***When***/
		int intck = boardDao.insertBoard(boardvo);

		/***Then***/
		assertEquals(1, intck);
	}
	
	/** 
	 * Method : offBoard
	 * 최초작성일 : 2018. 7. 24. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판 활성화, 비활성화
	 */
	@Test
	public void offBoard(){
		/***Given***/
		boardvo.setBd_ck(0);
		boardvo.setBd_name("자유게시판");
		boardvo.setBd_no(47);
		
		/***When***/
		int bd_ck = boardDao.offBoard(boardvo);

		/***Then***/
		assertEquals(1, bd_ck);
	}
	
	/** 
	 * Method : getBdNoVo
	 * 최초작성일 : 2018. 7. 24. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판 번호에 맞는 리스트 반환
	 */
	@Test
	public void getBdNoVo(){
		/***Given***/
		boardvo.setBd_no(47);
		int bd_no = boardvo.getBd_no();
		
		/***When***/
		BoardVo boardvo = boardDao.getBdNoVo(bd_no);

		/***Then***/
		assertEquals("자유게시판", boardvo.getBd_name());
	}
	
	

}
