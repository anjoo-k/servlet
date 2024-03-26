<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Include 지시어</h1>
	<div style="border:1px solid black">
		<%@ include file ="01_ScriptingElement.jsp" %>
	</div>
	
	포함한 jsp상에 선언되어있는 변수를 가져다 사용가능<br>
	1부터 100까지 총 합계 : <%=sum %>
	<!-- 주의!!! 헤더나 푸터에 사용하는 방식 jsp안에서 jsp를 불러오눈 것.
	그러면 library를 header에 한번만 넣어주면 된다.
	이 페이지에서 또 넣어주면 라이브러리를 두번 넣어준게 되는 것
	두번 클릭되서 안켜지거나 충돌해 오류가 난다. 주의!!! -->
	


</body>
</html>