<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<c:if test="${board.user.id eq principal.user.id}">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">����</a>
		<button id="btn-delete" class="btn btn-danger">����</button>
	</c:if>
	<br /> <br />
	<div align="right">

		No.<span id="id">${board.id}</span> : <span>${board.user.nickname}</span>


	</div>
	<div>
		<h3>[${board.category.name}]${board.title}</h3>
	</div>

	<hr>
	<div>${board.content}</div>

	<hr>
	<div>
		<button class="btn btn-secondary" onclick="history.back()">���ư���</button>

		<ul class="pagination justify-content-center">




			<c:if test="${boards.first ne true}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
			</c:if>
			<c:if test="${boards.first ne true}">
				<li class="page-item"><a class="page-link" href="/board/${board.id-1}">Previous</a></li>
			</c:if>

			<li class="page-item"><a class="page-link" href="/board/${board.id}">Next</a></li>
		</ul>
	</div>

	<c:if test="${not empty principal}">
		<div class="card">
			<form>
				<input type="hidden" id="boardId" value="${board.id}" />

				<div class="card-body">
					<textarea id="reply-content" class="form-control" row="7"></textarea>
				</div>
				<div class="card-footer">
					<button type="button" id="btn-reply-save" class="btn btn-primary">���</button>
				</div>
			</form>
		</div>
	</c:if>
	<c:if test="${empty principal}"><div>�α��ν� ����� �ۼ��� �� �ֽ��ϴ�.</div></c:if>
		
	
	<div class="card">
		<div class="card-header">���</div>

		<ul id="reply-box" class="list-group">
			<c:forEach var="reply" items="${board.replys}">

				<li id="reply-1" id="replyId-${reply.id}" class="list-group-item d-flex justify-content-between">

					<div>${reply.content}</div>
					<div class="d-flex">
						<div class="font-italic">${reply.user.nickname} &nbsp;</div>
						<input type="hidden" id="replyId" value="${reply.id}" />
						<c:if test="${reply.user.id eq principal.user.id}">
							<button onclick="index.replyDelete(${board.id},${reply.id})" class="badge">����</button>
						</c:if>
					</div>

				</li>
			</c:forEach>
		</ul>


	</div>

</div>


</div>
<script src="/js/board.js"></script>
<br />

<%@ include file="../layout/footer.jsp"%>