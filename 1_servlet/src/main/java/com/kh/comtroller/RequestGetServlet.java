package com.kh.comtroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 * 
 * Servlet 같은 경우 반드시 contextPath뒤에 작성되어야 한다.
 * http://localhost:8888/1_Servlet/test1.do
 */

// /test1.do가 서블릿 객체라는 것을 어노테이션이 알려준다.
//매핑:
//서블릿이 어떤 웹에서 요청을 받을지 주소를 연결해 주는 것.
//도메인 주소가 필요하다.
//파일이 아니니까.
//어노테이션으로 연결

// 컴파일러에 저장되어 있다가 이 경로로 요청이 왔을 때 받아줌
// 그리고 적절한 객체를 찾아서 꺼내줌. get요청이면 doGet, post 요청 오면 doPost 메서드 실행
@WebServlet("/test1.do")
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */										
//    					requeset 객체에는 요청자가 요청한 내용이 다 들어있다.		response 객체에는 내보낼 때 필요한 것들이 들어있다.
//    					어떤 프레임워크를 사용해도  request 객체를 사용한다		예를들면 스트림. 요청이 와서 돌려보낼 때 자바코드로부터 빼서 내보내 줘야함. 어디로 응답할 것인지
//    																	에러코드, 상태코드도 세팅되어 있고 리턴하는 등 응답하는 정보가 담겨있고 응답해준다 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get방식으로 요청 시 해당 doGet 메소드가 저절로 호출된다.
		// System.out.println("GET요청 정상적으로 받았습니다.");
		
		/**
		 * 첫번째 매개변수인 request에는 요청 시 전달된 내용들이 담겨있음(사용자가 입력한 값, 요청방식, 요청자 ip주소 등)
		 * 두번째 매개변수인 response는 요청 처리 후 응답할 때 사용되는 객체
		 * 
		 * 요청 처리를 위해서 요청 시 전달된 값들 추출
		 * request의 parameter 영역 안에 존재
		 * request.getParameter("키")
		 * 
		 * request의 parameter영역으로부터 전달된 데이터 추출하는 메소드 사용
		 * 
		 * 
		 */
		
		 String name = request.getParameter("name"); //"홍길동" | null(흰화면: 폼테그는 빈값을 보냄)
		 String gender = request.getParameter("gender"); //"M" | "W" | null : radio 태그의 결과
		 int age = Integer.parseInt(request.getParameter("age")); //"23"->23
//		 네트워크 헤드를 타고 넘어올 때 숫자도 다 문자로 바껴서 넘어온다. parse로 처리해줘야
		 String city = request.getParameter("city"); // "경기", "서울" 등등 중에 하나
		 double height = Double.parseDouble(request.getParameter("height")); //"170"->170.00000
		 
		 // 체크박스와 같이 복수의 벨류값들을 추출하고자 할 때 :getParameterValues 요걸로 써야 배열 받아줄 수 있다.
		 // getParameterValues는 리턴값도 배열이다.
		 String[] foods = request.getParameterValues("foods"); // ["한식","분식"] | null(흰화면)
		 
		 // 위처럼 html에서 가져왔으면 아래부터는 그냥 자바이다.
		 System.out.println("name : " + name);
		 System.out.println("gender : " + gender);
		 System.out.println("age : " + age);
		 System.out.println("city : " + city);
		 System.out.println("height : " + height);
//		 System.out.println("foods : " + String.join("/", foods));
		 
//		 서버쪽 코드를 바꿨으면 서버를 재시작 해줘야한다.
		 
		 if(foods == null) {
			 System.out.println("foods: 없음");
		 } else {
			 System.out.println("foods : " + String.join("/", foods));
		 }
		 
		 // 추출한 값들을 가지고 요청처리를 해야함 (db와 상호작용)
		 // >Service > Dao > DB sql로 실행
		 //									다오로 보내서 sql 받아와서 가져옴
		 /* int result = new MemberService().insertMember(name, gender, age, city, height, foods);
		 	if (result > 0) {
		 		// 성공 -> 성공페이지
		 	} else {
		 		// 실패 -> 실패페이지
		 	}
		 */
		 
		 // 위의 결과에 따라 응답페이지(html)만들어서 전송
		 // 즉, 여기 Java코드 내에 사용자가 보게될 응답 html 구문을 작성
		 
		 // 장점 : java코드 내에 작성하기 때문에 반복문이나 조건문, 유용한 메소드와 같은 것을 활용하여 동적인 응답을 내릴 수 있다.
		 // 단점 : 불편, 복잡하고 차후 html을 수정한다면 java 코드를 수정하는 것이기 때문에
		 // 	  수정된 내용을 다시 반영시키고자 서버를 재실행 해야 한다.    => 그래서 jsp가 나옴
		 
		 // response 객체를 통해 사용자에게 html(응답화면) 전달
		 // 1) 이제부터 내가 출력할 내용은 문서형태의 html이고 문자셋은 utf-8이다 -> 선언
		 response.setContentType("text/html; charset=utf-8");
		 
		 // 2) 응답하고자하는 사용자(요청했던 사람)와의 스트림(클라이언트와의 연결통로)을 생성
		 PrintWriter out = response.getWriter();
		 
		 // 3) 위에서 가져온 스트림을 통해서 응답html구문을 한줄씩 출력 : 기본적인 was 서버의 역할. 아래는 옛날방법
		 out.println("<html>");
		 
		 out.println("<head>");
		 out.println("<style>");
		 out.println("</style>");
		 
		 out.println("<body>");
		 out.println("<h2>개인정보응답화면</h2>");
		 out.printf("<span>%s</span>님은", name);
		 out.printf("<span>%d</span>살이며", age);
		 out.printf("<span>%s</span>에 삽니다.", city);
		 
		 out.println("성별은");
		 if(gender == null) {
			 out.println("선택하지 않았습니다.");
		 } else {
			 if(gender.equals("M")) {
				 out.println("<span>남자입니다.</span>");
			 }else {
				 out.println("<span>남자입니다.</span>");
			 }
		 }
		 
		 
		 out.println("</body>");
		 out.println("</head>");
		 
		 out.println("</html>");
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
