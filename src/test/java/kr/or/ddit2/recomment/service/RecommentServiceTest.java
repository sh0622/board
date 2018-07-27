package kr.or.ddit2.recomment.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import kr.or.ddit2.post.dao.PostDaoInf;
import kr.or.ddit2.post.model.PostVo;
import kr.or.ddit2.recomment.model.RecommentVo;

import org.junit.Before;
import org.junit.Test;

public class RecommentServiceTest {
	private RecommentServiceInf rds;
	private RecommentVo rv;
	
	@Before
	public void setup(){
		rds = new RecommentService();
		rv = new RecommentVo();
		
		rv.setId(1);
		rv.setPt_no(1);
		rv.setRe_contnet("댓글 테스트");
		rv.setRe_date(new Date());
		rv.setRe_no(45);
		rv.setRe_remove(0);
		rv.setStd_id("song");
	}
	
	/** 
	 * Method : getRecommentList
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 번호에 맞는 게시판 댓글 조회
	 */
	@Test
	public void getRecommentList(){
		/***Given***/
		int pt_no = 12;
	
		/***When***/
		List<RecommentVo> recommentList = rds.getRecommentList(pt_no);

		/***Then***/
		assertEquals(5, recommentList.size());

	}
	
	
	/** 
	 * Method : getRecommentList
	 * 최초작성일 : 2018. 7. 25.
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 댓글 사용 여부
	 */
	@Test
	public void removeRecom(){
		/***Given***/
		
		/***When***/
		int ck = rds.removeRecom(rv.getRe_no());

		/***Then***/
		assertEquals(1, ck);

	}
	
	/** 
	 * Method : getRecommentList
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 댓글 등록
	 */
	@Test
	public void insertRecomment(){
		/***Given***/
		
		/***When***/
		int ck = rds.insertRecomment(rv);

		/***Then***/
		assertEquals(ck, 1);

	}
	
}
