package com.kh.comtroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 호출 완료");
		// POST 방식 요청같은 경우는
		// 데이터를 추출하기 [전!!]에 인코딩 설정을 해야함
		request.setCharacterEncoding("UTF-8");
		
		
		// 요청 시 전달된 값들은 request의 parameter영역에 담겨있다.
		 String name = request.getParameter("name"); //"홍길동" | null(흰화면: 폼테그는 빈값을 보냄)
		 String gender = request.getParameter("gender"); //"M" | "W" | null : radio 태그의 결과
		 int age = Integer.parseInt(request.getParameter("age")); //"23"->23
		 String city = request.getParameter("city"); // "경기", "서울" 등등 중에 하나
		 double height = Double.parseDouble(request.getParameter("height")); //"170"->170.00000
		 
		 // 체크박스와 같이 복수의 벨류값들을 추출하고자 할 때 :getParameterValues 요걸로 써야 배열 받아줄 수 있다.
		 String[] foods = request.getParameterValues("foods"); // ["한식","분식"] | null(흰화면)
		 
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
				 
				 /* int result = new MemberService().insertMember(name, gender, age, city, height, foods);
				 	if (result > 0) {
				 		// 성공 -> 성공페이지
				 	} else {
				 		// 실패 -> 실패페이지
				 	}
				 */
		 
		 // 요청처리가 다 되었다는 가정하에 사용자가 보게될 응답html 작성
		 
		 // 순수 Servlet 방식: java 코드 내에 html 기술
		 // JSP(Java Server Page) 자바서버에서 만드는 페이지, 자바에서 html 만들어서 프론트한테 준다!
		 // html 내에 java 코드 쓸 수 있음
		 
		 // 응답페이지를 만드는 과정을 JSP에게 위임
		 // 단, 응답화면(jsp)에서 필요로하는 데이터들을 차곡차곡 담아서 전달해줘야한다.
		 // 데이터를 담기 위한 공간 == request의 attribute영역
		 // request.setAttribute("키",벨류);  -> 여기 담아줘야 jsp가 쓸 수 있다.
		 
		 request.setAttribute("name", name);
		 request.setAttribute("age", age);
		 request.setAttribute("city", city);
		 request.setAttribute("height", height);
		 request.setAttribute("gender", gender);
		 request.setAttribute("foods", foods);
		 
		 // 응답하고자하는 뷰(jsp) 선택해서 넘겨줌 => RequestDispatcher 생성
		 RequestDispatcher view = request.getRequestDispatcher("/views/responsePage.jsp");
		 // 아래코드: view의 요청을 위임하겠다 -> 들어가보면 html 파일과 동일. 다른점, 내부에서 자바를 쓸 수 있다.
		 view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post 호출 완료");
		doGet(request, response);
//		결국 doGet을 가져와서 실행하는 것을 알 수 있다.
		
	}

}
