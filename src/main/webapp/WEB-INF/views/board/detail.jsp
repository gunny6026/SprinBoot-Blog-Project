<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	
  <div class="form-group">
  <div>
  	글 번호 : <span id="id"><i>${board.id}</i></span>
  	작성자 : <span ><i>${board.user.username}</i></span>
  </div>
  <a class="btn btn-secondary" href="/">돌아가기</a>
  <c:if test="${board.user.id == principal.user.id}">
  <a href="/board/${board.id}/updateform" class="btn btn-primary">수정</a>
  <button id="btn-delete" class="btn btn-danger">삭제</button>
  </c:if>
  <br/> <br/>
    <h3>${board.title }</h3>
  </div>
  <hr>
  <div class="form-group">
  
	<div>
		${board.content }
	</div>
</div>
	<div class="card">
	<form>
		<input type="hidden" id="userId" value="${principal.user.id}" />
		<input type="hidden" id="boardId" value="${board.id}" />
			<div class="card-body">
			<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
			<button id="btn-reply-save" class="btn btn-primary">등록</button>
			</div>
		
		</form>
	</div>
 <br/>
 <div class="card">
 	
 	<div class="card-header">댓글 리스트</div>
 	<ul id="reply--box" class="list-group">
 		<c:forEach var="reply" items="${board.reply }">
 		<li id="reply--1" class="list-group-item d-flex justify-content-between">
 			<div>${reply.content}</div>
 			<div class="d-flex">
 				<div class ="font-italic">작성자 : ${reply.user.username} &nbsp: </div>
 				<input type="hidden" id="replyId" value="${reply.id }"/>
 				<c:choose>
 				<c:when test="${reply.user.id == principal.user.id}">
 				<button  id="btn-reply-delete" onclick="return confirm('정말로 삭제 하시겠습니까?')" 
 				class="badge">삭제</button>
 				</c:when>
 				<c:otherwise>
 				<button  onclick="alert('권한이 없습니다.')"  class="badge">삭제</button>
 				</c:otherwise>
 				</c:choose>
 				<!-- 댓글 같은 경우 로그인했을 때 회원과 작성자의 이름이 같을 때만 
 				삭제가 가능하다 -->
 			</div>
 		</li>
 		</c:forEach>
 		
 	</ul>
 </div>
</div>
<br>
<script>
      $('.summernote').summernote({
        tabsize: 2,
        height: 300
      });
    </script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>

