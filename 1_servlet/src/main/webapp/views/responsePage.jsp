<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	
		// view의 요청을 위임해서 받아움 -> html 파일과 동일. 다른점: 내부에서 자바를 쓸 수 있다.
		// 베이스는 html, 사이사이 자바코드 넣어서 사용 가능.
		// 자바가 실행되고, 자바부분은 사라지고 html 겉모습만! 넘겨줌
		
		
		
		// 스크립틀릭(scriptlet) == html 문서 내에서 자바코드를 쓸 수 있는 영역
		
		
		// view.forward(request, response); request에서 위임해줘서 아래처럼 그대로 사용 가능
		String name = (String)request.getAttribute("name");
		//getAttribute 안에 들어갔다 나오면 무조건 Object로 바뀜
		
	%>
	
	<h1>개인정보 응답화면 - POST</h1>
	<span id="name"><%=name%>님은</span>
	<!-- 화면에 표시해 주기 위해 자바의 print 코드처럼, = 을 써준다. 예시 <%=name%> -->
	

</body>
</html>