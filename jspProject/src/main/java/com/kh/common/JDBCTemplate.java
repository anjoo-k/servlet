package com.kh.common;
// jar 파일 넣어주시오

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// 이제 뭔가 가져올 때는 webapp 안에서 들어가야한다.
// 그 안에서 경로를 가져와야 나중에 실행가능
public class JDBCTemplate {
	
	// 1. Connection 객체 생성한 후 Connection 객체 반환
	
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		
		// 절대경로 루트는 classes 부터
		// 읽어들이고자 하는 classes 폴더 내의 driver.properties 파일의 물리적인 경로 가져오기
		String filePath = JDBCTemplate.class.getResource("/db/driver/driver.properties").getPath();
		// 물리경로를 코드로 가져오는 것
		
		try {
			prop.load(new FileInputStream(filePath));
			
			// jdbc driver 등록
			Class.forName(prop.getProperty("driver"));
			// 접속하고자하는 db의 url, 계정명, 비밀번호 제시해서 Connection 객체 생성
			conn = DriverManager.getConnection(prop.getProperty("url"),
										prop.getProperty("username"),
										prop.getProperty("password"));

			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// 2. Connection 객체 전달받아 commit 처리해주는 메소드
	public static void commit(Connection conn) {
			
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// 3. Connection 객체 전달받아 rollback 처리해주는 메소드
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4. Connection 객체 전달받아 close(반납) 처리해주는 메소드
		public static void close(Connection conn) {
			
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
}
		
		public static void close(Statement stmt) {
			
			try {
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
}
		
		public static void close(ResultSet rset) {
			
			try {
				if(rset != null && !rset.isClosed()) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
}
		
		
}
