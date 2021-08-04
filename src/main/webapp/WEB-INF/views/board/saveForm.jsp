<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>

<%@ include file="../layout/header.jsp" %>

<div class="container m-5">


    <div class="mt-3 mb-3">
        <select id="categoryId">
            <c:forEach var="category" items="${category.content}">
                <option value="${category.id}">${category.name }</option>
            </c:forEach>
        </select>
        <input type="text" class="form-control" id="title" placeholder="제목" required>
    </div>
    <div>
        <textarea class="form-control summernote" rows="5" id="content"></textarea>
    </div>
    <button id="btn-save" class="btn btn-primary">저장</button>


</div>
<script src="/js/board.js"></script>
<br/>
<script>
    $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 300
    });
</script>
<%@ include file="../layout/footer.jsp" %>

