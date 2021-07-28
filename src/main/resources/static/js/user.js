let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},


	save: function() {
		let data = {
			username: $("#username").val(),
			nickname: $("#nickname").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};
		//console.log(data);

		//ajax호출시 default는 비동기 호출
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청 
		//ajax가 통신 성공후 서버가 json을 리턴하면, 자동으로 자바 오브젝트로 변환해줌(아마 최신 버전이라)
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
			contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
			dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
			//안적어도 변환은 되긴 한다.
		}).done(function(resp) {
			if (resp.status === 500) {
				alert("회원 가입이 불가능 합니다.")
			}
			else {
				alert("회원가입이 완료 되었습니다.");
				//console.log(resp);
				location.href = "/";
			}
		}).fail(function(error) {
			//alert(JSON.stringify(error));
			console.log(data);

		});

	},
	update: function() {
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
		}).done(function(resp) {
			//console.log(data);
			alert("수정이 완료 되었습니다.");

			location.href = "/";
		}).fail(function(error) {
			//alert(JSON.stringify(error));
			console.log(data);

		});
	}





}

index.init();