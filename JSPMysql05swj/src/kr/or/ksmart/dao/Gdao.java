package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.ksmart.driverdb.DriverDB;
import kr.or.ksmart.dto.Goods;

public class Gdao {
	
	// 상품검색(상품 테이블만)_기간별_상품명
	public ArrayList<Goods> gSearchDateGname(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> goodlist = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   gSearchDateGname(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE g_name=? AND DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());
			pstmt.setString(2, g.getDate_min());
			pstmt.setString(3, g.getDate_max());
			System.out.println(pstmt + "<-- pstmt   gSearchDateGname(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchDateGname(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();
				goods.setG_code(rs.getString("g_code"));
				goods.setG_name(rs.getString("g_name"));
				goods.setG_cate(rs.getString("g_cate"));
				goods.setG_price(rs.getString("g_price"));
				goods.setG_date(rs.getString("g_date"));
				
				goodlist.add(goods);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return goodlist;
	}
	
	// 상품검색(상품 테이블만)_기간별
	public ArrayList<Goods> gSearchDate(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> goodlist = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   mSearchDate(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getDate_min());
			pstmt.setString(2, g.getDate_max());
			System.out.println(pstmt + "<-- pstmt   mSearchDate(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   mSearchDate(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();
				goods.setG_code(rs.getString("g_code"));
				goods.setG_name(rs.getString("g_name"));
				goods.setG_cate(rs.getString("g_cate"));
				goods.setG_price(rs.getString("g_price"));
				goods.setG_date(rs.getString("g_date"));
				
				goodlist.add(goods);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return goodlist;
	}
	
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
