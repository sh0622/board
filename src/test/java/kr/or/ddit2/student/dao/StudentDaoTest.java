package kr.or.ddit2.student.dao;

import static org.junit.Assert.*;
import kr.or.ddit2.student.model.StudentVo;

import org.junit.Before;
import org.junit.Test;

public class StudentDaoTest {
	
	private StudentVo studentvo;
	private StudentDaoInf studentDao;
	private String std_id;
	
	@Before
	public void setup(){
		studentvo = new StudentVo();
		studentDao = new StudentDao();
		
		std_id = "song";
	}
	
	/** 
	 * Method : getStdIdtest
	 * 최초작성일 : 2018. 7. 24. 
	 * 작성자 : PC12
	 * 변경이력 :  
	 * Method 설명 : std_id에 맞는 회원정보를 반환한다.
	 */
	@Test
	public void getStdIdtest(){
		/***Given***/
		
		/***When***/
		StudentVo studentVo = studentDao.getStdId(std_id);

		/***Then***/
		assertEquals(1, studentVo.getId());
	}
	

}
