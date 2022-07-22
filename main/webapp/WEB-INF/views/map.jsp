<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<style>
	#map{
		width : 400px;
		height : 400px;
	}
</style>


<body>

	<h3>글쓴이 위치</h3>
	<div id="map">
	
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0b2433be4a31662fe810753bc8a79d5c">
</script>
<script>
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)W
	};
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
</script>
	

</body>
</html>