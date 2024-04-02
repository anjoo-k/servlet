<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    #enroll-form table{
        border: 3px solid rgb(62, 87, 61);
        padding: 5px;
    }
    #enroll-form input, #enroll-form textarea{
        width: 100%;
        box-sizing: border-box;
    }
</style>

</head>
<body>
    <%@ include file="../common/menubar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2 align="center">공지사항 작성하기</h2>
        <br>

        <form action="<%=contextPath %>/insert.no" id="enroll-form" method="POST">
        <input type="hidden" name="userNo" vlaue="<%=loginUser.getUserno() %>">
            <table>
                <tr>
                    <th width="50">제목</th>
                    <td width="450"><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="content" rows="10" style="resize:none;"></textarea>
                    </td>
                </tr>
            </table>
            <br><br>
            <div align="center">
                <button type="submit">등록하기</button>
                <button type="reset">초기화</button>
                <button type="button" onclick="history.back()">뒤로가기</button>
                <!-- 히스토리 객체가 우리의 정보를 가지고 있다.
                어디서? 돔에서??? 웹에서? 브라우저에서??
                
                이 history.back()은 브라우저단에서 뒤로가기임-->
            </div>
        </form>

    </div>

</body>
</html>