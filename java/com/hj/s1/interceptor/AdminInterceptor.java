package com.hj.s1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hj.s1.member.MemberVO;

@Component
public class AdminInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// return이 true 면 Controller 전송 
		// return이 false면 Controller 진입을 막음 - redirect로 전송
		
		boolean check = false;
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		if(memberVO!=null && memberVO.getId().equals("admin")) {
			check =true;
		} else {
			//response.sendRedirect("../message/messageResult?result=onlyAdmin&path=../notice/noticeList");
			request.setAttribute("path", "../notice/noticeList");
			request.setAttribute("result", "관리자가 아닙니다.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			view.forward(request, response);
		}
		return check;
	}
}
