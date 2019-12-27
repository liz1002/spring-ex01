package com.khrd.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.khrd.domain.MemberVO;
import com.khrd.service.MemberService;

@RequestMapping("/auth/*")
@Controller
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void loginGet() {
		logger.info("~~~~~~~~~~~~~~ login GET ~~~~~~~~~~~~~~");
	}
	
	@RequestMapping(value = "loginPost", method = RequestMethod.POST)
	public void loginGet(MemberVO vo, Model model) {
		logger.info("~~~~~~~~~~~~~~ login POST ~~~~~~~~~~~~~~");
		
		MemberVO dbVo = service.selectByIdAndPwd(vo);
		if(dbVo == null) { //id & pw 불일치
			logger.info("~~~~~~~~~~~~~~ login POST NOT!! ~~~~~~~~~~~~~~");	
			return;
		}
		
		model.addAttribute("login", dbVo.getUserid()); //일치
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logoutGet(HttpSession session) {
		logger.info("~~~~~~~~~~~~~~ logout GET ~~~~~~~~~~~~~~");
		session.invalidate();
		
		return "redirect:/";
	}
}
