<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/logIn" method="post">
		<div>
			<label>아이디</label>
			<input type="text" name="id"/>
		</div>
		<div>
			<label>비밀번호</label>
			<input type="password" name="pw"/>
		</div>
		<input type="submit" value="로그인"/>
	</form>
</body>
</html>