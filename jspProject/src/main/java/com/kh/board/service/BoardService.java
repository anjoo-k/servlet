package com.kh.board.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.board.model.dao.BoardDao;

public class BoardService {
	
	public int selectListCount() {
		Connection conn = getConnection();
		int listCount = new BoardDao().selectListCount(conn);
		close(conn);
		return listCount;
	}

}
