package kr.or.ddit2.board.service;

import java.util.List;

import kr.or.ddit2.board.dao.BoardDao;
import kr.or.ddit2.board.dao.BoardDaoInf;
import kr.or.ddit2.board.model.BoardVo;

public class BoardService implements BoardServiceInf {
	
	BoardDaoInf boardDao = new BoardDao();
	
	
	@Override
	public List<BoardVo> getMemBoardList() {
		return boardDao.getMemBoardList();
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		return boardDao.insertBoard(boardVo);
	}

	@Override
	public int offBoard(BoardVo boardVo) {
		return boardDao.offBoard(boardVo);
	}

	@Override
	public BoardVo getBdNoVo(int bd_no) {
		return boardDao.getBdNoVo(bd_no);
	}

}
