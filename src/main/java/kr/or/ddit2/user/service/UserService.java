package kr.or.ddit2.user.service;

import kr.or.ddit2.user.dao.UserDao;
import kr.or.ddit2.user.dao.UserDaoInf;
import kr.or.ddit2.user.model.UserVo;

public class UserService implements UserServiceInf {

	UserDaoInf userDao = new UserDao();

	@Override
	public UserVo getUser(String id) {
		return userDao.getUser(id);
	}

}
