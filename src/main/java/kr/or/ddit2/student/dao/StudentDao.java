package kr.or.ddit2.student.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit2.mybatis.SqlMapSessionFactory;
import kr.or.ddit2.student.model.StudentVo;

public class StudentDao implements StudentDaoInf {

	//session객체를 얻어온다
	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	
	/** 
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 학생 아이디에 맞는 학생의 정보 반환
	 */
	@Override
	public StudentVo getStdId(String id) {
		SqlSession session = sqlSessionFactory.openSession();
		StudentVo student = session.selectOne("student.getStdId", id);
		session.close();
		
		return student;
	}

}
