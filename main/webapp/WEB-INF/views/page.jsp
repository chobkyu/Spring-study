<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<table>
		<thead>
			<tr>
				<th>seq</th>
				<th>study</th>
				<th>id</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="data" >
				<tr>
					<td><c:out value = "${data.seq}"/></td>
					<td><c:out value = "${data.study}"/></td>
					<td><c:out value = "${data.id}"/></td>
					<td><c:out value = "${data.date}"/></td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table> 
	
	<div>
	 <c:forEach begin="1" end="${pageNum}" var="num">
	    <span>
	     <a href="/page?num=${num}">${num}</a>
	  </span>
	 </c:forEach> 
	</div>
</body>
</html>