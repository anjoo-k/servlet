<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.board.model.vo.Board" %>
<%
	Board b = (Board)request.getAttribute("board");
// 글번호, 카테고리명, 제목, 내용, 작성자, 작성일 in
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
       background: rgb(77, 102, 82);
       color: rgb(226, 219, 158);
       width: 1000px;
       height: 500px;
       margin: auto;
       margin-top: 50px;
   }
   .outer table {
       border: 1px solid white;
       border-collapse: collapse;
       /* 보더 두줄로 나오는거 합치기 */
   }
   .outer > table tr, .outer > table td {
       border: 1px solid white;

   }
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp"%>
    
    <div class="outer">
        <br>
        <h2 align="center">일반게시판 상세보기</h2>
        <br>

        <table id="detail-area" border="1" align="center">
            <tr>
                <th width="70">카테고리</th>
                <td><%=b.getBoardCategoryNo() %></td>
                <th width="70">제목</th>
                <td width="350"><%=b.getBoardTitle() %></td>

            </tr>
            <tr>
                <th>작성자</th>
                <td><%=b.getBoardWriter() %></td>
                <th>작성일</th>
                <td><%=b.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 200px;"><%=b.getBoardContent() %></p>

                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                    <!-- case1 첨부파일 없을 때
                    첨부파일이 없습니다. -->
                    <!-- case2 첨부파일이 있을 때 -->
                    <a download="첨부파일1" href="https://i1.ruliweb.com/ori/20/09/01/17447a1911b526e8b.jpg">
                    file20230401</a>
                </td>
            </tr>

        </table>
        <br>
        <div align="center">
            <a href="<%=contextPath %>/list.bo?cpage=1" class="btn btn-sm btn-secondary">목록가기</a>
            <!-- css로 만든 버튼모양. 버튼모양이 나온다. -->
            <%if(loginUser != null && loginUser.getUserId().equals(b.getBoardWriter())) { %>
            <a href="" class="btn btn-sm btn-warning">수정하기</a>
            <a href="" class="btn btn-sm btn-danger">삭제하기</a>
            <% } %>
        </div>


    </div>

</body>
</html>