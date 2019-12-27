package com.khrd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.khrd.domain.BoardVO;
import com.khrd.domain.PageMaker;
import com.khrd.domain.SearchCriteria;
import com.khrd.service.BoardService;
import com.khrd.util.UploadFileUtils;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Autowired
	BoardService service;
	
	@Resource(name = "uploadPath") //bean id로 주입 받기
	private String uploadPath;
	
	@RequestMapping(value = "listPage", method = RequestMethod.GET)
	public void listPage(SearchCriteria cri, Model model) {
		logger.info("~~~~~~~~~~~~~~ listPage GET ~~~~~~~~~~~~~~");
		logger.info("cri ::: " + cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		List<BoardVO> list = service.listSearch(cri);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value = "readPage", method = RequestMethod.GET)
	public void readPage(int bno, SearchCriteria cri, int viewCnt, Model model) {
		logger.info("~~~~~~~~~~~~~~ readPage GET ~~~~~~~~~~~~~~");
		logger.info("bno ::: " + bno);
		
		service.addViewCnt(bno, viewCnt);
		BoardVO vo = service.selectBoardAndAttachByNo(bno);
		
		logger.info("vo 확인 ::: " + vo);
		model.addAttribute("boardVO", vo);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public void registGet() {
		logger.info("~~~~~~~~~~~~~~ register GET ~~~~~~~~~~~~~~");
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registPost(BoardVO vo, List<MultipartFile> imageFiles) throws IOException {
		logger.info("~~~~~~~~~~~~~~ register POST ~~~~~~~~~~~~~~");
		logger.info(vo.toString());
		
		ArrayList<String> files = new ArrayList<>();
		
		for(MultipartFile file : imageFiles) {
			if(file.isEmpty() == false) { //추가할 파일이 있으면 추가
				logger.info("*원본 파일명 ::: " + file.getOriginalFilename());
				logger.info("*파일 크기 ::: " + file.getSize());
			
				String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
				files.add(savedName); //업로드 된 파일명으로 다시 저장
			}
		}
		
		vo.setFiles(files);
		service.regist(vo);
		
		return "redirect:/sboard/listPage";
	}
	
	@RequestMapping(value = "modifyPage", method = RequestMethod.GET)
	public void modifyPageGet(int bno, SearchCriteria cri, Model model) {
		logger.info("~~~~~~~~~~~~~~ modifyPage GET ~~~~~~~~~~~~~~");
		logger.info("bno ::: " + bno);
		
		BoardVO vo = service.selectBoardAndAttachByNo(bno);
		
		model.addAttribute("boardVO", vo);
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value = "modifyPage", method = RequestMethod.POST)
	public String modifyPagePost(BoardVO vo, SearchCriteria cri, String[] delFiles, List<MultipartFile> imgFiles, Model model) throws IOException {
		logger.info("~~~~~~~~~~~~~~ modifyPage POST ~~~~~~~~~~~~~~");
		logger.info(vo.toString());
		logger.info("삭제할 파일 ::: " + delFiles);
		logger.info("추가할 파일 ::: " + imgFiles);

		if(delFiles != null) { //삭제할 파일이 있으면 삭제
			//폴더에서 삭제
			for(String delFile : delFiles) {
				UploadFileUtils.deleteFile(uploadPath, delFile);
			} 
		}
		
		ArrayList<String> files = new ArrayList<>();
		for(MultipartFile file : imgFiles) {
			if(file.isEmpty() == false) { //추가할 파일이 있으면 추가
				logger.info("*원본 파일명 ::: " + file.getOriginalFilename());
				logger.info("*파일 크기 ::: " + file.getSize());
				
				String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
				files.add(savedName); //업로드 된 파일명으로 다시 저장
			}
		}
		vo.setFiles(files);
		service.modifyAttach(vo, delFiles);
		
		model.addAttribute("bno", vo.getBno());
		model.addAttribute("page", cri.getPage());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		model.addAttribute("viewCnt", 0);
		
		return "redirect:/sboard/readPage";
	}
	
	@RequestMapping(value = "removePage", method = RequestMethod.GET)
	public String removePageGet(int bno, SearchCriteria cri, Model model) {
		logger.info("~~~~~~~~~~~~~~ removePage GET ~~~~~~~~~~~~~~");
		logger.info("bno ::: " + bno);
		
		service.remove(bno);

		model.addAttribute("page", cri.getPage());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/sboard/listPage";
	}	
}
