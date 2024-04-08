<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL Core Library</h1>
	
	<h3>1. 변수(속성 == attribute)</h3>
	<pre>
		*변수 선언과 동시에 초기화(c:set var="변수명" value="값" [scope="저장객체"])
		 - 변수 선언하고 초기값을 대입해두는 기능을 제공
		 - 해당 변수를 어떤 scope에 setAttribute를 통해서 담아둘지 결정할 수 있다.
		 => c:set으로 선언한 변수는 접근할 때 무조건 EL로 접근해야 한다.(스크립틀릿 XXX)
		 
	</pre>
	
	<c:set var="num1" value="10"/>
	<!-- pageContext.setAttribute("num1", 10); -->
	<c:set var="num2" value="20" scope="request"/>
	<!-- pageContext.setAttribute("num2", 20); -->
	num1 변수 : ${num1 } <br>
	num2 변수 : ${num2 } <br>
	
	<c:set var="result" value="${num1+num2}" scope="session"/>
	result 변수 : ${result } <br><br>
	
	${ pageScope.num1 } <br>
	${ requestScope.result } <br>
	${ pageScope.num1 } <br>
	
	<c:set var="result" scope="request">
		9999
	</c:set>
	${ requestScope.result } <br><br><br>
	
	
	<pre>
	* 변수 삭제(c:remove var="제거하고싶은 변수명" [scope=""])
	  - 해당 scope 영역에 해당 변수를 찾아서 제거하는 태그
	  - scope 지정 생략 시 모든 scope에서 모든 해당 변수를 찾아 제거
	  => 즉, 해당 scope에 .removeAttribute를 통해 제거하는 것이라고 생각하면 된다.
	</pre>
	
	삭제전 : ${result } <br><br>
	
	1) 특정 scope 지정해서 삭제
	<c:remove var="result" scope="request"/>
	request에서 삭제 후 result : ${result } <br>
	
	2) 모든 scope에서 삭제
	<c:remove var="result"/>
	모두 삭제 후 result : ${result } <br><br>
	
	<hr>
	
	<pre>
	*변수(데이터) 출력 (c:out value="출력하고자하는 값" [default="기본값"] [escapeXml="true | false"])
	</pre>
	
	<c:out value="${result }"/> <br>
	<c:out value="${result }" default="없음"/> <br>
		<% System.out.print("a"); %>
	<c:set var="outTest" value="<b>출력테스트</b>"/> <br>
	<c:out value="${outTest }"/> <br> <!-- escapeXml 생략 시 기본값이 true == 태그해석 안됨 -->
	<c:out value="${outTest }" escapeXml="false"/> <br>

	<hr>
	
	<h3>2. 조건문</h3>
	<pre>
	- JAVA의 if 문과 비슷한 역할을 하는 태그
	- 조건식은 test 속성에 작성(단, EL구문으로 기술히야 한다.)
	</pre>
	
	<c:if test="${ num1 > num2 }">
		<b>num1이 num2보다 큽니다.</b>
	</c:if>
	
	<c:if test="${ num1 lt num2 }">
		<b>num1이 num2보다 작습니다.</b>
	</c:if>
	
	<c:set var="str" value="안녕"/>
	<c:if test="${str eq '안녕' }">
		<h4>그래 안녕</h4>
	</c:if>
	<c:if test="${str ne '안녕' }">
		<h4>뭐지</h4>
	</c:if>
	
	
	<h3>3. 조건문 - choose(c:choose, c:when, c:otherwise)</h3>
	<c:choose>
		<c:when test="${num1 gt 30 }">
		</c:when>
		<c:when test="${num1 gt 20 }">
		</c:when>
		<c:when test="${num1 gt 10 }">
		</c:when>
		<c:otherwise>
			모든 조건은 맞지 않다.
		</c:otherwise>
	</c:choose>
</body>
</html>