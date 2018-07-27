package kr.or.ddit2.recomment.service;

import java.util.List;

import kr.or.ddit2.recomment.dao.RecommentDao;
import kr.or.ddit2.recomment.dao.RecommentDaoInf;
import kr.or.ddit2.recomment.model.RecommentVo;

public class RecommentService implements RecommentServiceInf {

	RecommentDaoInf recommentDao = new RecommentDao();
	
	@Override
	public List<RecommentVo> getRecommentList(int pt_no) {
		return recommentDao.getRecommentList(pt_no);
	}

	@Override
	public String getRecommentStdId(int re_no) {
		return recommentDao.getRecommentStdId(re_no);
	}

	@Override
	public int removeRecom(int rn_no) {
		return recommentDao.removeRecom(rn_no);
	}

	@Override
	public int insertRecomment(RecommentVo rv) {
		return recommentDao.insertRecomment(rv);
	}
	
}
