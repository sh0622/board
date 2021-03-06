<%--------------------------------------------------------------------------------
	* 화면명 : Smart Editor 2.8 에디터 연동 페이지
	* 파일명 : /page/test/index.jsp
--------------------------------------------------------------------------------%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Editor</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<link href="/jsp/bootstrap/css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap core CSS -->
<script src="/bootstrap/js/bootstrap.js"></script>
<!-- Custom styles for this template -->
<link href="/css/dashboard.css" rel="stylesheet">
<link href="/css/blog.css" rel="stylesheet">
<link href="/css/common.css" rel="stylesheet">
<link href="/css/offcanvas.css" rel="stylesheet">

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />

<!-- jQuery -->
<!-- <script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>-->

<!-- <script type="text/javascript" src="/js/jquery/jquery-3.2.1.js"></script> -->
<script type="text/javascript" src="/js/jquery-1.12.4.js"></script>


<script src="/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">


var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("저장하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
	})
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}

	return true;
}


$(function(){
    var cnt = 0;
	$("#file_cnt").val(cnt);
	
	
	$(".btnPlus").click(function(){
		if(cnt<4){
			cnt++;
			$("#file_cnt").val(cnt);
			var tag = "<div id=\"fileWrap\"><input type=\"file\" name=\"uploadFile"+cnt+"\"></div>";
			$("#fileWrap").append(tag);
		}
	});
	
	$(".btnMin").click(function(){
		if(cnt>0){
			cnt--;
			$("#file_cnt").val(cnt);
			$("div[id=fileWrap]:last").remove();
		}
	});
});


</script>

</head>
<body>
<%@ include file="/common/top.jsp" %>

	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="col-xs-12 col-sm-9">
				
				<div class="row">
					<div class="col-xs-12 blog-main">
						<h2 class="sub-header">게시물등록</h2>
						<div class="table-responsive">
						
				<!-- 여기서 부터 가져옴 -->
				<div class="col-xs-12 col-sm-12">
					<form class="form-horizontal" role="form" action="/postAdd" method="post" id="frm"  enctype="multipart/form-data">
						<input type="hidden" name="bd_no" value="${bd_no }" >
						<input type="hidden" name="bd_name" value="${bd_name}">
						<div class="form-group">
							<label for="pic" class="col-sm-1 control-label" style="text-align: left;">제목</label>
							<div class="col-sm-10">
								<input type="text" name="title" style="width:100%">
							</div>
						</div>

						<div class="form-group">
							<!-- 이 부분을 기반으로 에디터를 꾸며준다. -->
							<textarea name="smarteditor" id="smarteditor" rows="10" cols="50" style="width: 70%; height: 412px;"></textarea>
						</div>

						<label for="name" class="col-sm-2 control-label" style="text-align: left;">첨부파일</label>
						<div class="col-sm-10">
								<input type="hidden" name="file_cnt" id="file_cnt">
								<input type="hidden" name="pt_no" value="${pt_no }">
								<div id="fileWrap">
									<input type="file" name="uploadFile0" id="file">
								</div>
							<button type="button" class="glyphicon glyphicon-plus btnPlus"></button>
							<button type="button" class="glyphicon glyphicon-minus btnMin "></button>

							<input type="button" value="저장" id="savebutton" style="float: right; width:70px;" class="btn btn-primary">

						</div>
					</form>
				</div>
				<!--/row-->
			</div>
			<!--/.col-xs-12.col-sm-9-->
			</div>
			</div>
			</div>

			<%@ include file="/common/right.jsp"%>
			<hr>

		</div>
		<!--/.container-->
	</div>
	
</body>
</html>