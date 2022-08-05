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
				<th>date</th>
				
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
	<c:if test="${prev}">
		 <span>[ <a href="/page?num=${startPageNum - 1}">이전</a> ]</span>
		</c:if>
		
		<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
		 <span>
		 
		  <c:if test="${select != num}">
		   <a href="page?num=${num}">${num}</a>
		  </c:if>    
		  
		  <c:if test="${select == num}">
		   <b>${num}</b>
		  </c:if>
		    
		 </span>
		</c:forEach>
		
		<c:if test="${next}">
		 <span>[ <a href="page?num=${endPageNum + 1}">다음</a> ]</span>
		</c:if>
	</div>
</body>
</html>