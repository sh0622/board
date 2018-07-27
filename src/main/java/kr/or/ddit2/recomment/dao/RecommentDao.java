package kr.or.ddit2.recomment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit2.mybatis.SqlMapSessionFactory;
import kr.or.ddit2.recomment.model.RecommentVo;

public class RecommentDao implements RecommentDaoInf {
	
	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	
	@Override
	public List<RecommentVo> getRecommentList(int pt_no) {
		SqlSession sess = sqlSessionFactory.openSession();
		List<RecommentVo> recommentList = sess.selectList("recomment.getRecommentList", pt_no);
		sess.close();
		
		return recommentList;
	}

	@Override
	public String getRecommentStdId(int re_no) {
		SqlSession sess = sqlSessionFactory.openSession();
		String rv = sess.selectOne("recomment.getRecommentStdId", re_no);
		sess.close();
		
		return rv;
	}

	@Override
	public int removeRecom(int rn_no) {
		SqlSession sess = sqlSessionFactory.openSession();
		int recCnt= sess.update("recomment.removeRecomment", rn_no);
		sess.commit();
		sess.close();
		
		return recCnt;
	}

	@Override
	public int insertRecomment(RecommentVo rv) {
		SqlSession sess = sqlSessionFactory.openSession();
		int insertCnt= sess.insert("recomment.insertRecomment", rv);
		sess.commit();
		sess.close();
		
		return insertCnt;
	}
	
	

}
