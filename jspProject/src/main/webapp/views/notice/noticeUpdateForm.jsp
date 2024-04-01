<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.notice.model.vo.Notice" %>
<% 
    Notice n = (Notice)request.getAttribute("notice");
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
   #update-form table{
       border: 3px solid rgb(62, 87, 61);
       padding: 5px;
   }
   #update-form input, #update-form textarea{
       width: 100%;
       box-sizing: border-box;
   }
</style>

</head>
<body>
   <%@ include file="../common/menubar.jsp" %>

   <div class="outer" align="center">
       <br>
       <h2 align="center">공지사항 수정하기</h2>
       <br>

       <form action="<%=contextPath %>/update.no" id="update-form" method="POST">
           <input type="hidden" name="num" value="<%=n.getNoticeNo() %>">
           <!-- notice number을 보내주는 거니까 사용자에게 굳이 보여줄 필요 없음 -->
           <table>
               <tr>
                   <th width="50">제목</th>
                   <td width="450"><input type="text" name="title" required value="<%=n.getNoticeTitle()%>"></td>
               </tr>
               <tr>
                   <td>내용</td>
                   <td></td>
               </tr>
               <tr>
                   <td colspan="2">
                       <textarea name="content" rows="10" style="resize:none;"><%=n.getNoticeContent()%></textarea>
                   </td>
               </tr>
           </table>
           <br><br>
           <div align="center">
               <button type="submit">수정하기</button>
               <button type="button" onclick="history.back()">뒤로가기</button>
               <!-- 히스토리 객체가 우리의 정보를 가지고 있다.
               어디서? 돔에서??? 웹에서? 브라우저에서??
               
               이 history.back()은 브라우저단에서 뒤로가기임-->
           </div>
       </form>

   </div>

</body>
</html>