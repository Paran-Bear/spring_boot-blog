<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ include file="../layout/header.jsp" %>
<div class="container p-5 mx-auto" style="margin-top: 20%;margin-bottom:25%;width: fit-content">
    <table >
        <tbody>
        <tr>
            <th>
                <div class="p-2">아이디</div>
            </th>
            <th><input type="text" class="p-2 rounded" id="username" placeholder="Enter username" name="username"
                       required></th>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <th><input type="button" class="p-2 btn-secondary" id="check_username" value="중복체크"/></th>
            <input type="hidden" id="username_duple_check" value="unCheck"/>
        </tr>


        <tr>
            <th>
                <div class="flex-fill p-2">닉네임</div>
            </th>
            <th><input type="text" class="p-2 rounded" id="nickname" placeholder="Enter nickname" name="nickname"
                       required></th>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <th><input type="button" class="p-2 btn-secondary" id="check_nickname" value="중복체크"/></th>
            <input type="hidden" id="nickname_duple_check" value="unCheck"/>
        </tr>


        <tr>
            <th>
                <div class="flex-fill p-2">비밀번호</div>
            </th>
            <th><input type="password" class="rounded p-2" id="password"
                       placeholder="Enter password" name="password" required></th>
            <th>
                <div class="flex-fill small" id="password_info">공백없이 8자리 이상</div>
            </th>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </tr>


        <tr>
            <th>
                <div class="flex-fill p-2">비밀번호 확인</div>
            </th>
            <th><input type="password" class="flex-fill rounded sm p-2" id="check_password"
                       placeholder="Enter password" name="password" required></th>

            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </tr>


        <tr>
            <th>
                <div class="flex-fill p-2">이메일</div>
            </th>
            <th><input type="text" class="flex-fill rounded p-2" id="email" placeholder="Enter email" name="email"
                       required></th>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
            <th><input type="button" class="p-2 btn-secondary" id="check_email" value="중복체크"/></th>
            <input type="hidden" id="email_duple_check" value="unCheck"/>
        </tr>

        <tr>
            <th></th>
            <th>
                <button id="btn-save" class="p-2 btn btn-secondary">회원가입</button>
            </th>
            <th></th>
        </tr>
        </tbody>
    </table>
    </div>

    <script src="/js/user.js"></script>
    <br/>

    <%@ include file="../layout/footer.jsp" %>

