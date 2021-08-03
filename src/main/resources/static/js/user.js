let index = {
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
        $("#check_username").on("click", () => {
            this.usernameCheck();

        });
        $("#check_nickname").on("click", () => {
            this.nicknameCheck();

        });
        $("#check_email").on("click", () => {
            this.mailCheck();

        });
    },


    save: function () {
        let check = {
            user_: $("#username_duple_check").val(),
            nick_: $("#nickname_duple_check").val(),
            email_: $("#email_duple_check").val(),
            password_: $("#check_password").val(),
        };

        let data = {
            username: $("#username").val(),
            nickname: $("#nickname").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };
        //console.log(data);
        var temp = 0;
        //ajax호출시 default는 비동기 호출
        //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
        //ajax가 통신 성공후 서버가 json을 리턴하면, 자동으로 자바 오브젝트로 변환해줌(아마 최신 버전이라)


        if (check.user_ == "check" && check.nick_ == "check" && check.email_ == "check") {
            if (data.password.trim().length < 8 || data.password.search(/\s/) != -1) {
                alert("비밀번호 양식을 확인해주세요.");
            } else {
                if (data.password == check.password_) {
                    $.ajax({
                        type: "POST",
                        url: "/auth/joinProc",
                        data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
                        contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
                        dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
                        //안적어도 변환은 되긴 한다.
                    }).done(function (resp) {
                        if (resp.status === 500) {
                            alert("회원 가입이 불가능합니다. 관리자에게 문의하세요");
                            console.log(resp);

                        } else {
                            alert("회원가입이 완료 되었습니다.");
                            console.log(resp);
                            location.href = "/";
                        }
                    }).fail(function (error) {
                        //alert(JSON.stringify(error));
                        console.log(data);

                    });
                } else {
                    alert("비밀번호가 서로 다릅니다.");
                }
            }
        } else {
            alert("중복체크를 해주세요.")

        }


    },
    update: function () {
        let data = {
            id: $("#id").val(),
            nickname: $("#nickname").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };

        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
            contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
            dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
            //안적어도 변환은 되긴 한다.
        }).done(function (resp) {
            //console.log(data);
            alert("수정이 완료 되었습니다.");

            location.href = "/";
        }).fail(function (error) {
            //alert(JSON.stringify(error));
            console.log(data);

        });
    },
    usernameCheck: function () {
        let username = $("#username").val();

        if (username.search(/\s/) != -1) {
            alert("아이디에는 공백이 들어갈 수 없습니다.");
        } else {
            if (username.trim().length != 0) {
                // alert(username);
                $.ajax({
                    async: true,
                    type: "POST",
                    url: `/auth/usernamecheck/${username}`,
                    // data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
                    // contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
                    // dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
                    //안적어도 변환은 되긴 한다.
                }).done(function (resp) {
                    //console.log(data);
                    if (resp.status == -1) {
                        alert("이미 사용중 입니다.");
                        $("#username").val("");

                    } else {
                        alert("사용 가능 합니다.");
                        $("#username_duple_check").val("check");
                        document.getElementById('check_username').className = 'btn-success';
                    }

                    // location.href = "/";
                }).fail(function (error) {
                    //alert(JSON.stringify(error));
                    alert("아이디를 입력해주세요");
                });
            } else {
                alert("아이디를 입력해주세요")
            }
        }


    },
    nicknameCheck: function () {
        let nickname = $("#nickname").val();
        if (nickname.search(/\s/) != -1) {
            alert("닉네임에는 공백이 들어갈 수 없습니다.");
        } else {
            if (nickname.trim().length != 0) {
                // alert(username);
                $.ajax({
                    async: true,
                    type: "POST",
                    url: `/auth/nicknamecheck/${nickname}`,
                    // data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
                    // contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
                    // dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
                    //안적어도 변환은 되긴 한다.
                }).done(function (resp) {
                    //console.log(data);
                    if (resp.status == -1) {
                        alert("이미 사용중 입니다.");

                        $("#nickname").val("");

                    } else {
                        alert("사용 가능 합니다.");
                        $("#nickname_duple_check").val("check");
                        document.getElementById('check_nickname').className = 'btn-success';
                    }

                    // location.href = "/";
                }).fail(function (error) {
                    //alert(JSON.stringify(error));
                    alert("닉네임을 입력해주세요");
                });
            } else {
                alert("닉네임을 입력해주세요")
            }
        }

    },
    mailCheck: function () {
        let email = $("#email").val();
        if (email.search(/\s/) != -1) {
            alert("이메일 형식에 맞춰주세요.");
        } else {
            if (email.trim().length != 0) {
                // alert(username);
                $.ajax({
                    async: true,
                    type: "POST",
                    url: `/auth/emailcheck/${email}`,
                    // data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
                    // contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
                    // dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
                    //안적어도 변환은 되긴 한다.
                }).done(function (resp) {
                    //console.log(data);
                    if (resp.status == -1) {
                        alert("이미 사용중 입니다.");

                        $("#email").val("");

                    } else {
                        alert("사용 가능 합니다.");
                        $("#email_duple_check").val("check");
                        document.getElementById('check_email').className = 'btn-success';
                    }

                    // location.href = "/";
                }).fail(function (error) {
                    //alert(JSON.stringify(error));
                    alert("이메일 형식에 맞춰주세요.");
                });
            } else {
                alert("이메일 형식에 맞춰주세요.");
            }
        }

    }
}

index.init();