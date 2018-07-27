package kr.or.ddit2.post.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit2.mybatis.SqlMapSessionFactory;
import kr.or.ddit2.post.model.PostVo;
import kr.or.ddit2.student.model.StudentVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PostDao implements PostDaoInf {
	// session객체를 얻어온다
	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();

	/**
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 18.
	 * 작성자 : PC12
	 * 변경이력 :
	 * 
	 * @return Method 설명 : 번호에 맞는 게시판 글 조회
	 */
	@Override
	public List<PostVo> getPostList(int bd_no) {
		SqlSession sess = sqlSessionFactory.openSession();
		List<PostVo> boardList = sess.selectList("post.getPostList", bd_no);
		sess.close();

		return boardList;
	}

	/**
	 * Method : getPostPageList
	 * 최초작성일 : 2018. 7. 18.
	 * 작성자 : PC12
	 * 변경이력 :
	 * 
	 * @param map
	 * @return Method 설명 : 게시판 글 페이지네이션한 리스트 반환
	 */
	@Override
	public List<PostVo> getPostPageList(Map<String, Integer> map) {
		SqlSession sess = sqlSessionFactory.openSession();
		List<PostVo> postList = sess.selectList("post.getPostPageList", map);
		sess.close();

		return postList;
	}

	/**
	 * Method : getPostTotCnt
	 * 최초작성일 : 2018. 7. 18.
	 * 작성자 : PC12
	 * 변경이력 :
	 * 
	 * @return Method 설명 : 게시판 글 전체 건수 반환
	 */
	@Override
	public int getPostTotCnt(int bd_no) {
		SqlSession sess = sqlSessionFactory.openSession();
		int totCnt = sess.selectOne("post.getPostTotCnt", bd_no);
		sess.close();

		return totCnt;
	}
	
	/** 
	 * Method : getPostDetailList
	 * 최초작성일 : 2018. 7. 20. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param pt_no
	 * @return 
	 * Method 설명 : 게시글번호에 맞는 게시글정보 반환 
	 */
	public PostVo getPostDetailList(int pt_no){
		SqlSession sess = sqlSessionFactory.openSession();
		PostVo postvo = sess.selectOne("post.getPostDetailList", pt_no);
		sess.close();

		return postvo;
	}

	@Override
	public int insertPost(PostVo postVo) {
		SqlSession sess = sqlSessionFactory.openSession();
		int intsertCnt = sess.insert("post.insertPost", postVo);
		sess.commit();
		sess.close();
		
		return intsertCnt;
	}

	@Override
	public int removePost(PostVo postVo) {
		SqlSession sess = sqlSessionFactory.openSession();
		int removeCnt = sess.update("post.removePost", postVo);
		sess.commit();
		sess.close();
		
		return removeCnt;
	}

	@Override
	public int postUpdate(PostVo postVo) {
		SqlSession sess = sqlSessionFactory.openSession();
		int updateCnt = sess.update("post.postUpdate", postVo);
		sess.commit();
		sess.close();
		
		return updateCnt;
	}

	@Override
	public int postChild(PostVo postVo) {
		SqlSession sess = sqlSessionFactory.openSession();
		int insertChildCnt = sess.insert("post.postChild", postVo);
		sess.commit();
		sess.close();
		
		return insertChildCnt;
	}
	

}
