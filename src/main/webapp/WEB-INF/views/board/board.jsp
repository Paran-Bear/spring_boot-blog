<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<div class="row">
		<div class="col-sm-2">
			<ul class="nav flex-column">
				<li class="nav-item"><a class="nav-link" href="/board">전체</a></li>
				<c:forEach var="category" items="${category.content}">
					<li class="nav-item"><a class="nav-link" href="/board/category/${category.id }">${category.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-sm-8">


			<!-- Tab panes -->
			<div class="tab-content p-2">

				<!-- 전체 목록 -->
				<div class="tab-pane container active" id="all">

					<c:forEach var="board" items="${boards.content}">

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

					</c:forEach>

				</div>

				<!-- 카테고리 별 목록 -->
				<c:forEach var="category" items="${category.content }">
					<div class="tab-pane container " id="${category.name}">
						<c:forEach var="board" items="${boards.content}">
							<c:if test="${board.category.name eq category.name }">
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
					</div>
				</c:forEach>

			</div>
			<!-- 페이징 버튼 -->
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

		</div>
	</div>
</div>
<br />

<%@ include file="../layout/footer.jsp"%>

