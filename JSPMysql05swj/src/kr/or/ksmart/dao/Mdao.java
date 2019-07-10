package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.ksmart.driverdb.DriverDB;
import kr.or.ksmart.dto.User;

public class Mdao {
	
	// 한명 회원 조회 메서드 선언(수정화면)
	public User mSelectforUpdate(String m_id) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User mem = new User();
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   mSelectforUpdate()   Mdao.java");
			
			pstmt = conn.prepareStatement("SELECT * FROM tb_user WHERE u_id=?");
			pstmt.setString(1, m_id);
			System.out.println(pstmt + " : pstmt   mSelectforUpdate()   Mdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " : rs   mSelectforUpdate()   Mdao.java");
			
			if(rs.next()) {
				mem.setU_id(rs.getString("u_id"));
				mem.setU_pw(rs.getString("u_pw"));
				mem.setU_level(rs.getString("u_level"));
				mem.setU_name(rs.getString("u_name"));
				mem.setU_email(rs.getString("u_email"));
				mem.setU_phone(rs.getString("u_phone"));
				mem.setU_addr(rs.getString("u_addr"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e){}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e){}
			if(conn != null) try {conn.close();} catch(SQLException e){}
		}
		return mem;
	}
	
	
	public void mInsert(User m, Connection conn) throws ClassNotFoundException {
		// 3단계 쿼리 준비부터 시작
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement("insert into tb_user values(?, ?, ?, ?, ?, ?, ?)");
			
			System.out.println(pstmt + "<-- pstmt 1");
			
			pstmt.setString(1, m.getU_id());
			pstmt.setString(2, m.getU_pw());
			pstmt.setString(3, m.getU_level());
			
			System.out.println(pstmt + "<-- pstmt 2");
			
			pstmt.setString(4, m.getU_name());
			pstmt.setString(5, m.getU_email());
			pstmt.setString(6, m.getU_phone());
			pstmt.setString(7, m.getU_addr());
			
			System.out.println(pstmt + "<-- pstmt 3");
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
	}
	
	public void mInsert(User m) throws ClassNotFoundException {
		// 1,2 단계 드라이버 로딩, 디비 연결부터 시작
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn");
			
			pstmt = conn.prepareStatement("insert into tb_user values(?, ?, ?, ?, ?, ?, ?)");
			
			System.out.println(pstmt + "<-- pstmt 1");
			
			pstmt.setString(1, m.getU_id());
			pstmt.setString(2, m.getU_pw());
			pstmt.setString(3, m.getU_level());
			
			System.out.println(pstmt + "<-- pstmt 2");
			
			pstmt.setString(4, m.getU_name());
			pstmt.setString(5, m.getU_email());
			pstmt.setString(6, m.getU_phone());
			pstmt.setString(7, m.getU_addr());
			
			System.out.println(pstmt + "<-- pstmt 3");
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
	}
	
	public void mUpdate(User m) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + " <- conn   user_update_pro.jsp");
			
			pstmt = conn.prepareStatement("UPDATE tb_user SET u_pw=?, u_level=?, u_name=?, u_email=?, u_phone=?, u_addr=? WHERE u_id=?");
			pstmt.setString(1, m.getU_pw());
			pstmt.setString(2, m.getU_level());
			pstmt.setString(3, m.getU_name());
			pstmt.setString(4, m.getU_email());
			pstmt.setString(5, m.getU_phone());
			pstmt.setString(6, m.getU_addr());
			pstmt.setString(7, m.getU_id());
			
			System.out.println(pstmt + " <- pstmt   user_update_pro.jsp");
			
			int result = pstmt.executeUpdate();
			System.out.println(result + " <- result   user_update_pro.jsp");
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
	}
	
	public void mDelete(String mid) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + " <- conn   user_delete_pro.jsp");
			
			pstmt = conn.prepareStatement("DELETE FROM tb_user WHERE u_id=?");
			pstmt.setString(1, mid);
			System.out.println(pstmt + " <- pstmt   user_delete_pro.jsp");
			
			int result = pstmt.executeUpdate();
			System.out.println(result + " <- result   user_delete_pro.jsp");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
	}
}
