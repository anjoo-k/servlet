package com.kh.member.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

//스테틱일때 가능
import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	public Member loginMember(String userId, String userPwd) {

		Connection conn = getConnection();
		Member m = new MemberDao().loginMember(conn, userId, userPwd);

		close(conn);

		return m;
	}

	
	public int insertMember(Member m) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().insertMember(conn, m);

		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}


	public Member updateMember(Member m) {
		
		Connection conn = getConnection();
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) {
			commit(conn);
			// 수정이 되었으면 넣었다고 그대로 쓰면 안돼고
			// db에서 다시 가져와 주어야 db 수정 완인 것을 확정할 수 있다.
			
			//갱신된 회원 객체 다시 조회해오기
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
			
		} else {
			rollback(conn);
		}
		
		close(conn);
		return updateMem;
		
	}


	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		
		Connection conn = getConnection();
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		Member updateMem = null;
		
		if(result > 0) {
			
			commit(conn);
			updateMem = new MemberDao().selectMember(conn, userId);
			
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
		
	}
	
	public int deleteMember(String userId, String userPwd) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if(result > 0) {
			commit(conn);
			
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
	}
	
	public int idCheck(String checkId) {

		Connection conn = getConnection();
		int count = new MemberDao().idCheck(conn, checkId);
		
		close(conn);
		return count;
		
	}
	
	
	
	
	
}
