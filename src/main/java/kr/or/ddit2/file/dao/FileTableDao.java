package kr.or.ddit2.file.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit2.file.model.FileVo;
import kr.or.ddit2.mybatis.SqlMapSessionFactory;

public class FileTableDao implements FIleTableDaoInf{

	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory.getSqlSessionFactory();
	
	/** 
	 * Method : selectAllStudents
	 * 최초작성일 : 2018. 7. 22.
	 * 작성자 : PC12
	 * 변경이력 : 
	 * @return 
	 * Method 설명 : 게시물 번호에 맞는 파일리스트 반환
	 */
	@Override
	public List<FileVo> getFileList(int pt_no) {
		SqlSession sess = sqlSessionFactory.openSession();
		List<FileVo> fileList = sess.selectList("file.getFileList", pt_no);
		sess.close();
		
		return fileList;
	}

	@Override
	public int insertFile(FileVo fv) {
		SqlSession sess = sqlSessionFactory.openSession();
		int insertCk  = sess.insert("file.insertFile", fv);
		sess.commit();
		sess.close();
		
		return insertCk;
	}

	@Override
	public int getFileCnt(int pt_no) {
		SqlSession sess = sqlSessionFactory.openSession();
		int fileCnt  = sess.selectOne("file.getFileCnt", pt_no);
		sess.close();
		
		return fileCnt;
	}
	
	
}
