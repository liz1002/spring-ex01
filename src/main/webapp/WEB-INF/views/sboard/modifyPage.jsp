<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<style>
	#previewbox{
		border: 1px solid #d2d6de;
		width: 100%;
		min-height: 100px;
		margin-top: 5px;
		padding: 5px;
		overflow: hidden;
	}
	#previewbox img{
	  width: 100px;
	  height: 100px;
	}

	#imagebox{
		border: 1px solid #d2d6de;
		width: 100%;
		min-height: 100px;
		margin-top: 5px;
		padding: 5px;
		overflow: hidden;
	}
	div.item{
		margin: 5px;
		float: left;
		position: relative;
	}
	.item button.del{
		position: absolute;
		right: 3px;
		top: 3px;
		font-weight: bold;
		opacity: 0.7;
	}
</style>

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
				<form action="modifyPage?bno=${boardVO.bno}&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}" method="post" enctype="multipart/form-data">
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
						<div class="form-group">
							<label>New Image Files</label>
							<input type="file" name="imgFiles" class="form-control" multiple="multiple">
							<div id="previewbox"></div>
						</div>
						<div class="form-group uploadDiv">
							<label>Image File</label>
							<div id="imagebox">
								<c:forEach var="file" items="${boardVO.files}">
									<div class="item">
										<img src="${pageContext.request.contextPath}/upload/displayFile?filename=${file}">
										<button type="button" class="del" data-src="${file}">X</button>
									</div>
								</c:forEach>
							</div>
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

<script>
	/* $(function() {
		$("div.item img").each(function(i, obj) {
			var width = $(obj).width();
			$(obj).parent().width(width);
		})	
	}) */
	
	$(".del").click(function() {
		var filename = $(this).attr("data-src"); //삭제할 파일명
		var $input = $("<input>").attr("type", "hidden").attr("name", "delFiles").val(filename);
		$(".uploadDiv").append($input);
		$(this).parent().remove();
	})
	
	$("input[name='imgFiles']").change(function() {
		$("#previewbox").empty();
		
		for(var i=0; i<$(this)[0].files.length; i++){
			var file = $(this)[0].files[i];
			var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload = function(e) {
				var $div = $("<div>").addClass("item");
				var $img = $("<img>").attr("src", e.target.result);
				$div.append($img)
				$("#previewbox").append($div);
			}
		}
	})
	
	
</script>

<%@include file="../include/footer.jsp"%>