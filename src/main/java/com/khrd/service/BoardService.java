package com.khrd.service;

import java.util.List;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;

public interface BoardService {
	public void regist(BoardVO vo);
	public List<BoardVO> listAll();
	public void modify(BoardVO vo);
	public void remove(int bno);
	public BoardVO read(int bno);
	public void addViewCnt(int bno, int viewCnt);
	
	public List<BoardVO> listCriteria(Criteria cri);
	public int listCount();
	public List<BoardVO> listSearch(SearchCriteria cri);
	public int listSearchCount(SearchCriteria cri);
	
	public BoardVO selectBoardAndAttachByNo(int bno);
	public void modifyAttach(BoardVO vo, String[] delFiles);
}
