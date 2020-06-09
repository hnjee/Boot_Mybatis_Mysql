package com.hj.s1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hj.s1.util.Pager;

@Controller
public class TestController {
	
	@GetMapping("/test/select/{num}")
	public void test(@PathVariable(value = "num") int num) {
		System.out.println("num: "+num);
	}
	
	@GetMapping("/test/list/{curPage}/{kind}/{search}")
	public void test2(Pager pager) {
		System.out.println("curPage : "+pager.getCurPage());
		System.out.println("kind : "+pager.getKind());
		System.out.println("search : "+pager.getSearch());
	}
	
	@GetMapping("/test/select/{num}/{name}")
	public void test3(@PathVariable String num, @PathVariable String name) {
		System.out.println("num: "+num);
		System.out.println("name: "+name);
	}
}
