<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <!-- errorPage="err500.jsp" 로 만들어서 따로 뺄 수 있다.
    page 지시어는 하나만 주는게 일반적이나, import 같은 경우 따로 빼기도 한다. -->
<%@ page import="java.util.ArrayList, java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>page 지시어</h1>
	
	
	<%
		ArrayList<String> list = new ArrayList<>();
		list.add("Servlet");
		list.add("jsp");
	
		Date today = new Date();
	%>
	
	현재날짜 및 시간 : <%= today%> <br>
	0번째 인덱스 : <%=list.get(0) %> <br>
	

</body>
</html>