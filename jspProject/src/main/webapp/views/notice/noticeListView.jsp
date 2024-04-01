<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.notice.model.vo.Notice" %>

<%
    ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .outer{
        background: black;
        color: white;
        width: 1000px;
        height: 500px;
        margin: auto;
        margin-top: 50px;
    }
    .list-area{
        border: 1px solid white;
        text-align: center;
    }
    .list-area>tbody>tr:hover{
        background: gray;
        cursor: pointer;
    }
    
</style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>

    <div class="outer">
        <br>
        <h2 align="center">공지사항</h2>
        <br>

        <% if(loginUser != null && loginUser.getUserId().equals("admin")) { %>
            <div align="right" style="width:850px; margin-bottom: 4px;">
                <a href="<%=contextPath %>/enroll.no" class="btn-sm btn-secondary">글쓰기</a>
            </div>
        
        <% } %>


        <table class="list-area" align="center">
            <thead>
                <th>글번호</th>
                <th width="400">글제목</th>
                <th width="100">작성자</th>
                <th>조회수</th>
                <th width="100">작성일</th>
            </thead>
            <tbody>
                <% if(list.isEmpty()) { %>
                <!-- 공지사항이 없을 경우 -->
                    <tr>
                        <td colspan="5">존재하는 공지사항이 없습니다.</td>
                    </tr>
                <% } else %>
                <!-- 공지사항이 있을 경우 -->
                <% for(Notice n : list) { %>
                    <tr>
                        <td><%=n.getNoticeNo()%></td>
                        <td><%=n.getNoticeTitle()%></td>
                        <td><%=n.getNoticeWriter()%></td>
                        <td><%=n.getCount()%></td>
                        <td><%=n.getCreateDate()%></td>
                    </tr>
                <% } %>

            </tbody>
        </table>

    </div>
    <script>
        // const trList = document.querySelectorAll(".list-area > tbody > tr");
        // // [tr, tr,tr...]
        // for(const tr of trList){
        //     tr.onclick = function(ev){
        //         //url /kh/detail.no

        //         // onclick을 실행하는 요소가 this
        //         const noticeNo = this.childNodes[1].innerText;
        //         // .value는 input에만 쓰는 것
        //         // children쓰면 빈칸(띄워쓰기, 엔터 등) text들이 걸려져 나온다. 그래서 1로 시작
        //         // childNodes는 빈칸까지 배열칸 하나하나를 채운다.
        //         location.href = "<%=contextPath%>/detail.no?num=" + noticeNo;
        //         // 키값 ? url
        //     }
        // }

        // 제이쿼리 방식

        $(function(){
            $(".list-area > tbody > tr").click(function(){
                const noticeNo = $(this).children().eq(0).text();
                location.href = "<%=contextPath%>/detail.no?num=" + noticeNo;

                // num은 키값. name처럼 넘겨주는. 자바에서 받아줌
                // 제이쿼리는 eq로 받아온다
            })
        })
        // 제이쿼리가 무조건 함수..?
        // 스크립트, 제이쿼리 둘 다 알면 좋지만 스크립트부터 공부 ㄱ

    </script>



</body>
</html>