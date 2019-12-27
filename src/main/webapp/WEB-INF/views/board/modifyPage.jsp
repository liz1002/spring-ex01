<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<script>
	$(function() {
		$("#reset").click(function() {
			var res = confirm("수정을 취소하시겠습니까?");
			if(res == true){
				history.back();
			}
			return false;
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
				<form action="modifyPage?bno=${boardVO.bno}&page=${cri.page}" method="post">
					<div class="box-body">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" class="form-control" placeholder="Enter Title" value="${boardVO.title}">
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea rows="5" cols="10" name="content" class="form-control" placeholder="Enter Content">${boardVO.content}</textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${boardVO.writer}">
						</div>
					</div><!-- box-body -->
					<div class="box-footer">
						<button class="btn btn-primary" type="submit">Submit</button>
						<button class="btn btn-danger" id="reset">Reset</button>
					</div><!-- box-footer -->
				</form>
			</div>
		</div>
	</div>
</section>

<%@include file="../include/footer.jsp"%>