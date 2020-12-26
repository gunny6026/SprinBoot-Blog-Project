package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	// 메인화면 갈때는 인증이 필요 없기 때문에 주석처리
	//@AuthenticationPrincipal PrincipalDetail principal
	@GetMapping("/index")
	public String index() { // 컨트롤러에서 세션을 어떻게 찾는지?
		
		//WEB-INF/views/index.jsp
	//	System.out.println("로그인 사용자 아이디 :" +principal.getUsername());
		return "index";
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveform")
	public String saveForm() {
		return "board/saveform";
	}
}
