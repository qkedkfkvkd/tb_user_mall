package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.ksmart.driverdb.DriverDB;
import kr.or.ksmart.dto.User;

public class Mdao {
	
	// 로그인 성공 후 세션을 얻기 위해서 이름과 권한을 구한다.
	public User mGetForSession(String in_id) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   mGetForSession()   Mdao.java");
			
			String sql = "SELECT u_name, u_level FROM tb_user WHERE u_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, in_id);
			System.out.println(pstmt + "<-- pstmt   mGetForSession()   Mdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   mGetForSession()   Mdao.java");
			
			user = new User();
			
			if(rs.next()) {
				user.setU_name(rs.getString("u_name"));
				user.setU_level(rs.getString("u_level"));
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return user;
	}
	
	// 로그인 체크 메서드
	public String mLoginCheck(String in_id,String in_pw) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   mLoginCheck()   Mdao.java");
			
			String sql = "SELECT u_pw, u_name, u_level FROM tb_user WHERE u_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, in_id);
			System.out.println(pstmt + "<-- pstmt   mLoginCheck()   Mdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   mLoginCheck()   Mdao.java");
			
			if(rs.next()){
				System.out.println("01_01 아이디 일치");
				
				String dbpw = rs.getString("u_pw");
				
				System.out.println(dbpw + "<-- dbpw   mLoginCheck()   Mdao.java");
				
				if(dbpw.equals(in_pw)){
					System.out.println("02_01 로그인 성공");
					
					result="로그인성공";
				}else{
					System.out.println("02_02 비번 불일치");
					result="비번 불일치";
				}
			}else{
				System.out.println("01_02 아이디 불일치");
				result="아이디 불일치";
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return result;
	}
	
	// 회원 검색 리스트 조회
	public ArrayList<User> mSearch(String sk,String sv) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> memlist = null;
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   mSearch(String, String)   Mdao.java");
			
			if(sk == null & sv == null) {
				System.out.println("01   sk == null & sv == null");
				pstmt = conn.prepareStatement("SELECT * FROM tb_user");
				
			} else if(sk != null & sv.equals("")) {
				System.out.println("02   sk != null & sv.equals()");
				pstmt = conn.prepareStatement("SELECT * FROM tb_user");
				
			} else if (sk != null & sv != null) {
				System.out.println("03   sk != null & sv != null");
				pstmt = conn.prepareStatement("select * from tb_member where "+sk+"=?");
				pstmt.setString(1, sv);
			}
			
			System.out.println(pstmt + "<-- pstmt   mSearch(String, String)   Mdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   mSearch(String, String)   Mdao.java");
			
			memlist = new ArrayList<>();
			
			while(rs.next()){
				User user = new User();
				user.setU_id(rs.getString("u_id"));
				user.setU_pw(rs.getString("u_pw"));
				user.setU_level(rs.getString("u_level"));
				user.setU_name(rs.getString("u_name"));
				user.setU_email(rs.getString("u_email"));
				user.setU_phone(rs.getString("u_phone"));
				user.setU_addr(rs.getString("u_addr"));
				
				memlist.add(user);
			}
		
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return memlist;
	}
	
	// 전체 회원 조회
	public ArrayList<User> mAllSelect() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> memlist = null;
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   mAllSelect()   Mdao.java");
			
			memlist = new ArrayList<User>();
			
			pstmt = conn.prepareStatement("select * from tb_user");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				User mem = new User();
				mem.setU_id(rs.getString("u_id"));
				mem.setU_pw(rs.getString("u_pw"));
				mem.setU_level(rs.getString("u_level"));
				mem.setU_name(rs.getString("u_name"));
				mem.setU_email(rs.getString("u_email"));
				mem.setU_phone(rs.getString("u_phone"));
				mem.setU_addr(rs.getString("u_addr"));
				memlist.add(mem);
				System.out.println(mem + " <- memlist   mAllSelect()   Mdao.java");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e){}
			if(pstmt != null) try {pstmt.close();} catch(SQLException e){}
			if(conn != null) try {conn.close();} catch(SQLException e){}
		}
		return memlist;
	}
	
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
	
	// 회원 추가하기
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
	
	// 회원 정보 수정하기
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
	
	// 회원 삭제하기
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
