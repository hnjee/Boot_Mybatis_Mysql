package com.hj.s1.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public int memberJoin(MemberVO memberVO) throws Exception{
		return memberRepository.memberJoin(memberVO);
	}
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		return memberRepository.memberLogin(memberVO);
	}
	public MemberVO memberIdChk(MemberVO memberVO) throws Exception{
		return memberRepository.memberIdChk(memberVO);
	}
	//검증 메서드 추가 
	public boolean memberError(MemberVO memberVO, BindingResult bindingResult) throws Exception{
		boolean res = false; //에러가 없으면 false 
		//1. 기본 제공 검증
		//res = bindingResult.hasErrors();
		if(bindingResult.hasErrors()) {
			res = true;
		}
		//2. pw 일치 검증 
		if(!memberVO.getPw().equals(memberVO.getPwChk())) {
			bindingResult.rejectValue("pwChk", "memberVO.password.notSame");
			res = true;
		}
		//3. pw 글자 수 검증
		if(memberVO.getPw().length()>0&&memberVO.getPw().length()<4) {
			bindingResult.rejectValue("pw", "memberVO.password.morethan4");
			res = true;
		} 
		if(memberVO.getPw().length()>10) {
			bindingResult.rejectValue("pw", "memberVO.password.lessthan10");
			res = true;
		} 
		//4. 중복 id 검증
		MemberVO mem = memberRepository.memberIdChk(memberVO);
		if(mem!=null) {
			bindingResult.rejectValue("id", "memberVO.id.same");
			res = true;
		}
		return res;
	}
}
