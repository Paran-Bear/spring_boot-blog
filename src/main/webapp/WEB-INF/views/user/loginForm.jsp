<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="uname">Username:</label> <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" class="form-control" id="passwordd" placeholder="Enter password" name="password" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<button id="btn-login" class="btn btn-primary">·Î±×ÀÎ</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=c7041e2ccdc7d398c97df1eeb2a0e598&redirect_uri=http://www.parangom.xyz/auth/kakao/callback&response_type=code">
		<img height="38px" src="/image/icon/kakao_login_button.png"/>
		</a>
	</form>

</div>


<%@ include file="../layout/footer.jsp"%>

