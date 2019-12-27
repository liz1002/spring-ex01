package com.khrd.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO{
	private final static String namespace="mappers.BoardMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(BoardVO vo) {
		sqlSession.insert(namespace + ".insert", vo);
	}

	@Override
	public List<BoardVO> listAll() {
		return sqlSession.selectList(namespace + ".listAll");
	}

	@Override
	public void delete(int bno) {
		sqlSession.delete(namespace + ".delete", bno);
	}

	@Override
	public void update(BoardVO vo) {
		sqlSession.update(namespace + ".update", vo);
	}

	@Override
	public BoardVO selectByNo(int bno) {
		return sqlSession.selectOne(namespace + ".selectByNo", bno);
	}

	@Override
	public void updateViewCnt(int bno, int viewCnt) {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("bno", bno);
		map.put("viewCnt", viewCnt);
		
		sqlSession.update(namespace + ".updateViewCnt", map);
	}

	@Override
	public List<BoardVO> listPage(int page, int perPage) {
		page = (page -1) * perPage;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("page", page);
		map.put("perPage", perPage);
		
		return sqlSession.selectList(namespace + ".listPage", map);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) {
		return sqlSession.selectList(namespace + ".listCri", cri);
	}

	@Override
	public int listCount() {
		return sqlSession.selectOne(namespace + ".listCount");
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) {
		return sqlSession.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return sqlSession.selectOne(namespace + ".listSearchCount", cri);
	}

	@Override
	public void updateReplyCount(int amount, int bno) {
		Map<String, Integer> map = new HashMap<>();
		map.put("amount", amount);
		map.put("bno", bno);
		sqlSession.update(namespace + ".updateReplyCount", map);
	}

	@Override
	public void addAttach(String fullname) {
		sqlSession.insert(namespace + ".addAttach", fullname);
	}

	@Override
	public BoardVO selectBoardAndAttachByNo(int bno) {
		return sqlSession.selectOne(namespace + ".selectBoardAndAttachByNo", bno);
	}

	@Override
	public void deleteAttach(int bno) {
		sqlSession.delete(namespace + ".deleteAttach", bno);
	}

	@Override
	public void deleteAttachByFilename(int bno, String fullname) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("fullname", fullname);
		sqlSession.delete(namespace + ".deleteAttachByFilename", map);
	}

	@Override
	public void addAttachByBno(int bno, String fullname) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("fullname", fullname);
		sqlSession.insert(namespace + ".addAttachByBno", map);		
	}

}
