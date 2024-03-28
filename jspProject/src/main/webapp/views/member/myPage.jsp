<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        margin: auto;
        margin-top: 50px;
        padding: 10px 0 50px 0;
    }
    table{
        margin: auto;
    }
</style>
</head>

<body>
	<%@ include file="../common/menubar.jsp" %>
	<%
		String userId = loginUser.getUserId();
		String userName = loginUser.getUserName();
		
		String phone = loginUser.getPhone() == null ? "" : loginUser.getPhone();
		String email = loginUser.getEmail() == null ? "" : loginUser.getEmail();
		String address = loginUser.getAddress() == null ? "" : loginUser.getAddress();
		String interest = loginUser.getInterest() == null ? "" : loginUser.getInterest();
	
	%>


    <div class="outer">
        <br>
        <h2 align="center">회원가입</h2>
        <form action="<%=contextPath%>/update.me" id="enroll-form" method="POST">
            <!-- 위에 들어간 메뉴바에 있는 include 그대로 쓸 수 있어서 contextPath 바로 사용 가능한 것 -->
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" maxlength="12" value="<%=userId %>" readonly></td>
                    <td></td>
                    <!-- 폼태그 안이기 때문에 타입 버튼으로!!! 안주면 서브밋이 될 수도 있다. -->
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="6" value="<%=userName %>" required></td>
                </tr>

                <tr>
                    <td>전화번호</td>
                    <td><input type="text" name="phone" value="<%=phone %>" placeholder="- 포함해서 입력"></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="email" name="email" value="<%=email %>"></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><input type="text" name="address" value="<%=address %>"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>관심분야</td>
                    <td colspan="2">
                        <input type="checkbox" name="interest" id="movie" value="영화">
                        <label for="sports">영화</label>
                        <input type="checkbox" name="interest" id="sports" value="운동">
                        <label for="sports">운동</label>
                        <input type="checkbox" name="interest" id="hiking" value="등산">
                        <label for="sports">등산</label>
                        <br>
                        <input type="checkbox" name="interest" id="game" value="게임">
                        <label for="sports">게임</label>
                        <input type="checkbox" name="interest" id="fishing" value="낚시">
                        <label for="sports">낚시</label>
                        <input type="checkbox" name="interest" id="cooking" value="요리">
                        <label for="sports">요리</label>
                        <input type="checkbox" name="interest" id="etc" value="기타">
                        <label for="sports">기타</label>
                    </td>
                </tr>
            </table>
            <script>
                const interest = "<%=interest%>"; // 운동, 낚시, 영화... String으로 들어옴
                const inputArr = document.querySelectorAll("input[name=interest]");
                //[<input>, <input>, <input>, <input>....]


                for(let input of inputArr){ // 가져온 checkbox 요소들을 전부 반복한다.
                    if(interest.includes(input.value)){ // interest에 input.value의 값이 포함되어 있다면 true 
                        input.checked = ture;
                    }
                }
            </script>

            <br><br>

            <div align="center">
                <button type="submit" class="btn btn-sm btn-secondary">정보변경</button>
                <button class="btn btn-sm" data-toggle="modal" data-target="#update-password-modal">비밀변호변경</button>
                <button class="btn btn-sm btn-danger">회원탈퇴</button>
            </div>
        </form>
    </div>
    <script>
        function checkPwd(){
            const userPwd = document.querySelector("#enroll-form input[name=userPwd]").value;
            const userPwdCheck = document.querySelector("#enroll-form input[name=userPwdCheck]").value;

            if(userPwd !== userPwdCheck){
                alert("비밀번호가 일치하지 않습니다.")
                return false;
            }
        }


    </script>
    

	
	
    <!-- 비밀번호 변경 Modal -->
	<div class="modal" id="update-pwd-modal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">비밀번호 변경</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	        <form action="<%=contextPath%>/updatePwd.me" method="POST">
                <input type="hidden" name="userId" value="<%=userId%>">
                <table>
                    <tr>
                        <td>현재 비밀번호</td>
                        <td><input type="password" name="userPwd" required></td>
                    </tr>
                    <tr>
                        <td>변경할 비밀번호</td>
                        <td><input type="password" name="updatePwd" required></td>
                    </tr>
                    <tr>
                        <td>변경할 비밀번호 확인</td>
                        <td><input type="password" name="checkPwd" required></td>
                    </tr>
                </table>
                <br>
                <button id="edit-pwd-btn" type="submit" class="btn btn-sm btn-secondary">비밀번호 변경</button>

            </form>

          <script>
            document.getElementById("edit-pwd-btn").onclick = function() {
                const updatePwd = document.querySelector("input[name=updatePwd]").value;
                const checkPwd = document.querySelector("input[name=checkPwd]").value;

                if(updatePwd != checkPwd){
                    alert("비밀번호를 확인해주세요.")
                    return flase;
                }


                // if($("input[name=updatePwd]").val() !== $("input[name=checkPwd]").val()){
                //    제이쿼리로 가져오기
                // }

            }
          </script>
	
	     
		      </div>
	    </div>
	  </div>
	</div>
	
</body>
</html>