package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.member.service.MemberService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("로그인 정보 도착");
		
		//1) 전달값에 한글이 있을 경우 인코딩 처리(POST일 경우, get일 경우에 해도 상관없으므로 일단 해준다)
		request.setCharacterEncoding("UTF-8");
		
		//2) 요청 시 전달값 추출해서 변수 또는 객체에 기록하기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		//3) 요청하기(db에 sql문 활용해서 조회해줘)
		// service 클래스에 담당 메서드 호출
		Member loginUser = new MemberService().loginMember(userId, userPwd);
		
		/**
		 *  응답페이지에 전달할 값이 있을 경우 어딘가에는 담아야한다.(담을 수 있는 영역)
		 *  1) application : 여기에 담긴 데이터는 웹 애플리케니션 전역에서 꺼내서 사용 가능
		 *  2) session : 여기에 담긴 데이터는 직접 지우기 전까지, 세션이 만료(서버가 멈춤, 브라우저 종료)되기 전까지
		 *  			 어떤 jsp든, 어떤 servlet이건 꺼내서 사용할 수 있다.
		 *  			 백만명 접속하면 세션 백만개. 서버에 좋지않다.
		 *  3) request : 해당 영역에 담긴 데이터는 현재 이 request 객체를 "포워딩한 응답 jsp에서만" 꺼내서 사용 가능
		 *  4) page : 해당 jsp에서 담고 그 jsp에서만 사용 가능
		 *  
		 *  공통적으로 데이터를 담으려 한다면 담을객체.setAttribute("키", 벨류)
		 *  		데이터를 꺼내려 한다면 꺼낼객체.getAttribute("키")
		 *  		데이터를 지우려 한다면 지울객체.removeAttribute("키")
		 * */
		
		//4) 처리된 결과를 가지고 사용자가 보게될 응답뷰를 지정해서 포워딩 또는 url 재요청
		if(loginUser == null) {
			// 조회결과 없음 => 로그인 실패 내용 담아서 보여지는 에러 페이지(jsp)로 포워딩
			request.setAttribute("errorMsg", "로그인에 실패하였습니다.");
			
						
			// 응답페이지에게 위임
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			// forward로 너가 이거 처리해줘~
			view.forward(request, response);
			
			// 여기서는 왜 리다이렉트 안해줌?
			// 실패, 성공 둘 다 login.me 페이지인데 실패는 굳이 돌아갈 필요 없으니까~ 그냥 둠
			
		} else {
			// 조회 결과 있음 => 로그인 성공!!
			// 로그인 결과는 계속해서 가지고 있어야 함.
			// request는 request가 가는 그 부분 까지만 정보를 유지함
			// request는 요청(request)한거 끝나면 소멸
			// 그래서 로그인한 회원정보(loginUser)을 session 영역에 담기(왜? 여기저기서 다 가져다 쓸 수 있도록)
			// Servlet에서는 session 영역에 접근하고자 한다면 우선 세션객체를 얻어야한다.
			// request와 session 말고도 다른 영역들이 있다.

			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			
			// 1. 포워딩 방식 => 해당 방식은 url이 변경되지 않는다. => 우리는 localhost:8001/kh/라는 메인 url이 있음
			// 해당 방식으로 화면 응답 시 url 은 http://localhost:8001/kh/login.me가 나타난다.
			// 실제 화면은 localhost:8001/kh/의 응답화면이 나타난다.
			// login.me는 화면을 요청하기 위해 쓰는 거지 화면을 보여주기 위한 것은 아니다.
			// Redirect로 .getContextPath()이 담아놓은 메인페이지로 다시 보낸다.
			// 주소 바로 안쓰고 항상 꺼내서 사용해 줘야
			
			// 에러페이지는 따로 url이 필요없다. 원본 url이 그대로 있어야
			// 에러페이지 처리하면 메인 페이지가 아래처럼 두개가 됨
			// localhost:8001/kh/login.me
			// localhost:8001/kh/
			// 그래서 login.me url 못쓰게? 없애게?
			// 원본 페이지로 다시 돌려 보내줘야함 그래서 sendRedirect를 하는 것
			// 재요청을 하는 것. 너가 그 페이지 요청하면 내가 그 주소 다시 줄게
			
			
			// /kh/login.me 페이지가 존재는 하는데 그 페이지로 가면 저절로 /kh 페이지로 보내버리는 것? 
			
			
			// 2. url 재요청 방식
			// 기존에 해당 페이지를 응답하는 url이 존재한다면 사용 가능
			// 응답페이지 => index.jsp (jsp url 재요청)
			response.sendRedirect(request.getContextPath());
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
