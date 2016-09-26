<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				
				$('.fileDivClick').click(function() {
					var divIndex = $('.fileDivClick').index(this);
					//console.log(divIndex);
					
					if (window.confirm('선택한 이미지를 지우시겠습니까?')) {
						var index = $('.boardFileNo').val();
						//console.log(index);
						$('.fileDivClick').eq(divIndex).remove();
					}
				});
				
				$('#boardUpdateBtn').click(function() {
					$('.boardImage').each(function(item, index) {
						if($(this).val() == "") {
							$(this).remove();
						}
					});
					
					$('#updateForm').submit();
				})
				
				$('#backBtn').click(function() {
					window.history.back();
				});
				
			});
		</script>
	</head>
	<body>
		<div class="container">
			<h1>Board Update</h1>
			<form method="post" enctype="multipart/form-data" action="/boardUpdate" id="updateForm">
				<table class="table">
					<tr>
						<th>제목</th>
						<td><input type="text" name="boardTitle" value='${map["boardArticle"].boardArticleTitle}' /></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><input type="text" name="boardContent" value='${map["boardArticle"].boardArticleContect}' /></td>
					</tr>
					<tr>
						<td>
							<input type="button" id="fileAdd" value="add"/><span>${msg}</span>
							<div id="fileSection">
							</div>
						</td>
					</tr>
				</table>
				<c:if test='${map["boardFiles"].size() > 0}'>
					<c:forEach var="boardFile" items='${map["boardFiles"]}' varStatus="status">
						<div class="fileDivClick">
							${status.count}
							
							<img src="/upload/${boardFile.boardFileName}.${boardFile.boardFileExt}" width="50%" />
							<input type="hidden" class="boardFileNo" name="boardFileNo" value="${boardFile.boardFileNo}" />
						</div>
					</c:forEach>
				</c:if>
				<div>
					<input type="hidden" name="boardArticleNo" value='${map["boardArticle"].boardArticleNo}' />
					<input type="button" id="boardUpdateBtn" value="수정" />
					<input type="button" id="backBtn" value="취소" />
				</div>
			</form>
		</div>
	</body>
</html>