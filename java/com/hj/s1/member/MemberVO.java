package com.hj.s1.member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class MemberVO {
	@NotEmpty
	private String id; 		//비어있으면 X 
	
	@NotEmpty
	//@Size(max=10, min=4)
	private String pw;  	//비어있으면 X, 4글자 이상 10글자 이하  
	private String pwChk;
	
	@NotNull
	@Range(min=0, max=200)
	private Integer age;	//0살이상 200살이하 
	
	@NotEmpty
	@Email
	private String email; 	//email형식 검증 
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getPwChk() {
		return pwChk;
	}
	public void setPwChk(String pwChk) {
		this.pwChk = pwChk;
	}
}
