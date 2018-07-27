package kr.or.ddit2.student.service;

import kr.or.ddit2.student.dao.StudentDao;
import kr.or.ddit2.student.dao.StudentDaoInf;
import kr.or.ddit2.student.model.StudentVo;

public class StudentService implements StudentServiceInf {

	StudentDaoInf studentDao = new StudentDao();
	
	/** 
	 * Method : getStdId
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param id
	 * @return 
	 * Method 설명 : id에 맞는 회원정보를 반환
	 */
	@Override
	public StudentVo getStdId(String id) {
		return studentDao.getStdId(id);
	}


}
