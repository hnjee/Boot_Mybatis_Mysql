package com.hj.s1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hj.s1.member.MemberVO;
import com.hj.s1.util.FilePathGenerator;

@Controller
public class HomeController {
	@Autowired
	private FilePathGenerator pathGenerator;
	
	@GetMapping("/message/messageResult")
	public ModelAndView message(String path, String result) throws Exception{
		System.out.println(path);
		System.out.println(result);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", result);
		mv.addObject("path", path);
		mv.setViewName("common/result");
		return mv;
	}
	
	@GetMapping("/")
	public String home(Model model, HttpServletRequest request)throws Exception{
		if(request.getSession().getAttribute("member")!=null){
			MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
			System.out.println("id!@!!!!: "+memberVO.getId());
			String welcome = memberVO.getId()+"님 환영합니다! ";
			model.addAttribute("name", welcome);
		} else {
			model.addAttribute("name", "안녕하세요? 로그인 고고");
		}
		
		return "index";
	}
}