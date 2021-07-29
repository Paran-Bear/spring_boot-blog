let index = {
	init: function() {
		$("#btn-cSave").on("click", () => {
			this.csave();
		});
	},


	csave: function() {
		let data= {
			name: $("#New_name").val(),	
		}
		
		//console.log(data);

		//ajax호출시 default는 비동기 호출
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청 
		//ajax가 통신 성공후 서버가 json을 리턴하면, 자동으로 자바 오브젝트로 변환해줌(아마 최신 버전이라)
		$.ajax({
			type: "POST",
			url: "/api/category",
			data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
			contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
			dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
			//안적어도 변환은 되긴 한다.
		}).done(function(resp) {
			location.href="/admin"
			alert("카테고리가 추가되었습니다.")
		}).fail(function(error) {
			//alert(JSON.stringify(error));
			console.log(data);
			alert("카테고리를 추가할수 없습니다.")
		});

	},
	cUpdate: function(categoryId) {
		let data= {			
			id: categoryId,
			name: $(`#${categoryId}`).val(),	
		}
		
		$.ajax({
			type: "PUT",
			url: "/api/category",
			data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
			contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
			dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
			//안적어도 변환은 되긴 한다.
		}).done(function(resp) {
			alert("카테고리가 변경되었습니다.");
			console.log(data);
			location.href = "/admin";
		}).fail(function(error) {

			console.log(error);

		});

	},
	cDelete: function(categoryId) {
		$.ajax({
			type: "DELETE",
			url: `/api/category/${categoryId}`,
			//data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
			contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
			dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
			//안적어도 변환은 되긴 한다.
		}).done(function(resp) {
			alert("카테고리가 삭제되었습니다.");
			
			location.href = "/admin";
		}).fail(function(error) {

			console.log(error);

		});
	}






}

index.init();