<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Multipart Test</h1>
		<form method="post" enctype="multipart/form-data" action="/multipart">
			<input type="file" name="image"/>
			<input type="submit" value="확인"/>
		</form>
	</body>
</html>