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
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 데이터 추출해서 객체로 변환
		int noticeNo = Integer.parseInt(request.getParameter("num"));
				
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");

		
		Notice n = new Notice();
		n.setNoticeNo(noticeNo);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		
		// 실제 데이터베이스에서 update -> updateNotice()
		int result = new NoticeService().updateNotice(n);
		
		// 성공 시 -> /jsp/detail.no?num=
		// 실패 시 -> error페이지
		
		if(result > 0) {
			// 재요청(redirect)을 보내야 한다.
			// 왜? 지금 페이지 경로는 kh/update.no -> 수정요청페이지
			// 하지만 내가 다음으로 보고싶은 페이지는? => 상세페이지(kh/detail.no)
			// url 경로가 다르니까 재요청을 통해 화면과 url을 맞춰주자
			request.getSession().setAttribute("alertMsg", "공지사항 수정 완료");
//			request.setAttribute("alertMsg", "공지사항 수정 완료");
			response.sendRedirect(request.getContextPath() + "/list.no");
			// 응답을 내리는 것
			
			// 성공 시 -> /kh/derail.no?num=
		} else {
			// 실패 시에는 왜 request에 errorMsg를 담을까?
			// 에러페이지는 따로 url이 필요하지 않다.(왜? errorPage를 직접 찾아 들어갈 일이 없으니까.)
			// => 포워딩 => 포워딩 시에는 request를 전달할 수 있으니까
			request.setAttribute("errorMsg", "공지사항 수정 실패");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			// 요청받은걸 가지고 그 요청을 응답

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
