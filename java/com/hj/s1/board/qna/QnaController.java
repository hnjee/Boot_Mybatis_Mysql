package com.hj.s1.board.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.Module.SetupContext;
import com.hj.s1.board.BoardVO;
import com.hj.s1.member.MemberVO;
import com.hj.s1.util.Pager;

@Controller
@RequestMapping("/qna/**/")
public class QnaController {
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	@GetMapping("qnaList")
	public ModelAndView getSelectList(Pager pager) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardVO> ar = qnaService.getSelectList(pager);
		
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	@GetMapping("qnaSelect")
	public ModelAndView getSelectOne(BoardVO boardVO) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = qnaService.getSelectOne(boardVO);
		
		mv.addObject("vo", boardVO);
		mv.setViewName("board/boardSelect");
		return mv;
	}
	
	@GetMapping("qnaWrite")
	public ModelAndView setInsert(ModelAndView mv, HttpServletRequest request) throws Exception{
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		mv.addObject("writer", memberVO.getId());
		mv.addObject("path", "Write");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaWrite")
	public String setInsert(QnaVO qnaVO, MultipartFile[] files, RedirectAttributes rd) throws Exception{
		int res = qnaService.setInsert(qnaVO, files);
		rd.addFlashAttribute("path", "Write");
		rd.addFlashAttribute("result", res);
		return "redirect:./qnaList";
	}
	
	@GetMapping("qnaDelete")
	public String setDelete(BoardVO boardVO, RedirectAttributes rd) throws Exception{
		int res = qnaService.setDelete(boardVO);
		rd.addFlashAttribute("path", "Delete");
		rd.addFlashAttribute("result", res);
		return "redirect:./qnaList";
	}
	
	@GetMapping("qnaUpdate")
	public ModelAndView setUpdate(BoardVO boardVO, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		boardVO = qnaService.getSelectOne(boardVO);
		
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		mv.addObject("writer", memberVO.getId());
		mv.addObject("vo", boardVO);
		mv.addObject("path", "Update");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaUpdate")
	public String setUpdate(BoardVO boardVO, RedirectAttributes rd) throws Exception{
		int res = qnaService.setUpdate(boardVO);
		rd.addFlashAttribute("path", "Update");
		rd.addFlashAttribute("result", res);
		return "redirect:./qnaList";
	}
	
	//답글 쓰기
	@GetMapping("qnaReply")
	public ModelAndView setReply(BoardVO boardVO, ModelAndView mv, HttpServletRequest request) throws Exception{
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
		mv.addObject("writer", memberVO.getId());
		mv.addObject("vo", boardVO); //부모 번호 넘겨줌 
		mv.addObject("path", "Reply");
		mv.setViewName("board/boardWrite");
		return mv;
	}
	
	@PostMapping("qnaReply")
	public String setReply(BoardVO boardVO, MultipartFile[] files, RedirectAttributes rd) throws Exception{
		int res = qnaService.setReply(boardVO, files);
		rd.addFlashAttribute("path", "Reply");
		rd.addFlashAttribute("result", res);
		return "redirect:./qnaList";
	}
}
