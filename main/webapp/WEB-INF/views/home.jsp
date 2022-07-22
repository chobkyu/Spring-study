<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
	<meta charset="UTF-8">
	<link rel= "stylesheet" href="resources/css/home.css">
</head>

<body>
	
	<div id= "page">
		<header>
			<div id="logo">
				<h1>공부 내용</h1>
			</div>
			
			<div id="top_menu">
				<a href="/img">이미지 업로드</a>
				<a href="/multi">다중 이미지 업로드</a>
				<a href="/formFile">해당 리스트 보기</a>
			</div>
			
			<nav>
				<ul>
					<li><a href="/">이미지 업로드</a></li>
					<li><a href="/">이미지 업로드</a></li>
					<li><a href="/">이미지 업로드</a></li>
					
				</ul>
			</nav>
			
			<article id = "content">
				<h1>Spring_Web</h1>
				<c:forEach items="${list}" var = "memberVO">
					<p><c:out value="${memberVO.name} "></c:out></p>
					<p><c:out value="${memberVO.age} "></c:out></p>
				</c:forEach>
				<br>
				<button onclick = "location.href='/img'">image</button>
			</article>
		</header>
	</div>

	
	
	
</body>
</html>