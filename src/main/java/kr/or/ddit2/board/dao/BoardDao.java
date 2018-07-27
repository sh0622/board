package kr.or.ddit2.board.dao;

import java.util.List;

import kr.or.ddit2.board.model.BoardVo;
import kr.or.ddit2.mybatis.SqlMapSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class BoardDao implements BoardDaoInf {
	// session객체를 얻어온다
	private SqlSessionFactory sqlSessionFactory = SqlMapSessionFactory
			.getSqlSessionFactory();

	@Override
	public List<BoardVo> getMemBoardList() {
		SqlSession sess = sqlSessionFactory.openSession();
		List<BoardVo> boardList = sess.selectList("board.getMemBoardList");
		sess.close();

		return boardList;
	}
	
	@Override
	public int insertBoard(BoardVo boardVo) {
		SqlSession sess = sqlSessionFactory.openSession();
		int insertCk = sess.insert("board.insertBoard", boardVo);
		sess.commit();
		sess.close();
		
		return insertCk;
	}

	@Override
	public int offBoard(BoardVo boardVo) {
		SqlSession sess = sqlSessionFactory.openSession();
		int offCk = sess.update("board.offBoard", boardVo);
		sess.commit();
		sess.close();
		
		return offCk;
	}

	@Override
	public BoardVo getBdNoVo(int bd_no) {
		SqlSession sess = sqlSessionFactory.openSession();
		BoardVo bd_no_vo = sess.selectOne("board.getBdNoVo", bd_no);
		sess.close();
		
		return bd_no_vo;
	}
		
		
}
