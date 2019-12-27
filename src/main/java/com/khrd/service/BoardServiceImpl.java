package com.khrd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;
import com.khrd.persistence.BoardDAO;
import com.khrd.persistence.ReplyDAO;

@Service //bean에 등록
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private ReplyDAO reDao;
	
	@Override
	@Transactional
	public void regist(BoardVO vo) {
		dao.insert(vo);	
		for(String file : vo.getFiles()) {
			dao.addAttach(file);
		}
	}

	@Override
	public List<BoardVO> listAll() {
		return dao.listAll();
	}

	@Override
	public void modify(BoardVO vo) {
		dao.update(vo);
	}

	@Override
	@Transactional
	public void remove(int bno) {
		dao.deleteAttach(bno);
		reDao.deleteByBno(bno);
		dao.delete(bno);
	}

	@Override
	public BoardVO read(int bno) {
		return dao.selectByNo(bno);
	}

	@Override
	public void addViewCnt(int bno, int viewCnt) {
		dao.updateViewCnt(bno, viewCnt);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) {
		return dao.listCriteria(cri);
	}

	@Override
	public int listCount() {
		return dao.listCount();
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) {
		return dao.listSearchCount(cri);
	}

	@Override
	public BoardVO selectBoardAndAttachByNo(int bno) {
		return dao.selectBoardAndAttachByNo(bno);
	}

	@Override
	public void modifyAttach(BoardVO vo, String[] delFiles) {
		if(delFiles != null) { //db에서 삭제
			for(String fullname : delFiles) {
				dao.deleteAttachByFilename(vo.getBno(), fullname);
			}
		}
		
		for(String file : vo.getFiles()) { //db에 추가
			dao.addAttachByBno(vo.getBno(), file);
		}
		
		dao.update(vo); //파일 외 수정
	}
}
