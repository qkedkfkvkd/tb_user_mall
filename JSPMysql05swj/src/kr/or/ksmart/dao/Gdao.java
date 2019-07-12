package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kr.or.ksmart.driverdb.DriverDB;
import kr.or.ksmart.dto.Goods;
import kr.or.ksmart.dto.User;

public class Gdao {
	
	// 상품검색-조인쿼리(상품 테이블만)_기간별_
	public Map<String, Object> gSearchJoinDate(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   gSearchJoinDate()   Gdao.java");
			
			String sql = "SELECT u.u_id, u.u_name, "
					   + "g.g_code, g.g_name, g.g_cate, g.g_price, g.g_date "
					   + "FROM tb_user AS u INNER JOIN tb_goods AS g "
					   + "ON u.u_id = g.u_id "
					   + "AND DATE_FORMAT(g_date,'%Y-%m-%d') "
					   + "BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getDate_min());
			pstmt.setString(2, g.getDate_max());
			System.out.println(pstmt + "<-- pstmt   gSearchJoinDate()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchJoinDate()   Gdao.java");
			
			map = new HashMap<String, Object>();
			
			ArrayList<User> mlist = new ArrayList<>();
			ArrayList<Goods> glist = new ArrayList<>();
			
			while(rs.next()){
				User mem = new User();
				Goods goods = new Goods();
				
				mem.setU_id(rs.getString("u_id"));
				mem.setU_name(rs.getString("u_name"));
				
				goods.setG_code(rs.getString("g_code"));
				goods.setG_name(rs.getString("g_name"));
				goods.setG_cate(rs.getString("g_cate"));
				goods.setG_price(rs.getString("g_price"));
				goods.setG_date(rs.getString("g_date"));
				
				mlist.add(mem);
				glist.add(goods);
			}
			
			map.put("mlist", mlist);
			map.put("glist", glist);
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// 상품 리스트 전체 조회(관리자, 판매자 권한만)
	public ArrayList<Goods> goodsAllSelect() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> glist = null;
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   goodsAllSelect()   Gdao.java");
			
			String sql = "SELECT g_code, u_id, g_name, g_price, g_color, g_size from tb_goods";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   goodsAllSelect()   Gdao.java");
			
			glist = new ArrayList<>();
			
			while(rs.next()){
				Goods g = new Goods();
				
				g.setG_code(rs.getString("g_code"));
				g.setG_name(rs.getString("g_name"));
				g.setU_id(rs.getString("u_id"));
				g.setG_price(rs.getString("g_price"));
				g.setG_color(rs.getString("g_color"));
				g.setG_size(rs.getString("g_size"));
				
				glist.add(g);
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return glist;
	}
	
	
	// 상품 삭제 처리
	public void gDelete(String g_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   gDelete()   Gdao.java");
			
			pstmt = conn.prepareStatement("DELETE FROM tb_goods WHERE g_code=?");
			pstmt.setString(1, g_code);
			System.out.println(pstmt + " : pstmt   gDelete()   Gdao.java");
			
			int result = pstmt.executeUpdate();
			System.out.println(result + " : result   gDelete()   Gdao.java");
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	
	// 상품 수정 처리
	public void gUpdate(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   gUpdate()   Gdao.java");
			
			String sql = "UPDATE tb_goods SET " 
						+"g_name=?, g_cate=?, g_price=?, g_color=?, g_size=?, g_date=now(), g_desc=? WHERE g_code=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());
			pstmt.setString(2, g.getG_cate());
			pstmt.setString(3, g.getG_price());
			pstmt.setString(4, g.getG_color());
			pstmt.setString(5, g.getG_size());
			pstmt.setString(6, g.getG_desc());
			pstmt.setString(7, g.getG_code());
			System.out.println(pstmt + " : pstmt   gUpdate()   Gdao.java");
			
			int result = pstmt.executeUpdate();
			if(result != 0) {
				System.out.println("성공적으로 수정되었습니다.   gUpdate()   Gdao.java");
				System.out.println("성공적으로 수정된 행 갯수 : " + result + "   gUpdate()   Gdao.java");
			}
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	
	// 상품 상세보기
	public Goods gDetail(String g_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Goods goods = new Goods();
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   gDetail()   Gdao.java");
			
			pstmt = conn.prepareStatement("SELECT * FROM tb_goods WHERE g_code=?");
			pstmt.setString(1, g_code);
			System.out.println(pstmt + " : pstmt   gDetail()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " : rs   gDetail()   Gdao.java");
			
			if(rs.next()) {
				goods.setG_code(rs.getString("g_code"));
				goods.setU_id(rs.getString("u_id"));
				goods.setG_name(rs.getString("g_name"));
				goods.setG_cate(rs.getString("g_cate"));
				goods.setG_price(rs.getString("g_price"));
				goods.setG_color(rs.getString("g_color"));
				goods.setG_size(rs.getString("g_size"));
				goods.setG_date(rs.getString("g_date"));
				goods.setG_desc(rs.getString("g_desc"));
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return goods;
	}
	
	
	// 상품검색(상품 테이블만)_기간별_상품명_가격구간_가격 정렬
	public ArrayList<Goods> gSearchSort(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> goodlist = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   gSearchSort(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE g_name=? AND DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? "
					   + "AND g_price*1 BETWEEN ? AND ? "
					   + "ORDER BY g_price*1 " + g.getSort();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());
			pstmt.setString(2, g.getDate_min());
			pstmt.setString(3, g.getDate_max());
			pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));
			pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));
			System.out.println(pstmt + "<-- pstmt   gSearchSort(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchSort(Goods)   Gdao.java");
			
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
	
	
	// 상품검색(상품 테이블만)_기간별_상품명_가격구간
	public ArrayList<Goods> gSearchDateGnamePrice(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> goodlist = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			System.out.println(conn + "<-- conn   gSearchDateGnamePrice(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE g_name=? AND DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? "
					   + "AND g_price*1 BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());
			pstmt.setString(2, g.getDate_min());
			pstmt.setString(3, g.getDate_max());
			pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));
			pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));
			System.out.println(pstmt + "<-- pstmt   gSearchDateGnamePrice(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchDateGnamePrice(Goods)   Gdao.java");
			
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
			System.out.println(conn + "<-- conn   gSearchDate(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getDate_min());
			pstmt.setString(2, g.getDate_max());
			System.out.println(pstmt + "<-- pstmt   gSearchDate(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchDate(Goods)   Gdao.java");
			
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
