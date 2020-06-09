package com.hj.s1.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hj.s1.board.BoardVO;
import com.hj.s1.board.qna.QnaRepository;
import com.hj.s1.board.qna.QnaService;
import com.hj.s1.board.qna.QnaVO;
import com.hj.s1.member.MemberVO;


@Component
public class WriterChkInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private QnaRepository qnaRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//update, delete : 작성자만 접근 가능  
		
		boolean check = false;
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		int num =  Integer.parseInt(request.getParameter("num"));
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(num);
		boardVO = qnaRepository.getSelectOne(boardVO);

		
		if(memberVO!=null && boardVO.getWriter().equals(memberVO.getId())) {
			System.out.println("******작성자 맞음*****");
			check = true;
		} else {
			System.out.println("******작성자 아님*****");
			
			//response.sendRedirect("../message/messageResult?result=onlyWriterAvailable&path=../qna/qnaList");
			request.setAttribute("path", "../qna/qnaList");
			request.setAttribute("result", "작성자가 아닙니다.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			view.forward(request, response);
			
		}
		return check;
	}
	
}
