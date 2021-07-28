<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/user/join" class="POST">

		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" class="form-control"  id="username" placeholder="Enter username" name="username" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		
		<div class="form-group">
			<label for="nickname">Nickname:</label> 
			<input type="text" class="form-control"  id="nickname" placeholder="Enter nickname" name="nickname" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<div class="form-group">
			<label for="email">Email:</label> 
			<input type="text" class="form-control" id="email" placeholder="Enter email" name="email" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">ȸ������</button>
</div>
<script src="/js/user.js"></script>
<br />

<%@ include file="../layout/footer.jsp"%>

