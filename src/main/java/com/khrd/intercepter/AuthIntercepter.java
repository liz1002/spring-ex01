package com.khrd.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthIntercepter extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String auth = (String) session.getAttribute("Auth");
		if(auth == null) { //로그인 no
			saveDest(request); //강제 이동 전에 원래 이동할 주소와 매개변수를 session에 저장
			response.sendRedirect(request.getContextPath() + "/auth/login"); //login 화면으로 강제 이동
			return false; //원래 실행해야 하는 controller 실행 막음
		}
		
		return super.preHandle(request, response, handler);
	}
	
	private void saveDest(HttpServletRequest request) { //destination
		String uri = request.getRequestURI(); //로그인 후 이동할 주소 (?전)
		String query = request.getQueryString(); //매개변수 & 값 (?뒤)
		if(query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}
		
		if(request.getMethod().equalsIgnoreCase("get")) { //post는 이 방법 불가능
			request.getSession().setAttribute("dest", uri + query);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
