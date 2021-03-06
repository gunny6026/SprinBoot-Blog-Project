<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form   >
	<input type="hidden" id="id" value="${board.id}" />
  <div class="form-group">
    <label for="title">제목</label>
    <input value="${board.title}" type="text"  class="form-control" placeholder="제목 입력하세요" id="title">
  </div>
  
  <div class="form-group">
  <label for="content">내용</label>
  <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
</div>
 
</form>
 <!-- json으로 요청 할거기 때문에 버튼을 form 밖으로 빼냄 -->
  <button id="btn-update" class="btn btn-primary">글 수정</button>
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

