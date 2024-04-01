package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.service.BoardService;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--------------------------------------------페이징 처리------------------------------------------------------
		int listCount; // 현재 총 게시글 수
		int currnetPage; //현재 페이지(사용자가 요청한 페이지 = 사용자가 몇페이지인지 준다(프론트에서 보냄))
		int pageLimit; // 페이지 하단의 페이징 바 갯수
		int boardLimit; // 한 페이지 내에 보여질 게시글 최대 갯수
		// 위 4개의 값을 기준으로 아래 3개의 값을 구해야함
		
		int maxPage; // 가장 마지막 페이지(총 페이지의 수)
		int startPage; // 페이징바의 시작수
		int endPage; // 페이징바의 마지막 끝 수
		
		//* listCount : 총 게시글 수
		// 변경을 해줘야하면 service에서 처리해 줘야하나 걍 마 select해서 가져오는 거니까
		// 컨트롤러든 서비스든 어디든 있어도 상관 없다.
		listCount = new BoardService().selectListCount();
		
		//* currentPage : 현재 페이지(사용자가 요청한 페이지)
		currnetPage = Integer.parseInt(request.getParameter("cpage"));
		
		//* pageLimit : 페이징바의 최대 갯수
		pageLimit = 10;
		
		//* boardLimit 한 페이지내에 보여질 게시글 최대 갯수: 
		boardLimit = 10;
		
		/**
		 * * maxPage : 제일 마지막 페이지 수(총 페이지 수)
		 * 
		 * 총 게시글 갯수 / boardLimit => 올림처리
		 * 
		 */
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		/**
		 * * startPage : 페이징바 시작수 
		 * 
		 * startPage = ((currnetPage - 1) / pageLimit) * pageLimit + 1;
		 * 
		 * */
		
		startPage = ((currnetPage - 1) / pageLimit) * pageLimit + 1;
		
		/**
		 * endPage : 페이징바의 끝수
		 * 
		 * pageLimit : 10이라는 가정 하에
		 * 
		 * startPage: 1 => endPage : 10
		 * startPage: 11 => endPage : 20
		 * startPage: 21 => endPage : 30
		 * */
		
		endPage = startPage + pageLimit - 1;
		
		// startPage가 11이면 endPage는 20이다(만약 maxPage가 13이라면?)
		endPage = endPage > maxPage ? maxPage : endPage;
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
