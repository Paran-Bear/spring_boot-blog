<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<ul class="nav nav-pills">
		<li class="nav-item"><a class="nav-link" href="/board">전체</a></li>
		<c:forEach var="category" items="${category.content}">
			<c:if test="${categoryNum eq category.id }">
				<c:set var="cname" value="${category.name}"></c:set>
			</c:if>
			<li class="nav-item"><a class="nav-link" href="/board/category/${category.id}">${category.name }</a></li>
		</c:forEach>
	</ul>


	<c:choose>
		<c:when test="${categoryNum eq 0 }">
			<h2>전체</h2>
			<c:forEach var="board" items="${boards.content }">
				<div class="d-flex justify-content-start rounded border border-dark mb-3">
					<div class="p-2">${board.category.name }</div>
					<div class="p-2 flex-grow-1 text-center">
						<a class="text-dark" href="/board/${board.id}">${board.title}</a>
					</div>
					<div class="p-2 small">${board.count}</div>
					<div class="p-2 small">
						<fmt:parseDate var="parseRegDate" value="${board.createDate}" pattern="yyyy-MM-dd HH:mm" />
						<fmt:formatDate var="date" value="${parseRegDate}" pattern="yyyy-MM-dd HH:mm" />
						${date}
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:when test="${categoryNum ne 0}">
			<h2>${cname }</h2>
			<c:forEach var="board" items="${boards.content }">
				<c:if test="${board.category.id eq categoryNum }">
					<div class="d-flex justify-content-start rounded border border-dark mb-3">
						<div class="p-2">${board.category.name }</div>
						<div class="p-2 flex-grow-1 text-center">
							<a class="text-dark" href="/board/${board.id}">${board.title}</a>
						</div>
						<div class="p-2 small">
							<fmt:parseDate var="parseRegDate" value="${board.createDate}" pattern="yyyy-MM-dd HH:mm" />
							<fmt:formatDate var="date" value="${parseRegDate}" pattern="yyyy-MM-dd HH:mm" />
							${date}
						</div>
					</div>
				</c:if>
			</c:forEach>
		</c:when>
	</c:choose>




</div>
<br />

<%@ include file="../layout/footer.jsp"%>

