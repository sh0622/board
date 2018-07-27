<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<script src="/js/jquery-1.12.4.js"></script>
<link href="/jsp/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<script src="/bootstrap/js/bootstrap.js"></script>
<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/offcanvas.css" rel="stylesheet">

<script>
$(function(){
	var tag = "<div id=\"fileWrap\"><input type=\"text\" name=\"fileName\"><input type=\"file\" name=\"uploadFile\"></div>";
    var cnt = 1;
	
	$(".btnPlus").click(function(){
		if(cnt<5){
			$("#fileWrap").append(tag);
			++cnt;
		}
	});
	
	$(".btnMin").click(function(){
		if(cnt>1){
			$("div[id=fileWrap]:last").remove();
			--cnt;
		}
	});
});
</script>

</head>
<body>
	<%@ include file="/common/top.jsp"%>

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
				<div class="row">
					<div class="col-xs-12 blog-main">
						<h2 class=" sub-header">게시물등록</h2>
						<div class="table-responsive ">
							<!-- 여기서 부터 가져옴 -->
							<div class="col-xs-12 col-sm-9">
								<form class="form-horizontal" role="form" action="/postAdd"
									method="get" id="frm">
									<div class="form-group">
										<label for="pic" class="col-sm-2 control-label">제목</label>
										<div class="col-sm-10">
											<input type="text" name="title">
										</div>
									</div>

									<div class="form-group">
										<label for="id" class="col-sm-2 control-label">글내용</label>
										<form action="result.jsp" method="post" id="frm">
											<!-- 이 부분을 기반으로 에디터를 꾸며준다. -->
											<textarea name="smarteditor" id="smarteditor" rows="10"
												cols="100" style="width: 766px; height: 412px;"></textarea>
										</form>
										<input type="button" id="savebutton" value="서버전송" />
									</div>

									<div class="form-group">
										<label for="name" class="col-sm-2 control-label">첨부파일</label>
										<div class="col-sm-10">
											<form action="/fileUpload" method="post" enctype="multipart/form-data" id="filefm">
												<input type="hidden" name="pt_no" value="${pt_no }">
												<input type="hidden" name="file_cnt" value="cnt">
												<div id="fileWrap">
													<input type="file" name="uploadFile">
													
												</div>
												<button type="button"
													class="glyphicon glyphicon-plus btnPlus"></button>
												<button type="button"
													class="glyphicon glyphicon-minus btnMin"></button>
											</form>

										</div>
										<input type="submit" value="저장">
									</div>
								</form>
							</div>
							<!--/row-->
						</div>
					</div>
				</div>
			</div>
			<!--/.col-xs-12.col-sm-9-->

			<%@ include file="/common/right.jsp"%>
			<hr>

			<footer>
				<p>&copy; Company 2014</p>
			</footer>

		</div>
		<!--/.container-->
	</div>

</body>
</html>