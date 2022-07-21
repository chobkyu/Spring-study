<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function imgView(a){
		console.log(a);
		
		location.href = "/view?num="+a;
	}
</script>
<body>
	<h4>게시글</h4>
	<div>
		<c:forEach var="imgVO" items="${list}">
			<p onclick = "imgView(${imgVO.seq})"><c:out value="${imgVO.title}"></c:out></p> 
		</c:forEach>
		
	</div>
</body>
</html>