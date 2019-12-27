<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>

<section class="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">LIST ALL</h3>
				</div><!-- box-header -->
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
									<a href="read?bno=${board.bno}&viewCnt=1">${board.title}</a>
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
			</div>
		</div>
	</div>
</section>	

<%@include file="../include/footer.jsp"%>