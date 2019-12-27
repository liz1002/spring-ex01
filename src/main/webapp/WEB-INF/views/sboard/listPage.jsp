<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">LIST SEARCH PAGE</h3>
				</div><!-- box-header -->
				<div class="box-body">
					<select id="searchType">
						<option>---------- select ----------</option>
						<option ${cri.searchType=='t' ? 'selected' : ''} value="t">Title</option>
						<option ${cri.searchType=='c' ? 'selected' : ''}  value="c">Content</option>
						<option ${cri.searchType=='w' ? 'selected' : ''}  value="w">Writer</option>
						<option ${cri.searchType=='tc' ? 'selected' : ''}  value="tc">Title or Content</option>
						<option ${cri.searchType=='cw' ? 'selected' : ''}  value="cw">Content or Writer</option>
						<option ${cri.searchType=='tcw' ? 'selected' : ''}  value="tcw">Title or Content or Writer</option>
					</select>
					<input type="text" id="keyword" value="${cri.keyword}">
					<button id="btnSearch">SEARCH</button>
					<button id="btnRegister">NEW BOARD</button>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th style="width:40px;">BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th style="width:40px;">VIEWCNT</th>
						</tr>
						<c:forEach var="board" items="${list}">
							<tr>
								<td>${board.bno}</td>
								<td>
									<a href="readPage?
												bno=${board.bno}&page=${pageMaker.cri.page}&searchType=${cri.searchType}&keyword=${cri.keyword}&viewCnt=1">
										${board.title}</a>
									[${board.replycnt}]
								</td>
								<td>${board.writer}</td>
								<td>
									<fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd | HH:mm"/>
								</td>
								<td><span class="badge bg-red">${board.viewcnt}</span></td>
							</tr>
						</c:forEach>
					</table>
				</div><!-- box-body -->
				<div class="box-footer">
					<div class="text-center"> <!--자동 중앙 정렬 -->
						<ul class="pagination">
							<c:if test="${pageMaker.prev == true}">
								<li><a href="listPage?page=1&searchType=${cri.searchType}&keyword=${cri.keyword}">&ctdot;</a></li>
								<li><a href="listPage?page=${pageMaker.startPage-1}&searchType=${cri.searchType}&keyword=${cri.keyword}">&lBarr;</a></li>
							</c:if> 
							<c:forEach var="idx" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
								<li ${idx == pageMaker.cri.page ? 'class=active' : ''}> <!-- pageMaker.cri.page : 현재 페이지 번호 -->
									<a href="listPage?page=${idx}&searchType=${cri.searchType}&keyword=${cri.keyword}">${idx}</a>
								</li>
							</c:forEach>
							<c:if test="${pageMaker.next == true}">
								<li><a href="listPage?page=${pageMaker.endPage+1}&searchType=${cri.searchType}&keyword=${cri.keyword}">&rBarr;</a></li>
								<li><a href="listPage?page=${pageMaker.lastPage}&searchType=${cri.searchType}&keyword=${cri.keyword}">&ctdot;</a></li>
							</c:if>
						</ul>
					</div>
				</div><!-- box-footer -->
			</div>
		</div>
	</div>
</section>	

<script>
	$(function() {
		$("#btnSearch").click(function() {
			var type = $("#searchType").val();
			var keyword = $("#keyword").val();
		
			location.href = "listPage?searchType=" + type + "&keyword=" + keyword;
		})
		
		$("#btnRegister").click(function() {
			location.href = "register";
		})
	})
</script>

<%@include file="../include/footer.jsp"%>