package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.model.vo.Person;

/**
 * Servlet implementation class Elservlet
 */
@WebServlet("/el.do")
public class Elservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Elservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 데이터를 담을 수 있는 JSP 내장 객체의 종류
		 * 
		 * 1. ServletContext(application Scope)    내 컴퓨터 안에 생기는 객체
		 *    한 애플리케이션 당 1개 존재하는 객체
		 *    이 영역에 데이터를 담으면 애플리케이션 전역에서 사용 가능
		 *    공유 범위가 가장 큼
		 * 
		 * 2. HttpSession(session Scope)
		 * 	  한 브라우저당 1개 존재하는 객체   -> 브라우저: 크롬, 사파리, 웨일
		 * 	  이 영역에 데이터를 담으면 jsp/servlet 단에서 사용 가능
		 *  
		 * 3. HttpServletRequest
		 *    요청때 마다 매번 생성되는 객체
		 *    이 영역에 데이터를 담으면 해당 request 객체를 포워딩 받는 응답 jsp에서만 사용 가능
		 * 
		 * 4. PageContext(page Scope)
		 *    jsp 마다 존재하는 객체
		 *    공유범위가 가장 작음(해당페이지에서만 공유)
		 *    
		 *    
		 *    위 4개 객체의 데이터를 담을 때는 .setAttribute('키', 데이터)
		 *    데이터를 꺼낼 때 .getAttribute('키') (-> jsp 에서)
		 *    데이터 삭제할 때 .removeAttribute('키')    
		 */
		
		// request Scope에 담기
		request.setAttribute("classRoom", "I강의장");
		request.setAttribute("student", new Person("홍길동", 20, "남"));
		
		// session Scope에 담기
		HttpSession session = request.getSession();
		session.setAttribute("academy", "kh");
		session.setAttribute("teacher", new Person("김길동", 30, "여"));
		
		// 각 scope에 동일한 키로 데이터 담기
		request.setAttribute("scope", "request");
		session.setAttribute("scope", "session");
		
		// application Scope에 담기
		ServletContext application = request.getServletContext();
		application.setAttribute("scope", "application");
		
		// 응답페이지 지정해 해당 페이지가 포워딩되도록 설정
		request.getRequestDispatcher("views/1_EL/01_el.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
