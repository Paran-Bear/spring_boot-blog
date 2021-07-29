let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			this.replySave();
		});


	},
	save: function() {
		let data = {
			
			title: $("#title").val(),
			content: $("#content").val(),
			category: $("#category").val(),
		};
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
			contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
			dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
			//안적어도 변환은 되긴 한다.
		}).done(function(resp) {
			alert("저장 되었습니다.");
			location.href = "/";
		}).fail(function(error) {

			console.log(data);

		});
	},
	deleteById: function() {
		let id = $("#id").text();
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
			//안적어도 변환은 되긴 한다.
		}).done(function(resp) {
			alert("삭제 되었습니다.");

			location.href = "/";
		}).fail(function(error) {

			console.log(data);

		});

	},
	update: function() {
		let id = $("#id").val();
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};
		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
			contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
			dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
			//안적어도 변환은 되긴 한다.
		}).done(function(resp) {
			alert("수정 되었습니다.");

			location.href = "/board/" + id;
		}).fail(function(error) {

			console.log(data);

		});
	},
	replySave: function() {
		let data = {
			content: $("#reply-content").val()
		};
		let boardId = $("#boardId").val();
		$.ajax({
			type: "POST",
			url: `/api/board/${boardId}/reply`,
			data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
			contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
			dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
			//안적어도 변환은 되긴 한다.
		}).done(function(resp) {

			alert("댓글이 저장되었습니다.");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {

			console.log(error);

		});
	},
	replyDelete: function(boardId,replyId) {
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			//data: JSON.stringify(data), //http body data, So need the "MINE TYPE"
			contentType: "application/json; charset=utf-8", //What type of body Data(MINE)
			dataType: "json"//Response from Request, bascically -> String Type(Json) --> Change to JavaScriptObject
			//안적어도 변환은 되긴 한다.
		}).done(function(resp) {
			alert("댓글이 삭제되었습니다.");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {

			console.log(error);

		});
	}

}
index.init();