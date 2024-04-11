<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>1. formatNumber</h3>
	<p>
		숫자데이터의 포맷(형식) 지정 <br>
		- 표현하고자 하는 숫자데이터의 형식을 통화기호, % 등 원하는 쓰임에 맞게 지정하는 태그
		
		(fmt:formatNumber value="출력할값" [groupinUsed="true|false" type="precent|currency" currencySymbol="문자"])
	</p>
	<c:set var="num1" value="123456789"/>
	<c:set var="num2" value="0.75"/>
	<c:set var="num3" value="50000"/>
	
	그냥 출력 ${num1}<br>
	세자리마다 구분하여 출력 : <fmt:formatNumber value="${num1 }"/> <br> <!-- 세자리 잘라주는게 true -->
	숫자그대로 출력 : ${num1 } <fmt:formatNumber value="${num1 }" groupingUsed="false"/> <br>

	percent : <fmt:formatNumber value="${num2}" type="percent"/><br>
	currency : <fmt:formatNumber value="${num3}" type="currency" groupingUsed="false"/><br>
	currency : <fmt:formatNumber value="${num3}" type="currency" currencySymbol="$"/><br>

	
	<h3>2. formatDate</h3>
	<p>날짜 및 시간 데이터의 포맷 지정(단, java.util.Date. 객체 사용)</p>
	
	<c:set var="current" value="<%=new java.util.Date() %>"/>
	출력 : ${currnet }
	
	<ul>
		<li>현재 날짜 : <fmt:formatDate value="${current }"/></li> <!-- type 생략 시 type="date"이 디폴트 -->
		<li>현재 시간 : <fmt:formatDate value="${current }" type="time"/></li> 
		<li>현재 날짜, 시간 : <fmt:formatDate value="${current }" type="both"/></li> 
		<li>medium : <fmt:formatDate value="${current }" type="both" dateStyle="medium" timeStyle="medium"/></li>
		<li>long : <fmt:formatDate value="${current }" type="both" dateStyle="medium" timeStyle="long"/></li>
		<li>full : <fmt:formatDate value="${current }" type="both" dateStyle="full" timeStyle="full"/></li>
		<li>short : <fmt:formatDate value="${current }" type="both" dateStyle="short" timeStyle="short"/></li>
		<li>pattern : <fmt:formatDate value="${current }" type="both" pattern="yyyy-MM-dd(E) HH:mm:ss(a)"/></li>
	</ul>
	
	
	
	<h4>3. JSTL function Library</h4>
	<p>EL 안에서 사용할 수 있는 메소드를 제공</p>
	<a href="views/3_Custom_Action_tag/03_fn.jsp">fn library</a>
	
	
	
	
	
	
	
	
	

</body>
</html>