package com.khrd.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class); //콘솔

	@RequestMapping(value = "/", method = RequestMethod.GET) //command, get/post
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale); //콘솔에 찍기
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate ); //브라우저로 값 넘겨주기
		
		return "home"; //이동할 jsp 파일명
	}
	
}
