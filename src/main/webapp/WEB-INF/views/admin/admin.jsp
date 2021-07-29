<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>
<div class="container pt-3 ">
	<ul class="nav nav-pills">
		<li class="nav-item"><a class="nav-link active" data-toggle="pill" href="#category">카테고리</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="pill" href="#users">회원 관리</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="pill" href="#etc">기타</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content col-sm-6">

		<div class="tab-pane container active pt-3" id="category">

			<ul class="list-group">
				<div class="input-group mb-3">
					<input type="text" class="form-control" id="New_name" placeholder="카테고리 추가">
					<div class="input-group-append">
						<button class="btn btn-success" id="btn-cSave" type="button">추가</button>
					</div>
				</div>
				<c:forEach var="category" items="${categorys.content}">
					<div class="input-group mb-3">

						<input type="text" class="form-control" id="${category.id}" placeholder="${category.name}">
						<div class="input-group-append">
							<button onclick="index.cUpdate(${category.id})" class="btn btn-secondary" type="button">수정</button>
							<button onclick="index.cDelete(${category.id})" class="btn btn-danger" type="button">삭제</button>
						</div>

					</div>
				</c:forEach>


			</ul>

		</div>
		<div class="tab-pane container fade" id="users">...</div>
		<div class="tab-pane container fade" id="etc">...</div>
	</div>
</div>
<script src="/js/admin.js"></script>
<%@ include file="../layout/footer.jsp"%>