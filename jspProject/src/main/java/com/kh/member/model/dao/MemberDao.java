package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import static com.kh.common.JDBCTemplate.*;
// 현업에서는 *을 지양해야.

import com.kh.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		String filePath = MemberDao.class.getResource("/db/sql/member-mapper.xml").getPath();
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
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
		// select문 -> Member객체(한행) 조회 => ResultSet 객체
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql); // 미완성 sql
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery(); // 조회 결과가 있다면 한행 반환 | 없다면 반환x
			
			if(rset.next()) {
				m = new Member(
						rset.getInt("user_no"),
						rset.getString("user_id"),
						rset.getString("user_pwd"),
						rset.getString("user_name"),
						rset.getString("phone"),
						rset.getString("email"),
						rset.getString("address"),
						rset.getString("interest"),
						rset.getDate("enroll_date"),
						rset.getDate("modify_date"),
						rset.getString("status")
						);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public int insertMember(Connection conn, Member m) {
		// insert문 => 처리된 행 수 => 트랜잭션 처리
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

	}
	
	public int updateMember(Connection conn, Member m) {
		//update => 처리된 행 수 => 트랜잭션 처리
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql); //미완성된 sql
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Member selectMember(Connection conn, String userId) {
		// userId로 select한걸 날림
		// select -> resultSet에는 한행이 돌아올 것(userId 하나 날렸으니까) -> Member 객체 담아서
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");

		
		try {
			pstmt = conn.prepareStatement(sql); // 미완성 sql -> Member-mapper.xml로 보내
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(
						rset.getInt("user_no"),
						rset.getString("user_id"),
						rset.getString("user_pwd"),
						rset.getString("user_name"),
						rset.getString("phone"),
						rset.getString("email"),
						rset.getString("address"),
						rset.getString("interest"),
						rset.getDate("enroll_date"),
						rset.getDate("modify_date"),
						rset.getString("status")
						);
				

			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public int updatePwdMember(Connection conn, String userId, String userPwd, String updatePwd) {
		// update => 처리된 행 수 => 트랜잭션
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwdMember");		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteMember(Connection conn, String userId, String userPwd) {
		// update => 처리된 행 수 => 트랜잭션
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMember");		
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int idCheck(Connection conn, String checkId) {
		//select => ResultSet 한 행(몇개있냐? 니까) => int
		
		int count = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, checkId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return count;
		
	}
	

}
