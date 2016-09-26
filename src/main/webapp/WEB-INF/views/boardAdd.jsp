<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#fileAdd').click(function() {
					var flag = true;
					$('.boardImage').each(function(item, index) {
						if($(this).val() == "") {
							flag = false;
						}
					});
					if(flag) {
						$('#fileSection').append('<div><input type="file" class="boardImage" name="boardImage"/></div>');
					}
				});
				
				$('#boardAdd').click(function() {
					$('.boardImage').each(function(item, index) {
						if($(this).val() == "") {
							$(this).remove();
						}
					});
					
					if($('#boardTitle').val() == "") {
						$('#titleHelper').text('제목을 입력하세요');
					} else if($('#boardContent').val() == "") {
						$('#contentHelper').text('내용을 입력하세요');
					} else {
						$('#addForm').submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="container">
			<h1>Board Add</h1>
			<form method="post" enctype="multipart/form-data" action="/boardAdd" id="addForm">
				<table class="table">
					<tr>
						<th>제목</th>
						<td>
							<input type="text" id="boardTitle" name="boardTitle" value="${boardRequest.boardTitle}"/>
							<span id="titleHelper"></span>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<input type="text" id="boardContent" name="boardContent" value="${boardRequest.boardContent}"/>
							<span id="contentHelper"></span>
						</td>
					</tr>
					
					<tr>
						<th>이미지</th>
						<td>
							<input type="button" id="fileAdd" value="add"/><span>${msg}</span>
							<div id="fileSection">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan=2><input type="button" id="boardAdd" value="확인"/></td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>