<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ include file="../layout/header.jsp" %>
<div class="container">

    <form class="form-inline">
        <div class="form-group">
            <label for="username">���̵�</label>
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <input type="button" class="btn-secondary" id="check_username" value="�ߺ�üũ"/>
            <input type="hidden" id="username_duple_check" value="unCheck"/>
        </div>
    </form>

    <form class="form-inline">
        <div class="form-group">
            <label for="nickname">�г���</label>
            <input type="text" class="form-control" id="nickname" placeholder="Enter nickname" name="nickname" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <input type="button" class="btn-secondary" id="check_nickname" value="�ߺ�üũ"/>
            <input type="hidden" id="nickname_duple_check" value="unCheck"/>
        </div>
    </form>
    <form class="form-inline">
        <div class="form-group">
            <label for="password">��й�ȣ</label> <input type="password" class="form-control" id="password"
                                                      placeholder="Enter password" name="password" required>
            <label class="small" id="password_info">������� 8�ڸ� �̻�</label>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
    </form>
    <form class="form-inline">
        <div class="form-group">
            <label >��й�ȣ Ȯ��</label> <input type="password" class="form-control" id="check_password"
                                                      placeholder="Enter password" name="password" required>

            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
    </form>
    <form class="form-inline">
        <div class="form-group">
            <label for="email">�̸���</label>
            <input type="text" class="form-control" id="email" placeholder="Enter email" name="email" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <input type="button" class="btn-secondary" id="check_email" value="�ߺ�üũ"/>
            <input type="hidden" id="email_duple_check" value="unCheck"/>
        </div>
    </form>

    <button id="btn-save" class="btn btn-secondary" >ȸ������</button>
</div>
<script src="/js/user.js"></script>
<br/>

<%@ include file="../layout/footer.jsp" %>

