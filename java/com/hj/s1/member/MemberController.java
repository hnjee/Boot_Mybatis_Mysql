package com.hj.s1.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.omg.CORBA.DynAnyPackage.TypeMismatch;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member/**/")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("memberJoin")
	public ModelAndView memberJoin(ModelAndView mv) throws Exception{
		mv.setViewName("member/memberJoin");
		mv.addObject("memberVO", new MemberVO());
		return mv;
	}
	
//	@PostMapping("memberJoin")
//	public String memberJoin(MemberVO memberVO, RedirectAttributes rd) throws Exception{
//		int res = memberService.memberJoin(memberVO);
//		if(res==1) {
//			rd.addFlashAttribute("path", "Join 성공");
//		} else {
//			rd.addFlashAttribute("path", "Join 실패");
//		}
//		return "redirect:../";
//	}
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(@Valid MemberVO memberVO, BindingResult bindingResult, RedirectAttributes rd) throws Exception {
		ModelAndView mv = new ModelAndView();
//		if(bindingResult.hasErrors()) {
//			mv.setViewName("member/memberJoin");
//		}else {
//			int res = memberService.memberJoin(memberVO);
//			rd.addFlashAttribute("result", res);
//			mv.setViewName("redirect:../");
//		}
		
		boolean errRes = memberService.memberError(memberVO, bindingResult);
		if(errRes) {
			mv.setViewName("member/memberJoin");
		}else {
			int res = memberService.memberJoin(memberVO);
			rd.addFlashAttribute("result", res);
			mv.setViewName("redirect:../");
		}
		return mv;
	}
	
	@GetMapping("memberLogin")
	public ModelAndView memberLogin(ModelAndView mv) throws Exception{
		mv.setViewName("member/memberLogin");
		return mv;
	}
	
	@PostMapping("memberLogin")
	public String memberLogin(MemberVO memberVO, RedirectAttributes rd, HttpSession session) throws Exception{
		memberVO = memberService.memberLogin(memberVO);
		//로그인 검증
		if(memberVO!=null) {
			//로그인 성공
			session.setAttribute("member", memberVO);
			return "redirect:../";
			
		}else { 
			//로그인 실패 
			rd.addFlashAttribute("path", "Login 실패");
			return "redirect:../";
		}
	}
	
	@GetMapping("memberLogout")
	public String memberLogout(RedirectAttributes rd, HttpSession session) throws Exception{
		session.invalidate();
		rd.addFlashAttribute("path", "Logout 완료");
		return "redirect:../";
	}
	
	@GetMapping("memberPage")
	public ModelAndView memberPage(ModelAndView mv) throws Exception{
		mv.setViewName("member/memberPage");
		return mv;
	}
	
}
