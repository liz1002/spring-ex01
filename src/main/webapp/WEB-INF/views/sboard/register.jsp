<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<style>
	#imagebox{
		border: 1px solid #d2d6de;
		width: 100%;
		min-height: 100px;
		margin-top: 5px;
		padding: 5px;
	}
	#imagebox img{
		width: 100px;
		height: 100px;
		margin: 5px;
	}
</style>

<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div><!-- box-header -->
				<form action="register" method="post" enctype="multipart/form-data">
					<div class="box-body">
						<div class="form-group">
							<label>Title</label>
							<input type="text" name="title" class="form-control" placeholder="Enter Title">
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea rows="5" cols="10" name="content" class="form-control" placeholder="Enter Content"></textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" name="writer" class="form-control" placeholder="Enter Writer" value="${Auth}" readonly="readonly">
						</div>
						<div class="form-group">
							<label>Image Files</label>
							<input type="file" name="imageFiles" class="form-control" multiple="multiple" placeholder="Enter File">
							<div id="imagebox"></div>
						</div>
					</div><!-- box-body -->
					<div class="box-footer">
						<button class="btn btn-primary" type="submit">Submit</button>
					</div><!-- box-footer -->
				</form>
			</div>
		</div>
	</div>
</section>

<script>
	$("input[name='imageFiles']").change(function() {
		$("#imagebox").empty();
		
		for(var i=0; i<$(this)[0].files.length; i++){
			var file = $(this)[0].files[i];
			var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload = function(e) {
				var $img = $("<img>").attr("src", e.target.result);
				$("#imagebox").append($img);
			}
		}
	})
</script>

<%@include file="../include/footer.jsp"%>