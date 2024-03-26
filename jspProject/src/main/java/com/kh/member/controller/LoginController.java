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
@WebServlet("/Login.me")
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
		
		//4) 처리된 결과를 가지고 사용자가 보게될 응답뷰를 지정해서 포워딩 또는 url 재요청
		if(loginUser == null) {
			// 조회결과 없음 => 로그인 실패 내용 담아서 보여지는 에러 페이지(jsp)로 포워딩
			request.setAttribute("errorMsg", "로그인에 실패하였습니다.");
			
			// 응답페이지에게 위임
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
			view.forward(request, response);
			
		} else {
			// 조회 결과 있음 => 로그인 성공!!
			// 로그인 결과는 계속해서 가지고 있어야 함.
			// request는 request가 가는 그 부분 까지만 정보를 유지함
			// 그래서 로그인한 회원정보(loginUser)을 session 영역에 담기(왜? 여기저기서 다 가져다 쓸 수 있도록)
			// Servlet에서는 session 영역에 접근하고자 한다면 우선 세션객체를 얻어야한다.
			// request와 session 말고도 다른 영역들이 있다.
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			response.sendRedirect(request.getContextPath());
			
			//request랑 session이 어디에 위치한 영역인디..?
			
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
