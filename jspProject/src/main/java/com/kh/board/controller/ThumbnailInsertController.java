package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024*1024*10;
			
			String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail_upfile/");
			
			// MultipartRequest: 읽을 수 없는 파일 인코딩 & 파일을 자동으로 업로드
			// 이 때 전달된 파일 업로드(request 변환)
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			Board b = new Board();
			b.setBoardWriter(multiRequest.getParameter("userNo"));
			b.setBoardTitle(multiRequest.getParameter("title"));
			b.setBoardContent(multiRequest.getParameter("content"));
			
			// 첨부파일 사진 담아줌
			ArrayList<Attachment> list = new ArrayList<>();
			for(int i = 1; i <= 4; i++ ) {
				String key = "file" + i;
				if(multiRequest.getOriginalFileName(key) != null) {
					// 첨부파일이 존재할 경우
					// Attachment 생성 -> 원본명, 수정된 파일명, 폴더경로, 파일레벨 => list 담기
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("resources/thumbnail_upfile/");
					
					at.setFileLevel(i == 1? 1 : 2);
					
					list.add(at);
										
				}
			}
			
			int result = new BoardService().insertThumbnailBoard(b, list);
			
			// 응답뷰요청
			if(result > 0) { // 성공 -> /kh/list.th
				request.getSession().setAttribute("alertMsg", "사진 게시글 등록 성공");
				response.sendRedirect(request.getContextPath() + "/list.th");
			} else {
				request.setAttribute("errorPage", "사진 게시글 등록 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp");
			}

			
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
