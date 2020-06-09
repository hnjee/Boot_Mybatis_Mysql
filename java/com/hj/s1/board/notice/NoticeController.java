package com.hj.s1.board.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hj.s1.board.BoardVO;
import com.hj.s1.board.notice.noticeFile.NoticeFileVO;
import com.hj.s1.member.MemberVO;
import com.hj.s1.util.Pager;

@Controller
@RequestMapping("/notice/**/")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("noticeList")
	public ModelAndView getSelectList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = noticeService.getSelectList(pager);	
		
		mv.addObject("pager", pager);
		mv.addObject("list", ar);
		mv.setViewName("board/boardList");
		return mv; 
	}
	
	@GetMapping("noticeSelect")
	public ModelAndView getSelectOne(BoardVO boardVO) throws Exception{
		boardVO = noticeService.getSelectOne(boardVO);
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", boardVO);
		mv.setViewName("board/boardSelect");
		return mv; 
	}
	
	
	@GetMapping("noticeWrite")
	public ModelAndView setInsert(HttpServletRequest request) throws Exception{
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/boardWrite");
		mv.addObject("writer", memberVO.getId());
		mv.addObject("path", "Write");
		mv.addObject("boardVO", new BoardVO());
		return mv; 
	}
	
//	@PostMapping("noticeWrite")
//	public String setInsert(BoardVO boardVO, MultipartFile[] files, RedirectAttributes rd)throws Exception{
//		int result = noticeService.setInsert(boardVO, files);
//		rd.addFlashAttribute("result", result);
//		return "redirect:./noticeList";
//	}
	
	//SpringForm 처리
	@PostMapping("noticeWrite")
	public ModelAndView setInsert(@Valid BoardVO boardVO, BindingResult bindingResult, MultipartFile[] files,RedirectAttributes rd)  throws Exception{
		//파라미터 넣을때 검증하려는 객체 바로 뒤에 bindingResult가 들어가야함 
		ModelAndView mv = new ModelAndView();
		if(bindingResult.hasErrors()) {
			mv.setViewName("board/boardWrite");
			mv.addObject("path", "Write");
		}else {
			int res = noticeService.setInsert(boardVO, files);
			rd.addFlashAttribute("result", res);
			mv.setViewName("redirect:./noticeList");
		}
		return mv;
	}
	
	
	@GetMapping("noticeUpdate")
	public ModelAndView setUpdate(BoardVO boardVO, ModelAndView mv, HttpServletRequest request) throws Exception{
		boardVO = noticeService.getSelectOne(boardVO);
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		mv.addObject("writer", memberVO.getId());
		mv.addObject("vo", boardVO);
		mv.addObject("path", "Update");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("noticeUpdate")
	public ModelAndView setUpdate(BoardVO boardVO, RedirectAttributes rd)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = noticeService.setUpdate(boardVO);
		mv.setViewName("redirect:./noticeList");
		rd.addFlashAttribute("result", result);
		return mv;
	}
	
	@GetMapping("noticeDelete")
	public ModelAndView setDelete(BoardVO boardVO, RedirectAttributes rd)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = noticeService.setDelete(boardVO);
		mv.setViewName("redirect:./noticeList");
		rd.addFlashAttribute("result", result);
		return mv;
	}
	
	@GetMapping("fileDown")
	public ModelAndView fileDown(NoticeFileVO noticeFileVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		noticeFileVO = noticeService.fileDown(noticeFileVO);
		mv.addObject("fileVO", noticeFileVO);
		mv.setViewName("fileDown");
		return mv;
	}
	
	//예외 처리 메서드
	@ExceptionHandler(Exception.class) 
	public ModelAndView error3() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/serverError");
		return mv;
	}
//	
}
