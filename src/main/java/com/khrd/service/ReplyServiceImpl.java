package com.khrd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.khrd.domain.Criteria;
import com.khrd.domain.ReplyVO;
import com.khrd.persistence.BoardDAO;
import com.khrd.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO dao;

	@Autowired
	private BoardDAO boardDao;
	
	@Override
	@Transactional //트랜잭션 자동 처리
	public void regist(ReplyVO vo) {
		dao.create(vo);
		boardDao.updateReplyCount(1, vo.getBno());
	}

	@Override
	public List<ReplyVO> listAll(int bno) {
		return dao.list(bno);
	}

	@Override
	public void modify(ReplyVO vo) {
		dao.update(vo);
	}

	@Override
	@Transactional
	public void remove(int rno) {
		int bno = dao.getBno(rno);
		dao.delete(rno);
		boardDao.updateReplyCount(-1, bno);
	}

	@Override
	public List<ReplyVO> listCri(int bno, Criteria cri) {
		return dao.listCri(bno, cri);
	}

	@Override
	public int listCriTotalCount(int bno) {
		return dao.listCriTotalCount(bno);
	}
	
}
