<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
		<div>
			<input type="text" class="form-control" id="title" placeholder="Enter username" name="username" required>
			</div>
		<div>
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
		<button id="btn-save" class="btn btn-primary">ÀúÀå</button>
	</form>

	

</div>
<script src="/js/board.js"></script>
<br />
 <script>
      $('.summernote').summernote({
        placeholder: 'Hello Bootstrap 4',
        tabsize: 2,
        height: 300
      });
    </script>
<%@ include file="../layout/footer.jsp"%>

