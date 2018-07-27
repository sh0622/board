package kr.or.ddit2.file.service;

import java.util.List;

import kr.or.ddit2.file.dao.FIleTableDaoInf;
import kr.or.ddit2.file.dao.FileTableDao;
import kr.or.ddit2.file.model.FileVo;

public class FileTableService implements FileTableServiceInf {

	FIleTableDaoInf fileTableDao = new FileTableDao();
	
	@Override
	public List<FileVo> getFileList(int pt_no) {
		return fileTableDao.getFileList(pt_no);
	}

	@Override
	public int insertFile(FileVo fv) {
		return fileTableDao.insertFile(fv);
	}

	@Override
	public int getFileCnt(int pt_no) {
		return fileTableDao.getFileCnt(pt_no);
	}

}
