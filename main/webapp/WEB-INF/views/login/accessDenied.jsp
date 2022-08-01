<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<HTML> 
	<HEAD> 
		<META http-equiv="content-type" content="text/html; charset=euc-kr"> 
		<TITLE> 권한이 없습니다</TITLE> 
		<SCRIPT LANGUAGE="JavaScript"> 
		
		function Timer() { 
		setTimeout("locateKap()",10000); 
		 } 
		function locateKap(){
		 location.replace("/");
		}
		 
		</SCRIPT> 
		<script language='javascript'>
			  cnt = 10; // 카운트다운 시간 초단위로 표시
			  function countdown() {
			   if(cnt == 0){
			          // 시간이 0일경우
			         locateKap();
			   }else {
			         // 시간이 남았을 경우 카운트다운을 지속한다.
			        document.all.choonDiv.innerHTML = cnt + "초후에 인덱스 페이지로 이동이 됩니다..";
			        setTimeout("countdown()",1000);
			  cnt--;
			   }
			  }
		</script>
	</HEAD> 
	<BODY onLoad="Timer();">
		<div>권한이 없습니다</div>
		<div id="choonDiv"></div>
		<script>countdown();</script>
	</BODY> 
</HTML>
    <!-- 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>accessDenied</title>
	</head>
	<script>
		function Timer(){
			setTimeout("locateKap()",10000);
			
		}
		function locateKap(){
			location.replace("/");
		}
		var cnt =10;
		function countDown(){
			if(cnt==0){
				//시간이 0인 경우
				locateKap();
			}
			else {
				//시간이 남았을 경우 카운트 다운
				document.all.choondiv.innerHTML = cnt + "초 후에 페이지 이동";
				setTimeout("countDown()",1000);
				cnt--;
			}
		}
	</script>
	<body onLoad="Timer();">
		권한이 없습니다!!!!!
		<div id="chooDiv"></div>
		<script>countDown();</script>
	</body>
</html> -->