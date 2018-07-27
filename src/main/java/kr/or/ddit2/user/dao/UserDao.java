package kr.or.ddit2.user.dao;

import kr.or.ddit2.user.model.UserVo;

public class UserDao implements UserDaoInf{

	/** 
	 * 
	 * Method : getUser
	 * 최초작성일 : 2018. 7. 18. 
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @param id
	 * @return 
	 * Method 설명 : id로 사용자정보 반환(가짜라 차후 바꿔야함)
	 * 
	 */
	@Override
	public UserVo getUser(String id) {
		UserVo userVo = new UserVo();
		userVo.setId("song");
		userVo.setPw("1234");
		return userVo;
	}
	
	
}
