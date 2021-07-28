<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="../layout/header.jsp"%>

<sec:authorize access="isAuthenticated">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<div class="container">
	<form action="/user/join" class="POST">

		<input type="hidden" id="id" value="${principal.user.id }" />
		<input type="hidden" id="username" name="username" value="${principal.user.username }" />
		<input type="hidden" value="${principal.user.email}" id="email" name="email">



		<div class="form-group">
			<label for="username">Nickname:</label> <input type="text" value="${principal.user.nickname }" class="form-control" id="nickname" placeholder="Enter username" name="nickname">
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		

			
				
		
	</form>
	<button id="btn-update" class="btn btn-primary">가입 완료</button>
</div>
<script src="/js/user.js"></script>
<br />

<%@ include file="../layout/footer.jsp"%>

