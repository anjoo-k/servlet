package com.kh.notice.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.kh.notice.model.vo.Notice;

import static com.kh.common.JDBCTemplate.*;

public class NoticeDao {
	
	private Properties prop = new Properties();
	// xml 파일을 prop에 로드시켜줌
	
	public NoticeDao() {
		// xml 파일 찝어서 가져온다~
		// classes 파일 밑에서 가져온다
		// 엥 이거 뭐지????????????????????????
		String filePath = NoticeDao.class.getResource("/db/sql/notice-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Notice> selectNoticeList(Connection conn){
		//select => ResultSet(여러행) => ArrayList<Notice>
		ArrayList<Notice> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoitceList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			// rset.next() => 다음 객체에 값이 있는지 없는지 알 수 있음/ 다음객체가 비어있을 때 까지 반복 추출
			
			while(rset.next()) {
				list.add(new Notice(
						rset.getInt("notice_no"),
						rset.getString("notice_title"),
						rset.getString("user_id"),
						rset.getInt("count"),
						rset.getDate("create_date")
						));
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;		
	}
	
	public int insertNotice(Connection conn, Notice n) {
		// insert -> 처리된 행 수 -> 트랙잭션 처리
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);// 미완성
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, Integer.parseInt(n.getNoticeWriter()));
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public int increaseCount(Connection conn, int noticeNo) {
		// update문(sql) -> 처리된 행 수 -> 트랜잭션 처리
		
		int result = 0;
		
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	public Notice selectNotice(Connection conn, int noticeNo){
		//select => ResultSet(한 행) => Notice
		Notice n= null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			// rset.next() => 다음 객체에 값이 있는지 없는지 알 수 있음/ 다음객체가 비어있을 때 까지 반복 추출
			
			if(rset.next()) {
				n = new Notice(
						rset.getInt("notice_no"),
						rset.getString("notice_title"),
						rset.getString("notice_content"),
						rset.getString("user_id"),
						rset.getDate("create_date")
						);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return n;		
	}
	
	
	public int updateNotice(Connection conn, Notice n){
		// update문 => 처리된 행 수 => 트랜잭션 처리 해야
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			// excuteUpdate : insert, update, delete
			// excuteQuery : select
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	public int deleteNotice(Connection conn, int noticeNo){
		// update문 => 처리된 행 수 => 트랜잭션 처리 해야
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			// excuteUpdate : insert, update, delete
			// excuteQuery : select
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
}
