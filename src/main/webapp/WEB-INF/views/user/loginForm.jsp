<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>

<%@ include file="../layout/header.jsp" %>

<div class="container text-dark" style="padding: 5%;margin-top: 20%;width: fit-content;">
    <form action="/auth/loginProc" method="post">
        <table>
            <tbody>
            <tr>
                <th><label class="display-4 p-2">ID</label></th>
                <th><input type="text" class="display-4 p-1 rounded" id="username"
                           placeholder="Enter username" name="username" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </th>
            </tr>
            <tr>
                <th><label class="display-4 p-2">PW</label></th>
                <th><input type="password" class="display-4 p-1 rounded" id="password"
                           placeholder="Enter password" name="password" required></th>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </tr>
            <tr>
                <th></th>
                <th class="justify-content-center">
                    <button id="btn-login" class="btn btn-primary">·Î±×ÀÎ</button>
                    <a href="https://kauth.kakao.com/oauth/authorize?client_id=c7041e2ccdc7d398c97df1eeb2a0e598&redirect_uri=http://www.parangom.xyz/auth/kakao/callback&response_type=code">
                        <img height="38px" src="/image/icon/kakao_login_button.png"/>
                    </a>
                </th>
            </tr>
            </tbody>
        </table>
    </form>

</div>


<%@ include file="../layout/footer.jsp" %>

