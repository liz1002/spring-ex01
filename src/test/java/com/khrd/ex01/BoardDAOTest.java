package com.khrd.ex01;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.SearchCriteria;
import com.khrd.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDAOTest {
	@Autowired
	private BoardDAO dao;
	
//	@Test
	public void testInsert() {
		dao.insert(new BoardVO(0, "우하항", "내용내용내용", "user01", null, 0, 0));
	}
	
//	@Test
	public void testListAll() {
		dao.listAll();
	}
	
//	@Test
	public void testDelete() {
		dao.delete(1);
	}
	
//	@Test
	public void testUpdate() {
		dao.update(new BoardVO(2, "수정한 제목", "수정한 내용", null, null, 0, 0));
	}
	
//	@Test
	public void testSelectByNo() {
		dao.selectByNo(2);
	}
	
//	@Test
	public void testListPage() {
		dao.listPage(7, 20);
	}
	
//	@Test
	public void testListCri() {
		Criteria cri = new Criteria();
		cri.setPage(103);
		cri.setPerPageNum(10);
		dao.listCriteria(cri);
	}
	
	@Test
	public void testListSearch() {
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setPerPageNum(10);
		cri.setSearchType("t");
		cri.setKeyword("반");
		dao.listSearch(cri);
	}
}
