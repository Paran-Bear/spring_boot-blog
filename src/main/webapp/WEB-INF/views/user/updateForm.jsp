<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="../layout/header.jsp"%>

<sec:authorize access="isAuthenticated">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<div class="container">
	<form action="/user/join" class="POST">
		
		<c:if test="${empty principal.user.oauth }">
		<input type="hidden" id="id" value="${principal.user.id }" />
			<div class="form-group">
				<label for="username">ID</label> <input type="text" class="form-control" value="${principal.user.username }" id="username" placeholder="Enter username" name="username" readonly>

			</div>

			<div class="form-group">
				<label for="nickname">Nickname:</label> <input type="text" value="${principal.user.nickname }" class="form-control" id="nickname" placeholder="Enter nickname" name="nickname" required>

			</div>

			<div class="form-group">
				<label for="password">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">

			</div>

			<div class="form-group">
				<label for="email">Email:</label> <input type="text" value="${principal.user.email }" class="form-control" id="email" placeholder="Enter email" name="email" required>
				<div class="valid-feedback">Valid.</div>
				<div class="invalid-feedback">Please fill out this field.</div>
			</div>
		</c:if>
		<c:if test="${not empty principal.user.oauth}">
			<div class="form-group">
				<label for="nickname">Nickname:</label> <input type="text" value="${principal.user.nickname }" class="form-control" id="nickname" placeholder="Enter nickname" name="nickname" required>

			</div>
			<input type="hidden" id="id" value="${principal.user.id }" />
			<input type="hidden" id="username" name="username" value="${principal.user.username }" />
			<input type="hidden" id="email" value="${principal.user.email}" name="email">
			<input type="hidden" id="password" name="password" value="">

		</c:if>


	</form>
	<button id="btn-update" class="btn btn-primary">정보 수정</button>
</div>
<script src="/js/user.js"></script>
<br />

<%@ include file="../layout/footer.jsp"%>

