<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ include file="../layout/header.jsp" %>
<div class="container">

    <form class="form-inline">
        <div class="form-group">
            <label for="username">아이디</label>
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <input type="button" class="btn-secondary" id="check_username" value="중복체크"/>
            <input type="hidden" id="username_duple_check" value="unCheck"/>
        </div>
    </form>

    <form class="form-inline">
        <div class="form-group">
            <label for="nickname">닉네임</label>
            <input type="text" class="form-control" id="nickname" placeholder="Enter nickname" name="nickname" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <input type="button" class="btn-secondary" id="check_nickname" value="중복체크"/>
            <input type="hidden" id="nickname_duple_check" value="unCheck"/>
        </div>
    </form>
    <form class="form-inline">
        <div class="form-group">
            <label for="password">비밀번호</label> <input type="password" class="form-control" id="password"
                                                      placeholder="Enter password" name="password" required>
            <label class="small" id="password_info">공백없이 8자리 이상</label>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
    </form>
    <form class="form-inline">
        <div class="form-group">
            <label >비밀번호 확인</label> <input type="password" class="form-control" id="check_password"
                                                      placeholder="Enter password" name="password" required>

            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
    </form>
    <form class="form-inline">
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <input type="button" class="btn-secondary" id="check_email" value="중복체크"/>
            <input type="hidden" id="email_duple_check" value="unCheck"/>
        </div>
    </form>

    <button id="btn-save" class="btn btn-secondary" >회원가입</button>
</div>
<script src="/js/user.js"></script>
<br/>

<%@ include file="../layout/footer.jsp" %>

