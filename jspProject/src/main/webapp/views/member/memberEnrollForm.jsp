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

    <div class="outer">
        <br>
        <h2 align="center">회원가입</h2>
        <form action="<%=contextPath%>/insert.me" id="enroll-form" method="POST">
            <!-- 위에 들어간 메뉴바에 있는 include 그대로 쓸 수 있어서 contextPath 바로 사용 가능한 것 -->
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" maxlength="12" required></td>
                    <td><button type="button">중복확인</button></td>
                    <!-- 폼태그 안이기 때문에 타입 버튼으로!!! 안주면 서브밋이 될 수도 있다. -->
                </tr>
                <tr>
                    <td>* 비밀번호</td>
                    <td><input type="password" name="userPwd" maxlength="15" required></td>
                </tr>
                <tr>
                    <td>* 비밀번호 확인</td>
                    <td><input type="password" name="userPwdCheck" maxlength="15" required></td>
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="6" required></td>
                </tr>

                <tr>
                    <td>전화번호</td>
                    <td><input type="text" name="phone" placeholder="- 포함해서 입력"></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input type="email" name="email"></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><input type="text" name="address"></td>
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

            <br><br>

            <div align="center">
                <button type="submit" onclick="return checkPwd();">회원가입</button>
                <button type="reset">초기화</button>
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
	
	
</body>
</html>