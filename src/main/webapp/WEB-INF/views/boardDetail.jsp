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
	</head>
	<body>
		<div class="container">
			<h1>Board Detail</h1>
			<a href='/boardUpdateForm?boardArticleNo=${map["boardArticle"].boardArticleNo}'>수정</a>
			<a href='/boardDelete?boardArticleNo=${map["boardArticle"].boardArticleNo}'>삭제</a>
			<table class="table">
				<tr>
					<th>제목</th>
					<td>${map["boardArticle"].boardArticleTitle}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${map["boardArticle"].boardArticleContect}</td>
				</tr>
				<c:if test='${map["boardFiles"].size() > 0}'>
					<c:forEach var="boardFile" items='${map["boardFiles"]}' varStatus="status">
						<tr>
							<th>${status.count}</th>
							<td>
								${boardFile.boardFileName}.${boardFile.boardFileExt}<br>
								<img src="/upload/${boardFile.boardFileName}.${boardFile.boardFileExt}" width="50%" />
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</body>
</html>