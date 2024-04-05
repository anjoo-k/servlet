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
                    <td><button type="button" onclick="idCheck()">중복확인</button></td>
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
                <button type="submit" onclick="return checkPwd();" disabled>회원가입</button>
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
        
        function idCheck(){
            //onclick되면 사용자가 입력한 id 값을 서버에 보내서 조회를 요청하고 싶은 것(존재하는 아이디인가?)
            // => 서버에 요청
            // 1) 사용불가 : alert 메세지 출력(사용할 수 없는 아이디입니다.), 다시입력
            // 2) 사용가능 : 진짜 사용할것인지? ㅇㅇ -> 더 이상 아이디 수정하지 못하도록 막기, 회원가입 버튼 활성
            //                                ㄴㄴ -> 다시 입력하도록 유도                                          
            
            const idInput = document.querySelector("#enroll-form input[name=userId]");
            console.log(idInput.value)

            //idInput.value 값을 서버에 보내야 한다. ajax 사용
            // $표시는 제이쿼리의 변수, 제이쿼리 안쓰면 ajax 못씀
            // $.ajax()
            // 응답페이지 전체를 다시 받을 필요가 없다. 원하는 그 데이터 하나만 받으면 됌
            // 응답페이지 중 일부만 서버에 요청보내고 받을 때 사용
            /**
             * $.ajax({
             *      type : 전송타입 GET | POST,
             *      url : 어디로 요청을 보낼지
             *      data : {key : value} -> 어떤 데이터를 포함해서 보낼지
             *      success : function(){} -> 성공 시 실행해줄 함수
             *      err : function(){} -> 실패 시 실행해줄 함수
             * })
            */

            console.log("ajax 출발")
            
            $.ajax({
                type : "GET",
                url : "idCheck.me",
                data : {
                    checkId : idInput.value
                }, // key:value로 보낸다! 
                success: function(res){ // 리턴된 결과가 도착하면 여기로 받아서 결과 보여줌
                    console.log("ajax 응답도착")
                    console.log("성공 : ", res)
                    
                    if(res === "NNNNY"){
                    	if(confirm("사용가능한 아이디입니다. 사용하시겠습니까?")){
                    		//더 이상 아이디 수정하지 못하도록 막기, 회원가입 버튼 활성
                    		idInput.setAttribute("readonly", true); // 리드온리 기능 활성화
                    		const submitBtn = document.querySelector("#enroll-form button[type=submit]")
                    		submitBtn.removeAttribute("disabled");
                    	} else { // 아니라고 한 경우 아이디 입력창 포커싱
                    		idInput.focus();
                    	}
                    } else{
                        alert("사용불가한 아이디입니다.");
                        idInput.focus();
                    }

                },
                error: function(err){
                    console.log("실패 : ", err)

                }
            })            
            console.log("ajax 이후코드")
            
            

            // 비동기 형식 출발 출력>(서버로 요청 보내서 DB 기다림)> 이후코드출력 > 응답도착~~~ 부터 출력
        }


    </script>

	
	
</body>
</html>