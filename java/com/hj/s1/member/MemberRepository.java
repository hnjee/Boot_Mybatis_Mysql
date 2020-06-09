package com.hj.s1.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository //생략가능 
@Mapper
public interface MemberRepository {
	public int memberJoin(MemberVO memberVO) throws Exception;
	public MemberVO memberLogin(MemberVO memberVO) throws Exception;
	public MemberVO memberIdChk(MemberVO memberVO) throws Exception;
}
