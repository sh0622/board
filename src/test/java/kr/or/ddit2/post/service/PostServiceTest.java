package kr.or.ddit2.post.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit2.post.model.PostVo;

import org.junit.Before;
import org.junit.Test;

public class PostServiceTest {

	private PostServiceInf psi;
	private PostVo pv;
	
	@Before
	public void setup(){
		psi = new PostService();
		pv = new PostVo();
		
		pv.setBd_no(67);
		pv.setPt_no(15);
		pv.setId(1);
		pv.setPt_content("junit테스트");
		pv.setPt_title("junit테스트");
		pv.setPt_remove(0);
		pv.setRn(15);
		pv.setPt_seq(0);
		pv.setPt_group(15);
		pv.setPt_date(new Date());
		
	}

	/** 
	 * Method : getPostList
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 번호에 맞는 게시판 글 조회
	 */
	@Test
	public void getPostList(){
		/***Given***/
		
		/***When***/
		List<PostVo> postList = psi.getPostList(67);

		/***Then***/
		assertEquals(1, postList.get(0).getId());

	}
	
	/** 
	 * Method : getPostPageList
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판 글 페이지네이션한 리스트 반환
	 */
	@Test
	public void getPostPageList(){
		/***Given***/
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("bd_no", 67);
		map.put("page", 1);
		map.put("pageSize", 10);
		
		/***When***/
		Map<String, Object> resultMap = psi.getPostPageList(map);
		List<PostVo> resultList = (List<PostVo>) resultMap.get("postPageList");
		

		/***Then***/
		assertEquals(10, resultList.size());
	}
	

	/** 
	 * Method : insertPost
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param 
	 * @return 
	 * Method 설명 : 게시글 등록 
	 */
	@Test
	public void insertPost(){
		/***Given***/
		
		/***When***/
		int ck = psi.insertPost(pv);

		/***Then***/
		assertEquals(1, ck);
	};
	
	
	/** 
	 * Method : getPostDetailList
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시글번호에 맞는 게시글정보 반환 
	 */
	@Test
	public void getPostDetailList(){
		/***Given***/
		int pt_no = 1;
		
		/***When***/
		PostVo postvo = psi.getPostDetailList(pt_no); 
		
		/***Then***/
		assertEquals(1, postvo.getPt_no());
	}
	
	/** 
	 * Method : removePost
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시글번호에 맞는 게시글정보 반환 
	 */
	@Test
	public void removePost(){
		/***Given***/
		
		/***When***/
		int ck = psi.removePost(pv);

		/***Then***/
		assertEquals(1, ck);

	}
	
	/** 
	 * Method : removePost
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시물 수정
	 */
	@Test
	public void postUpdate(){
		/***Given***/
		pv.setPt_no(14);
		pv.setPt_title("수정된 제목");
		pv.setPt_content("수정된 내용");
		
		/***When***/
		int ck = psi.postUpdate(pv);

		/***Then***/
		assertEquals(ck, 1);

	}
	
	/** 
	 * Method : removePost
	 * 최초작성일 : 2018. 7. 23. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param 
	 * @return 
	 * Method 설명 : 답글 추가
	 */
	@Test
	public void postChild(){
		/***Given***/
		pv.setBd_no(67);
		pv.setPt_no(15);
		pv.setId(1);
		pv.setPt_content("junit테스트");
		pv.setPt_title("junit테스트");
		pv.setPt_remove(0);
		pv.setRn(15);
		pv.setPt_seq(1);
		pv.setPt_group(2);
		pv.setPt_date(new Date());
		
		/***When***/
		int ck = psi.postChild(pv);

		/***Then***/
		assertEquals(1, ck);
	}
	
	/** 
	 * Method : getPostTotCnt
	 * 최초작성일 : 2018. 7. 25. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시판 글 전체 건수 반환
	 */
	@Test
	public void getPostTotCnt(){
		/***Given***/
		
		/***When***/
		int cnt = psi.getPostTotCnt(pv.getBd_no());

		/***Then***/
		assertEquals(16, cnt);
	}
	
}
