<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>jsp:include</h3>
	<p>또 다른 페이지를 포함하려할 때 쓰는 태그</p>

	<h4>1. 기존의 include 지시어를 이용한 방식(정적 include 방식 == 컴파일 시 애초에 포함되어 있는 형태)</h4>
	<%--
	<%@ include file="footer.jsp" %>
	<br>
	
	특징 : include 하고 있는 페이지 상에 선언되어있는 변수를 현재 페이지에서도 사용가능<br>
	include한 페이지의 year 변수 : <%=year %> <br><br>
	
	
	=> 단, 현지 페이지에서 include한 파일에 있는 변수와 동일한 이름의 변수 설정 불가 <br><br>
    <%int year = 2023; %> 
	--%>
	
	<h4>2. JSP 표준 액션 태그를 이용한 방식(동적 include 방식 == 런타임 시 포함되는 형태) </h4>
	<jsp:include page="footer.jsp"/>
	<br>
	
	특징1 : include 하고 있는 페이지에 선언된 변수를 공유하지 않음
	=> 동일한 이름의 변수로 재선언 가능하다 <br><br>
	<% int year = 2022; %>
	
	특징2 : 포함 시 include 하는 페이지로 값을 전달할 수 있음
	<jsp:include page="footer.jsp">
		<jsp:param name="test" value="hi"/>
	</jsp:include>
	
</body>
</html>