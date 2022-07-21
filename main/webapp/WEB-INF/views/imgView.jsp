<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>

<script languege="JavaScript">
	byte[] bydata = ${bydata};
	
	var base64 = Convert.ToBase64String(bydata);
	var imgSrc = String.Format("data:image/jpg;base64,{0}", base64);

</script>
<body>
	<!-- img태그의 src 경로는 byte이미지 가져오는 컨트롤러 호출(/getByteImage) -->
	<img alt="" src="${imgSrc}" />
	
	<div>
		<p>${board.title}</p>
		
	</div>
	<div>
		<p>${board.content}</p>
		
	</div>
</body>
  

</html>