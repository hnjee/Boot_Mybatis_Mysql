package com.hj.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hj.s1.interceptor.CustomInterceptor;
import com.hj.s1.interceptor.AdminInterceptor;
import com.hj.s1.interceptor.LoginChkInterceptor;
import com.hj.s1.interceptor.WriterChkInterceptor;

@Component
public class InterceptorConfig implements WebMvcConfigurer{
	//SpringBoot 2.0 이후로    WebMvcConfigurerAdapter는 Deprecated
	//SpringBoot 2.0 이후에는 WebMvcConfigurer 사용
	
	@Autowired 
	private AdminInterceptor adminInterceptor;
	
	@Autowired
	private LoginChkInterceptor loginChkInterceptor;
	
	@Autowired
	private WriterChkInterceptor writerChkInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 적용할 Interceptor 등록
		registry.addInterceptor(adminInterceptor)
		
		//Interceptor에서 사용할 URL등록 
		.addPathPatterns("/notice/*")
		//Interceptor에서 제외할 URL등록 
		.excludePathPatterns("/notice/noticeList")
		.excludePathPatterns("/notice/noticeSelect");

		
		registry.addInterceptor(loginChkInterceptor)
		.addPathPatterns("/qna/*")
		.excludePathPatterns("/qna/qnaList");
		
		
		registry.addInterceptor(writerChkInterceptor)
		.addPathPatterns("/qna/qnaUpdate")
		.addPathPatterns("/qna/qnaDelete");
		
		
		//WebMvcConfigurer.super.addInterceptors(registry);
	}
}
