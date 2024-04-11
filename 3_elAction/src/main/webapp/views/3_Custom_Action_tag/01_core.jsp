<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList, com.kh.model.vo.Person"%>
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
	
	
	<h3>4.반복문 - forEach</h3>
	<pre>
		for loop 문 - (c:forEach var="변수명" brgin="초기값" end="끝값" [step="반복시증가값"])
		향상된 for 문 - (c:forEach var="변수명" items="순차적으로 접근하고자 하는 객체(배열/컬렉션)" [varStatus="현재 접근된 요소의 상태값"])
	</pre>
	
	<%-- 
		<%for(int i = 1; i<= 10; i+=2){ %>
		<%} %>
	 --%>
	
	<c:forEach var="i" begin="1" end="10" step="2">
		반복확인 : ${i} <br>
	</c:forEach>
	
	<c:forEach var="i" begin="1" end="6">
		<h${i}>태그안에서 사용</h${i}>
	</c:forEach>
	
	
	<c:set var="colors">
		red, yellow, green, pink
	</c:set>
	colors 변수 : ${colors }<br>
	
	<ul>
		<c:forEach var="c" items="${colors}">
			<li style="color: ${c}">${c}</li>
		</c:forEach>			
	</ul>
	
	<%
		ArrayList<Person> list = new ArrayList<>();
		list.add(new Person("홍길동", 18, "남자"));
		list.add(new Person("김길동", 25, "여자"));
		list.add(new Person("이순신", 40, "여자"));
	%>
	
	<table>
		<thead>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
		</thead>
		<tbody>
		<%--
			<% if(list.isEmpty()){ %>
				<tr><td colspan="3">조회된 사람이 없습니다.</td>
				</tr>
			<%} else {%>
				<%for(Person p: list){ %>
					<tr>
					<td><%=p.getName() %></td>
					<td><%=p.getAge() %></td>
					<td><%=p.getGender() %></td>
				<%} %>
			<%} %>
			 --%>
			 
			 
		 <c:set var="pList" value="<%=list %>" scope="request"/>
		 <!-- 위의 Person 변수가 어디에 담겼는지 모른다
		 page, request, session, app 어딘가? 그래서 어디에 담을지 해줘야 -->
		 
		 <c:choose>
		 	<c:when test="${empty pList }">
			 	<tr><td colspan="3">조회된 사람이 없습니다.</td></tr>
		 	</c:when>
		 	<c:otherwise>
		 		<c:forEach var="p" items="${pList }" varStatus="status">
		 			<tr>
		 				<td>${status.count }</td>
		 				<td>${p.name }</td>
		 				<td>${p.age }</td>
		 				<td>${p.gender }</td>
		 			</tr>
		 		</c:forEach>
		 	</c:otherwise> 	
		 </c:choose>
		</tbody>
	</table>
	
	
	
	<h3>5. 반복문 - forTokens</h3>
	<pre>
	(c:forTokens var="변수명" items="분리하고싶은 문자열" delims="구분자")
	
	- 구분자를 통해서 분리된 각각의 문자열에 순차적으로 접근하면서 반복 수행
	- JAVA의 문자열.split("구분자")과 비슷
	</pre>
	
	<c:set var="device" value="컴퓨터,노트북/핸드폰.모니터,냉장고"/>
	
	<ol>
		<c:forTokens var="d" items="${device}" delims=",./">
			<li>${d}</li>
		</c:forTokens>
	</ol>
	
	
	
	<h3>6. url쿼리스트링</h3>
	<pre>
	- url 경로를 생성하고, 쿼리스트링을 정의해둘 수 있는 태그
	
	c:url var="변수명" value="요청url"
		c:param name="키값" value="전달할 값" /
		c:param name="키값" value="전달할 값" /
	/c:url
	</pre>
	
	<a href="list.do?cpage=1&num=2">기존방식</a>
	<c:url var="listUrl" value="list.do">
		<c:param name="cpage" value="1"/>
		<c:param name="num" value="2"/>
	</c:url>
	<a href="${listUrl}">c:url이용한 방식</a>
	
	<!-- jsp에서 jstl이라는 라이브러리를 이용해서 사용하는것이고
	jstl은 자바의 라이브라리다? 자바에서 돈다 
	jstl은 jsp 페이지에서 조금 더 문법을 편하게 쓸 수 있게 해주는 것
	
	화면을 통채로 바꾸는게 아닌
	브라우저 돔의 일부만 바꿔주는 것 ajax
	
	브라우저에서 도는 것은 무조건 html, css, js 
	결국 프론트엔드는 자스다. 리액트고 뭐고 다 자스임 -->
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
</body>
</html>