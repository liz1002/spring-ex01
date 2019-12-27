package com.khrd.persistence;

import java.util.List;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;

public interface BoardDAO {
	public void insert(BoardVO vo);
	public List<BoardVO> listAll();
	public void delete(int bno);
	public void update(BoardVO vo);
	public BoardVO selectByNo(int bno);
	public void updateViewCnt(int bno, int viewCnt);
	
	public List<BoardVO> listPage(int page, int perPage);
	public List<BoardVO> listCriteria(Criteria cri);
	public int listCount();
	public List<BoardVO> listSearch(SearchCriteria cri);
	public int listSearchCount(SearchCriteria cri);
	public void updateReplyCount(int amount, int bno);
	
	public void addAttach(String fullname);
	public BoardVO selectBoardAndAttachByNo(int bno);
	public void deleteAttach(int bno);
	public void addAttachByBno(int bno, String fullname);
	public void deleteAttachByFilename(int bno, String fullname);
}
