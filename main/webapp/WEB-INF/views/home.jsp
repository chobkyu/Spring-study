<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>
		Hello world!  
	</h1>

	<P>  The time on the server is ${serverTime}. </P>
	<p> hihihihihihihihi </p>
	<c:forEach items="${list}" var = "memberVO">
		<p><c:out value="${memberVO.name} "></c:out></p>
		<p><c:out value="${memberVO.age} "></c:out></p>
	</c:forEach>
	<button onclick = "location.href='/img'">image</button>
</body>
</html>