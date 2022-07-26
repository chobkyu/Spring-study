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
			
			
		</header>
		
		<article id = "content">
				<h1>Spring_Web</h1>
				<table>
					<thead>
						<tr>
							<th>Study</th>
							<th>ID</th>
							<th>DATE</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td onclick="location.href='/img'">이미지 업로드</td>
							<td>조병규</td>
							<td>07-24</td>
						</tr>
						<tr>
							<td onclick="location.href='/formFile'">이미지 리스트</td>
							<td>조병규</td>
							<td>07-24</td>
						</tr>
						<tr>
							<td onclick="location.href='/multi'">다중파일 업로드</td>
							<td>조병규</td>
							<td>07-25</td>
						</tr>
						<tr>
							<td onclick="location.href='/map'">카카오맵, 공공데이터 API 사용</td>
							<td>조병규</td>
							<td>07-26</td>
						</tr>
						<tr>
							<td onclick="location.href='/logIn'">POST 사용해 로그인</td>
							<td>조병규</td>
							<td>07-29</td>
						</tr>
						<tr>
							<td onclick="location.href='/logOut'">로그아웃</td>
							<td>조병규</td>
							<td>08-02</td>
						</tr>
						<tr>
							<td onclick = "location.href='/page?num=1'">페이징</td>
							<td>조병규</td>
							<td>08-04</td>
						</tr>
					</tbody>
				</table>
				
				
				
		</article>
		
		<footer id="foot">
			<c:forEach items="${list}" var = "memberVO">
					<p><c:out value="${memberVO.name} "></c:out></p>
					<p><c:out value="${memberVO.age} "></c:out></p>
				</c:forEach>
				<br>
		</footer>
	</div>

	
	
	
</body>
</html>