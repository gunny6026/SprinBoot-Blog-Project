package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board ,@AuthenticationPrincipal PrincipalDetail principal) {
		
		System.out.println("BoardApiController 호출 post => service ㄱ");
		System.out.println("board 값:  "+board);
		System.out.println("user값인 principal : "+principal+"ㄴㄴ"+principal.getUsername());
		
		boardService.글쓰기(board, principal.getUser());
	
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	//댓글 삭제하기
	@DeleteMapping("/api/reply/delete/{id}")
	public ResponseDto<Integer> deleteReply(@PathVariable int id){
		
		System.out.println("댓글 삭제하기 로직 id는 댓글 번호?? : "+id);
		boardService.댓글삭제하기(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@PutMapping("api/board/{id}")
	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
		
		boardService.글수정하기(id,board);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
		
	}
	
	//데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다.
	//dto를 사용하지 않은 이유는 ~ 규모가 작기 때문에 굳이..?
	@PostMapping("api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto requestDto){
		
		System.out.println("댓글 버튼 누름-> 게시글 번호 : "+requestDto.getBoardId() );
		System.out.println("reply 객체 :" +requestDto.getContent() );
		System.out.println("user 객체인 principal : "+ requestDto.getUserId());
		
		boardService.댓글쓰기(requestDto);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	
}
