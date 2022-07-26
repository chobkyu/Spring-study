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
		width : 800px;
		height : 800px;
	}
</style>


<body>

	<h3>글쓴이 위치</h3>
	<div id="map">
	
	</div>
	<p><em>지도를 클릭해주세요!</em></p> 
<div id="clickLatlng"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0b2433be4a31662fe810753bc8a79d5c"></script>
	<script>
	
		
		var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(37.584466044995345 , 126.96999459195231), //지도의 중심좌표.
			level: 4 //지도의 레벨(확대, 축소 정도)W
		};
		var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		
		//API에서 받아온 좌표
		var apiLat = ${lat};
		var apiLon = ${lon};
		var markerPosition  = new kakao.maps.LatLng(${lat}, ${lon}); 
		
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		
		//api에서 받아온 좌표 길이
		var polyLine;
		var Overlay;
		//폴리곤 구하기
		var lat = 37.584466044995345;
		var lng = 126.96999459195231;
		var polygonPath ;
		var polygon;
		
		//거리 구하기
		var drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
		var moveLine; // 선이 그려지고 있을때 마우스 움직임에 따라 그려질 선 객체 입니다
		var distanceOverlay; // 선의 거리정보를 표시할 커스텀오버레이 입니다
		var dots = {}; // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.
		
		var clickLine = new Array();
		var distanceOverlay = new Array();
		
		var positions = [
			
		    {
		        title: '경복고', 
		        latlng: new kakao.maps.LatLng(37.58689690165778, 126.97172872732155)
		    },
		    {
		        title: '영빈관', 
		        latlng: new kakao.maps.LatLng(37.58470798201685, 126.97386936851382)
		    },
		    {
				title: '엔씨엘', 
		        latlng: new kakao.maps.LatLng(37.584466044995345 , 126.96999459195231 )
		    },
		];
		
		
        
        //console.log(clickLine.getLength().getLat());
		/*
		// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
		var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

		    
		 // 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
		    content : iwContent,
		    removable : iwRemoveable
		});
		    
		// 마커에 클릭이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'click', function() {
		      // 마커 위에 인포윈도우를 표시합니다
		      infowindow.open(map, marker);  
		});
		*/
		
		var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
	    
		
		for (var i = 0; i < positions.length; i ++) {
			
			   
		    // 마커 이미지의 이미지 크기 입니다
		    var imageSize = new kakao.maps.Size(24, 35); 
		    
		    // 마커 이미지를 생성합니다    
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
			// 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        image : markerImage // 마커 이미지 
		    });
			
		    var iwContent = '<div style="padding:5px;">'+positions[i].title+'</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		    iwPosition = positions[i].latlng; //인포윈도우 표시 위치입니다

			// 인포윈도우를 생성합니다
			var infowindow = new kakao.maps.InfoWindow({
			    position : iwPosition, 
			    content : iwContent 
			});
			  
			// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
			infowindow.open(map, marker); 
		}
		
		function polyPoly(polyLat,polyLng){
			// 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
			polygonPath = [
			    new kakao.maps.LatLng(37.58689690165778, 126.97172872732155),
			    new kakao.maps.LatLng(37.58470798201685, 126.97386936851382),
			    new kakao.maps.LatLng(polyLat , polyLng ) ,
			    new kakao.maps.LatLng(37.58689690165778, 126.97172872732155),
			];

			// 지도에 표시할 다각형을 생성합니다
			polygon = new kakao.maps.Polygon({
			    path:polygonPath, // 그려질 다각형의 좌표 배열입니다
			    strokeWeight: 3, // 선의 두께입니다
			    strokeColor: '#39DE2A', // 선의 색깔입니다
			    strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
			    strokeStyle: 'longdash', // 선의 스타일입니다
			    fillColor: '#A2FF99', // 채우기 색깔입니다
			    fillOpacity: 0.7 // 채우기 불투명도 입니다
			});

			// 지도에 선을 표시합니다 
			polygon.setMap(map); 
		}
		
		polyPoly(lat,lng); //폴리곤 실행
		polyPolyline(lat, lng); //폴리 라인 실행
		apiPoly(lat, lng);
		// 지도에 클릭 이벤트를 등록합니다
		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
										
			for (var i=0; i<clickLine.length; i++) {
				clickLine[i].setMap(null);
				distanceOverlay[i].setMap(null);  
			}
			
			
			//기존 폴리곤 제거
			polygon.setMap(null);
			
			//기존 거리 구하는거 제거
			Overlay.setMap(null);
			polyLine.setMap(null);
		    // 클릭한 위도, 경도 정보를 가져옵니다 
		    var latlng = mouseEvent.latLng; 
		    
		    // 마커 위치를 클릭한 위치로 옮깁니다
		    marker.setPosition(latlng);
		    
		    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
		    message += '경도는 ' + latlng.getLng() + ' 입니다';
		    
		    lat = latlng.getLat();
		    lng = latlng.getLng();
		    console.log(lat);
		    console.log(lng);
		 // 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
		 	
		 	 
			polyPoly(lat,lng);
			polyPolyline(lat, lng);
			apiPoly(lat, lng);
		    console.log(polygonPath[2]);
		    var resultDiv = document.getElementById('clickLatlng'); 
		    resultDiv.innerHTML = message;
		    
		    
		});
		
		function polyPolyline(lat, lng){
			var linePath =  [
	        	new kakao.maps.LatLng(37.58689690165778, 126.97172872732155),
	        	new kakao.maps.LatLng(37.58470798201685, 126.97386936851382),
	        	new kakao.maps.LatLng(lat , lng),
	        	new kakao.maps.LatLng(37.58689690165778, 126.97172872732155)
	        ];
			
	        
	        
	        for(i=0; i<linePath.length-1;i++){
	        	clickLine[i] = new kakao.maps.Polyline({
	                map: map, // 선을 표시할 지도입니다 
	                path: [
	                	linePath[i],
	                	linePath[i+1]
	                ], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
	                strokeWeight: 3, // 선의 두께입니다 
	                strokeColor: '#db4040', // 선의 색깔입니다
	                strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
	                strokeStyle: 'solid' // 선의 스타일입니다
	            });
	        	distanceOverlay[i] = new kakao.maps.CustomOverlay({
	                content: '<div class="dotOverlay">거리 <span class="number">' + Math.floor(clickLine[i].getLength()) + '</span>m</div>',
	                position: linePath[i],
	                yAnchor: 1,
	                zIndex: 2
	            });

	            // 지도에 표시합니다
	            clickLine[i].setMap(map); //clickLine = 객체, clickLine[i] = ClickLine()
	            distanceOverlay[i].setMap(map);
	        }
		}
		
		function apiPoly(lat, lng){
			var linePath = [
				new kakao.maps.LatLng(apiLat , apiLon),
				new kakao.maps.LatLng(lat , lng),
			];
			polyLine = new kakao.maps.Polyline({
				map: map, // 선을 표시할 지도입니다 
                path: [
                	linePath[0],
                	linePath[1]
                ], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
                strokeWeight: 3, // 선의 두께입니다 
                strokeColor: '#db4040', // 선의 색깔입니다
                strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
                strokeStyle: 'solid' // 선의 스타일입니다
			});
			Overlay = new kakao.maps.CustomOverlay({
                content: '<div class="dotOverlay">거리 <span class="number">' + Math.floor(polyLine.getLength()) + '</span>m</div>',
                position: linePath[0],
                yAnchor: 1,
                zIndex: 2
            });
			
			polyLine.setMap(map);
			Overlay.setMap(map);
		}
	</script>
		

</body>
</html>