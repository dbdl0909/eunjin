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
			<h1>Board List</h1>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="List" items="${boardArticleList}">
						<tr>
							<td>${List.boardArticleNo}</td>
							<td>
								<a href="/boardDetail?boardArticleNo=${List.boardArticleNo}">${List.boardArticleTitle}</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/boardAdd">글쓰기</a>
		</div>
	</body>
</html>