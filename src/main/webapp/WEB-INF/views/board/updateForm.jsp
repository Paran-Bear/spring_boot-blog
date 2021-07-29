<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
	<input type="hidden" id="id" value="${board.id}"/>
	<div class="input-group mt-3 mb-3">
			<div class="input-group-prepend">
				<div class="form-group">
					<select class="form-control" id="category">
						
						<c:forEach var="category" items="${category.content}">
							<option>${category.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<input type="text" class="form-control" id="title" placeholder="제목" value="${board.title }"name="username" required>
		</div>
		<div>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
		<button id="btn-update" class="btn btn-primary">저장</button>
	</form>



</div>
<script src="/js/board.js"></script>
<br />
<script>
	$('.summernote').summernote({
		placeholder : 'Hello Bootstrap 4',
		tabsize : 2,
		height : 300
	});
</script>
<%@ include file="../layout/footer.jsp"%>

