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
		overflow: hidden;
	}
	div.item{
		width: 100px;
		margin: 5px;
		float: left;
		position: relative;
	}
</style>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.5.3/handlebars.min.js"></script>
<script>
	$(function() {
		$("#modify").click(function() {
			location.href="modifyPage?bno=${boardVO.bno}&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}";
		})
		$("#remove").click(function() {
			var res = confirm("정말 삭제하시겠습니까?");
			if(res == true){
				location.href="removePage?bno=${boardVO.bno}&page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}";
			}
		})
		$("#listPage").click(function() {
			location.href="listPage?page=${cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}";
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
					<div class="form-group">
						<label>Image File</label>
						<div id="imagebox">
							<c:forEach var="file" items="${boardVO.files}">
								<div class="item">
									<img src="${pageContext.request.contextPath}/upload/displayFile?filename=${file}">
								</div>
							</c:forEach>
						</div>
					</div>
				</div><!-- box-body -->
				<div class="box-footer">
					<div class="btn btn-warning" id="modify">Modify</div>
					<div class="btn btn-danger" id="remove">Remove</div>
					<div class="btn btn-primary" id="listPage">Go List</div>
				</div><!-- box-footer -->
			</div>
		</div>
	</div>
	
	<!-- 댓글 추가 폼 -->
	<div class="row"> 
		<div class="col-xs-12">
			<div class="box box-success">
				<div class="box-header"> 
					<h3 class="box-title">Add New Reply</h3>
				</div>
				<div class="box-body">
					<label>Writer</label>
					<input type="text" class="form-control" id="newReplyer" value="${Auth}" readonly="readonly">
					<label>Reply Text</label>
					<input type="text" class="form-control" id="newReplyText">
				</div>
				<div class="box-footer">
					<button id="btnAdd" class="btn btn-info">Add Reply</button> <!-- 댓글 추가 버튼 -->
				</div>
			</div>
		</div>
	</div>
	
	<!-- 댓글 리스트 -->
	<ul class="timeline">
		<li class="time-label" id="repliesDiv"> <!-- 리스트 버튼 -->
			<span class="bg-green">Replies List [<span id="replyCnt">${boardVO.replycnt}</span>]</span>
		</li>
	</ul>
	<div class="text-center">
		<ul id="pagination" class="pagination pagination-sm no-margin">
			
		</ul>
	</div>
</section>

<!--수정창 -->
<div id="modifyModal" class="modal modal-primary fade" role="dialog">
	<div class="modal-dialog">
		<!--Modal content-->
		<div class="modal-contant">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">rno 번호 들어갈 곳</h4>
			</div>
			<div class="modal-body">
				<p>
					<input type="text" id="replytext" class="form-control">
				</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" id="btnModSave">Modify</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!---------- SCRIPT --------->
<script id="template" type="text/t-handlebars-template">
{{#list}}
	<li class="replyLi" data-rno="{{rno}}">
		<i class="fa fa-comments bg-blue"></i>
		<div class="timeline-item">
			<span class="time">
				<i class="fa fa-clock-o"></i> {{prettifyDate regdate}}
			</span>
			<h3 class="timeline-header"><strong>{{rno}}</strong> - {{replyer}}</h3>
			<div class="timeline-body">{{replytext}}</div>
			<div class="timeline-footer">
				<a class="btn btn-primary btn-xs" data-toggle="modal" data-target="#modifyModal" id="btnMod">modify</a>
				<a class="btn btn-danger btn-xs" id="btnDel">delete</a>
			</div>
		</div>
	</li>
{{/list}}
</script>

<script>
	var pageNo = 1;
	
	/* 댓글 기능 */
	Handlebars.registerHelper("prettifyDate", function(value) {
		var date = new Date(value);
		var year = date.getFullYear();
		var month = date.getMonth()+1;
		var day = date.getDate();
		
		return year + "-" + month + "-" + day;
	})
	
	/* 리스트 갱신 */
	function getPageList(page) {
		$.ajax({
			url: "${pageContext.request.contextPath}/replies/${boardVO.bno}/" + page,
			type: "get",
			dataType: "json",
			success: function(res) {
				console.log(res);

				/* 재클릭 시 기존 내용삭제 */
				$(".replyLi").remove();
				$("#pagination").empty();
				
				/* 템플릿 */
				var source = $("#template").html();
				var func = Handlebars.compile(source);
				var str = func(res);
				$(".timeline").append(str);
				
				/* 댓글 수 */	
				$("#replyCnt").text(res.pageMaker.totalCount);
				
				var startPage = res.pageMaker.startPage;
				var endPage = res.pageMaker.endPage;
				
				/* 이전 버튼 */
				if(res.pageMaker.prev == true){
					var $li = $("<li>");
					var $a = $("<a>").attr("data-page", startPage-1).attr("href", "#").html("&ltrif;");
					$li.append($a);
					$("#pagination").append($li);
				}
				
				/* 페이지 번호 */
				for(var i=startPage; i<=endPage; i++){
					var $li = $("<li>")
					var $a = $("<a>").attr("data-page", i).attr("href", "#").text(i);
					if(i == page){
						$li.addClass("active"); //선택된 페이지에 ".active" 클래스
					}
					$li.append($a);
					$("#pagination").append($li);
				}
				
				/* 다음 버튼 */
				if(res.pageMaker.next == true){
					var $li = $("<li>");
					var $a = $("<a>").attr("data-page", endPage+1).attr("href", "#").html("&rtrif;");
					$li.append($a);
					$("#pagination").append($li);
				}
			},
			error: function(err) {
				console.log(err);
			}
		})
	} //getPageList
	
	$("#repliesDiv").click(function() {
		getPageList(pageNo);
	})
	
	$(document).on("click", ".pagination a", function() {
		var page = $(this).attr("data-page");
		pageNo = page;
		getPageList(page);
	})
	
	/* 추가 */
	$(document).on("click", "#btnAdd",function() {
		var bno = ${boardVO.bno};
		var replyer = $("#newReplyer").val();
		var replyText = $("#newReplyText").val();
		var data = JSON.stringify({
				bno : bno,
				replyer: replyer,
				replytext: replyText
			});
		
		$.ajax({
			url: "${pageContext.request.contextPath}/replies/",
			type: "post",
			data: data,
			headers: {"Content-Type" : "application/json"},
			dataType: "text",
			success: function(res) {
				console.log(res);
				getPageList(pageNo);
				$("#newReplyer").val("");
				$("#newReplyText").val("");
			},
			error: function(err) {
				console.log(err);
			}
		})
	})
	
	/* 수정 */
	$(document).on("click", "#btnMod",function() {
		var rno = $(this).parents(".replyLi").attr("data-rno");
		$(".modal-title").text(rno);
		var replytext = $(this).parents(".replyLi").find(".timeline-body").text();
		$("#replytext").val(replytext);
	})
	
	/* 수정하기 */
	$(document).on("click", "#btnModSave",function() {
		var rno = $(".modal-title").text();
		var replytext = $("#replytext").val();				
			
		$.ajax({
			url: "${pageContext.request.contextPath}/replies/" + rno,
			type: "put",
			data: JSON.stringify({
				replytext: replytext
			}),
			headers: {"Content-Type" : "application/json"},
			dataType: "text",
			success: function(res) {
				console.log(res);
				$("#modifyModal").modal("hide");
				getPageList(pageNo);
			},
			error: function(err) {
				console.log(err);
			}
		})
	})
	
	/* 삭제 */
	$(document).on("click", "#btnDel",function() {
		var rno = $(this).parents(".replyLi").attr("data-rno");	
	
		$.ajax({
			url: "${pageContext.request.contextPath}/replies/" + rno,
			type: "delete",
			dataType: "text",
			success: function(res) {
				console.log(res);
				getPageList(pageNo);
			},
			error: function(err) {
				console.log(err);
			}
		})
	})
	
</script>

<%@include file="../include/footer.jsp"%>