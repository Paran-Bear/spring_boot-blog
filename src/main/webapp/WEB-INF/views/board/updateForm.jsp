<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container m-5">

		<input type="hidden" value="${principal.user.id }"/>
	
		<input type="hidden" id="id" value="${board.id}" />
		<div class="mt-3 mb-3">


			<select id="category">

				<c:forEach var="category" items="${category.content}">	
					<c:choose>
						<c:when test="${category.name eq board.category.name }">
							<option value="${category.id}" selected>${category.name }</option>
						</c:when>
						<c:otherwise>
							<option value="${category.id}">${category.name }</option>
						</c:otherwise>

					</c:choose>

				</c:forEach>
			</select>
		</div>

		<input type="text" class="form-control" id="title" placeholder="제목" value="${board.title }" name="username" required>

		<div>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
		<button id="btn-update" class="btn btn-primary">저장</button>
	



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

