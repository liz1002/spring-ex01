<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<script>
	$(function() {
		$("#modify").click(function() {
			location.href="modify?bno=${boardVO.bno}";
		})
		$("#remove").click(function() {
			var res = confirm("정말 삭제하시겠습니까?");
			if(res == true){
				location.href="remove?bno=${boardVO.bno}";
			}
		})
		$("#listAll").click(function() {
			location.href="listAll";
		})
	})
</script>

<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div><!-- box-header -->
				<div class="box-body">
					<div class="form-group">
						<label>Title</label>
						<input type="text" name="title" class="form-control" placeholder="Enter Title" value="${boardVO.title}" readonly="readonly">
					</div>
					<div class="form-group">
						<label>Content</label>
						<textarea rows="5" cols="10" name="content" class="form-control" placeholder="Enter Content" readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label>Writer</label>
						<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${boardVO.writer}" readonly="readonly">
					</div>
				</div><!-- box-body -->
				<div class="box-footer">
					<div class="btn btn-warning" id="modify">Modify</div>
					<div class="btn btn-danger" id="remove">Remove</div>
					<div class="btn btn-primary" id="listAll">Go List</div>
				</div><!-- box-footer -->
			</div>
		</div>
	</div>
</section>

<%@include file="../include/footer.jsp"%>