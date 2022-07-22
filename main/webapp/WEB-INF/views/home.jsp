<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
	<meta charset="UTF-8">
	<link rel = "styleseet" href="resources/css/home.css">
</head>
<style>
	*{margin : 0px; padding:0px;}
	ul{list-style : none;}
	a{text-decoration:none;}
	
	header{
		width : 1200px;
		height : 95px;
		background-color : #2d3a4b; 
		position : relative;
	}
	
	#logo{
		position : absolute;
		top : 30px;
		left : 30px;
	}
	
	#top_menu{
		position : absolute;
		top : 20px;
		right : 10px;
		color : white;
	}
	
	#top_menu a{color : white;}
	
	nav{
		position : absolute;
		bottom : 10px;
		left : 220px;
		font-size : 16px;
	}
	
	nav li{
		display : inline;
		margin-left : 30px;
	}
	
	nav li a{
		color : white;
	}
	article{
	position : absolute;
		top : 95px;
	}
	#content h1{
		margin-top : 10px;
	}
	#content p{
		display : inline;
		margin-top : 10px;
		margin-left : 50px;
	}
	
	#page{
		//width : 820px;
		margin : 0px auto;
	}
</style>
<body>
	
	<div id= "page">
		<header>
			<div id="logo">
				<h1>공부 내용</h1>
			</div>
			
			<div id="top_menu">
				<a href="/">이미지 업로드</a>
				<a href="/">다중 이미지 업로드</a>
				<a href="/">해당 리스트 보기</a>
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
				<button onclick = "location.href='/img'">image</button>
			</article>
		</header>
	</div>

	
	
	
</body>
</html>