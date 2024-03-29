package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.vo.Notice;
import com.kh.notice.service.NoticeService;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		

		// view 단에서 - 타이틀이랑 내용이 넘어오는데,
		// 공지사항을 누가 작성했냐도 있잖어? -> 로그인에 있다.
		
		// 데이터추출 -> 객체로 만들어서 서비스로 전달
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");
		
		// 로그인한 회원정보 얻어내는 방법
		// 1. input type = "hidden"으로 처음부터 요청 시 숨겨서 정달하는 방법
		// 2. session 영역에서 저장되어있는 회원객체로부터 얻어오는 방법
		HttpSession session = request.getSession();
		
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		
		
		Notice n = new Notice();
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		n.setNoticeWriter(String.valueOf(userNo));
		// insertNotice()
		
		int result = new NoticeService().insertNotice(n);
		
		
		if(result == 0) {
			request.setAttribute("errorMsg", "공지사항 등록 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			
		} else {
			session.setAttribute("alertMsg", "공지사항 등록 완료");
			// 등록하기 위한거니까 등록이 끝나고 어떤화면을 보여줄 건지
			// 공지사항 목록을 보여줘야, 다시 list 페이지로 돌려보내줘야한다.
			// 다른페이지로 보내줄꺼니까 Redirect 쓰면 된다.
			
			response.sendRedirect(request.getContextPath() + "/list.no");
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
