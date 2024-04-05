package com.kh.common;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxTextController
 */
@WebServlet("/jqAjax.do")
public class AjaxTextController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxTextController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
//		response.getWriter().print(name);
//		response.getWriter().print(age);
		// [{k:v}]로 가지고 있는 경우가 많다.
		// 그래서 보낼 때 위처럼 보내면 k:v로 가지 않고 하나씩 가며, nameage 이렇게 값이 간다.
		// json에 보내줄 수 있는 라이브러리 필요
		
		// 여러 개를 응답하고 싶지만 할 수 없다다.
		/*
		 * JSON(자바스크립트에서 객체를 표기했던 방법, 실제로는 파일형식을 의미한다)
		 * ajax 통신 시 데이터전송에 가장 많이 사용되는 포맷 형식 중 하나
		 * 
		 * {key:value, key:value ... } => JSONObject
		 * [value, value, value... ] => JSONArray 
		 * */
		
//		JSONArray jArr = new JSONArray();
//		// 라이브러리 받았으니까 import 가능
//		jArr.add(name);
//		jArr.add(age);
//		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(jArr);
		
		//결과 [홍길동, 25]
		
		
		
//		JSONObject jobj = new JSONObject(); //{} : 키값 형태로 생긴 것
//		jobj.put("name", name); // {name : 홍길동}
//		jobj.put("age", age); // {name : 홍길동, age : 61} -> 요렇게 세팅됌
//		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(jobj);
		
		//결과 [{name : 홍길동, age : 25}]
	
		
		
//		하나씩 다 담아서 보낸 것. 번거롭고 귀찮다.
		ArrayList<Member> list = new ArrayList<>();
		list.add(new Member(1, "홍길동", "01011111111"));
		list.add(new Member(1, "김길동", "01022222222"));
		list.add(new Member(1, "박길동", "01033333333"));
		
//		JSONArray jArr = new JSONArray();
//		for(Member m : list) {
//			JSONObject jobj = new JSONObject();
//			jobj.put("userNo", m.getUserNo());
//			jobj.put("userName", m.getUserName());
//			jobj.put("phone", m.getPhone());
//			jArr.add(jobj);
//		}
		
		// 결과 [{"phone":"01011111111","userNo":1,"userName":"홍길동"},{"phone":"01022222222","userNo":1,"userName":"김길동"},{"phone":"01033333333","userNo":1,"userName":"박길동"}]
		
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(jArr);
		
		response.setContentType("text/html; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
		// Gson을 라이브러리를 받아서 코드를 만들면
		// 위의 for문 array 만드는 것을 대신해 준다.
		
//		[{
//			userName : "홍길동",
//			userNo : 1,
//			phone : 01011111111
//		},{
//			userName : "홍길동",
//			userNo : 1,
//			phone : 01011111111
//		},{
//			userName : "홍길동",
//			userNo : 1,
//			phone : 01011111111
//		 }
//		]
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
