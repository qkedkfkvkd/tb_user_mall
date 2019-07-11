package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.ksmart.driverdb.DriverDB;
import kr.or.ksmart.dto.Goods;

public class Gdao {
	
	// 상품 등록 하기
	public void gInsert(Goods g) {
		// 1,2 단계 드라이버 로딩, 디비 연결부터 시작
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   gInsert(Goods)   Gdao.java");
			
			pstmt = conn.prepareStatement("SELECT DISTINCT SUBSTR(g_code,1,6) AS temp_code FROM tb_goods");
			System.out.println(pstmt + " <- pstmt   gInsert(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " <- rs   gInsert(Goods)   Gdao.java");
			
			rs.next();
			String temp_g_code = "";
			if(rs.next()) {
				temp_g_code = rs.getString("temp_code");
			}
			System.out.println(temp_g_code + " <- temp_g_code   gInsert(Goods)   Gdao.java");
			
			
			try {rs.close();} catch(SQLException e) {}
			try {pstmt.close();} catch(SQLException e) {}
			
			
			pstmt = conn.prepareStatement("SELECT MAX(SUBSTR(g_code,7)) AS max_code FROM tb_goods");
			System.out.println(pstmt + " <- pstmt   gInsert(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " <- rs   gInsert(Goods)   Gdao.java");
			
			String temp_max = "";
			if(rs.next()) {
				temp_max = rs.getString("max_code");
			}
			System.out.println(temp_max + " <- temp_max   gInsert(Goods)   Gdao.java");
			
			int max_code = Integer.parseInt(temp_max);
			System.out.println(max_code + " <- max_code   gInsert(Goods)   Gdao.java");
			
			max_code++;
			System.out.println(max_code + " <- max_code++   gInsert(Goods)   Gdao.java");
			
			String g_code = temp_g_code+max_code;
			System.out.println(g_code + " <- g_code   gInsert(Goods)   Gdao.java");
			
			
			try {rs.close();} catch(SQLException e) {}
			try {pstmt.close();} catch(SQLException e) {}
			
			
			pstmt = conn.prepareStatement("INSERT INTO tb_goods VALUES(?, ?, ?, ?, ?, ?, ?, NOW(), ?)");
			pstmt.setString(1, g_code);
			pstmt.setString(2, g.getU_id());
			pstmt.setString(3, g.getG_name());
			pstmt.setString(4, g.getG_cate());
			pstmt.setString(5, g.getG_price());
			pstmt.setString(6, g.getG_color());
			pstmt.setString(7, g.getG_size());
			pstmt.setString(8, g.getG_desc());
			System.out.println(pstmt + " <- pstmt   gInsert(Goods)   Gdao.java");
			
			int result = pstmt.executeUpdate();
			System.out.println(result + " <- result   gInsert(Goods)   Gdao.java");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
}
