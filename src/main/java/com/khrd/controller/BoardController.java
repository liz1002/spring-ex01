package com.khrd.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khrd.domain.BoardVO;
import com.khrd.domain.Criteria;
import com.khrd.domain.PageMaker;
import com.khrd.service.BoardService;

@RequestMapping("/board/*") //jsp 경로가 같으면 command에서 해당 경로 생략 가능

@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService service;

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public void registGet() {
		logger.info("~~~~~~~~~~~~~~ register GET ~~~~~~~~~~~~~~");
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registPost(BoardVO vo) {
		logger.info("~~~~~~~~~~~~~~ register POST ~~~~~~~~~~~~~~");
		logger.info(vo.toString());
		
		service.regist(vo);
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "listAll", method = RequestMethod.GET)
	public void listAll(Model model) {
		logger.info("~~~~~~~~~~~~~~ listAll GET ~~~~~~~~~~~~~~");
		
		List<BoardVO> list = service.listAll();
		
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value = "listCri", method = RequestMethod.GET)
	public void listCri(Criteria cri, Model model) {
		logger.info("~~~~~~~~~~~~~~ listCri GET ~~~~~~~~~~~~~~");
		logger.info("cri ::: " + cri);
		
		List<BoardVO> list = service.listCriteria(cri);
		
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value = "listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) {
		logger.info("~~~~~~~~~~~~~~ listPage GET ~~~~~~~~~~~~~~");
		logger.info("cri ::: " + cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount());
		
		List<BoardVO> list = service.listCriteria(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void read(int bno, int viewCnt, Model model) {
		logger.info("~~~~~~~~~~~~~~ read GET ~~~~~~~~~~~~~~");
		logger.info("bno ::: " + bno);
		
		service.addViewCnt(bno, viewCnt);
		BoardVO vo = service.read(bno);
		
		model.addAttribute("boardVO", vo);
	}
	
	@RequestMapping(value = "readPage", method = RequestMethod.GET)
	public void readPage(int bno, Criteria cri, int viewCnt, Model model) {
		logger.info("~~~~~~~~~~~~~~ readPage GET ~~~~~~~~~~~~~~");
		logger.info("bno ::: " + bno);
		
		service.addViewCnt(bno, viewCnt);
		BoardVO vo = service.read(bno);
		
		model.addAttribute("boardVO", vo);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public void modifyGet(int bno, Model model) {
		logger.info("~~~~~~~~~~~~~~ modify GET ~~~~~~~~~~~~~~");
		logger.info("bno ::: " + bno);
		
		BoardVO vo = service.read(bno);
		
		model.addAttribute("boardVO", vo);
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyPost(BoardVO vo) {
		logger.info("~~~~~~~~~~~~~~ modify POST ~~~~~~~~~~~~~~");
		logger.info(vo.toString());
		
		service.modify(vo);
		
		return "redirect:/board/read?bno=" + vo.getBno() + "&viewCnt=0";
	}
	
	@RequestMapping(value = "modifyPage", method = RequestMethod.GET)
	public void modifyPageGet(int bno, Criteria cri, Model model) {
		logger.info("~~~~~~~~~~~~~~ modifyPage GET ~~~~~~~~~~~~~~");
		logger.info("bno ::: " + bno);
		
		BoardVO vo = service.read(bno);
		
		model.addAttribute("boardVO", vo);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value = "modifyPage", method = RequestMethod.POST)
	public String modifyPagePost(BoardVO vo, Criteria cri) {
		logger.info("~~~~~~~~~~~~~~ modifyPage POST ~~~~~~~~~~~~~~");
		logger.info(vo.toString());
		
		service.modify(vo);
		
		return "redirect:/board/readPage?bno=" + vo.getBno() + "&page=" + cri.getPage() + "&viewCnt=0";
	}
	
	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public String removeGet(int bno) {
		logger.info("~~~~~~~~~~~~~~ remove GET ~~~~~~~~~~~~~~");
		logger.info("bno ::: " + bno);
		
		service.remove(bno);
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "removePage", method = RequestMethod.GET)
	public String removePageGet(int bno, Criteria cri) {
		logger.info("~~~~~~~~~~~~~~ removePage GET ~~~~~~~~~~~~~~");
		logger.info("bno ::: " + bno);
		
		service.remove(bno);
		
		return "redirect:/board/listPage?page=" + cri.getPage();
	}
}
