<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="layout/header.jsp"%>

<div class="container">
<!-- 	<ul class="nav nav-pills">
		<li class="nav-item"><a class="nav-link active" data-toggle="pill" href="#home">전체</a></li>
		<c:forEach var="category" items="${category.content }">
			<li class="nav-item"><a class="nav-link" data-toggle="pill" href="#${category.name}">${category.name}</a></li>
		</c:forEach>
	</ul>

	<div class="tab-content">
		<div class="tab-pane container active" id="home">
			<c:forEach var="board" items="${boards.content}">
				<div class="card m-2">
					<div class="card-body">
						<h4 class="card-title">
							<a href="/board/${board.id}">[${board.category}]${board.title}</a>
						</h4>
						<ul class="list-inline small">
							<li class="list-inline-item">${board.user.nickname}</li>
							<fmt:parseDate var="parseRegDate" value="${board.createDate}" pattern="yyyy-MM-dd HH:mm" />

							<fmt:formatDate var="date" value="${parseRegDate}" pattern="yyyy-MM-dd HH:mm" />

							<li class="list-inline-item">${date}</li>
						</ul>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:forEach var="category" items="${category.content }">
			<div class="tab-pane container fade" id="${category.name }">
				<c:forEach var="board" items="${boards.content}">
					<c:if test="${board.category == category.name}">
						<div class="card m-2">
							<div class="card-body">
								<h4 class="card-title">
									<a href="/board/${board.id}">[${board.category}]${board.title}</a>
								</h4>
								<ul class="list-inline small">
									<li class="list-inline-item">${board.user.nickname}</li>
									<fmt:parseDate var="parseRegDate" value="${board.createDate}" pattern="yyyy-MM-dd HH:mm" />

									<fmt:formatDate var="date" value="${parseRegDate}" pattern="yyyy-MM-dd HH:mm" />

									<li class="list-inline-item">${date}</li>
								</ul>
							</div>
						</div>
					</c:if>

				</c:forEach>
			</div>
		</c:forEach>


	</div>


	<ul class="pagination justify-content-center">

		<c:choose>
			<c:when test="${boards.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>

	</ul>
 -->
<p><a href="/board"> 보드 바로가기</a></p>

</div>
<br />

<%@ include file="layout/footer.jsp"%>

