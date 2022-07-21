<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/saveImage" enctype="multipart/form-data" method="post">
		<div>
			<label>title</label>
			<input type="text" name="title" value=""/>
			
		</div>
		<div>
			<label>content</label>
			<input type="text" name="content" value=""/>
			
		</div>
		
		
		<input type="file" name="imgFile" />
		<input type="submit" value="이미지저장"/>
		
	</form>



</body>
</html>