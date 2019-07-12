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
	
	// 상품검색-조인쿼리(상품 테이블만)_기간별_상품명_가격구간_가격 정렬
		public Map<String, Object> gSearchJoinSort(Goods g) throws ClassNotFoundException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Map<String, Object> map = null;
			
			try{
				DriverDB db = new DriverDB();
				conn = db.driverDbcon();
				// 커넥션 객체를 얻어온다.
				System.out.println(conn + "<-- conn   gSearchJoinSort()   Gdao.java");
				
				String sql = "SELECT u.u_id, u.u_name, "
						   + "g.g_code, g.g_name, g.g_cate, g.g_price, g.g_date "
						   + "FROM tb_user AS u INNER JOIN tb_goods AS g "
						   + "ON u.u_id = g.u_id "
						   + "AND g.g_name = ? "
						   + "AND DATE_FORMAT(g_date,'%Y-%m-%d') "
						   + "BETWEEN ? AND ? "
						   + "and g.g_price*1 BETWEEN ? AND ? "
						   + "ORDER BY g_price*1 " + g.getSort();
				// 회원 테이블에서 회원 아이디와 회원 이름을 가져오고
				// 상품 테이블에서 상품 코드, 상품 이름, 카테고리, 가격, 등록일을 가져온다.
				// 회원 테이블의 별명을 u로 하고 상품 테이블의 별명을 g로 하여
				// 회원테이블의 회원 아이디와 상품 테이블의 판매자 아이디가 같을 조건으로 inner join을 수행한다.
				// 특정 상품 이름을 넣어주고 등록일을 날짜 포맷하여 기간 구간을 넣어준다.
				// 가격 컬럼이 varchar 이므로 sql 수행하면서 숫자로 변환될 수 있게 *1을 하여 가격 구간을 넣어주고
				// 가격 컬럼으로 ASC, DESC (오름차순, 내림차순) 정렬을 수행한다.
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, g.getG_name());		// 상품 이름
				pstmt.setString(2, g.getDate_min());	// 최소 등록일 부터
				pstmt.setString(3, g.getDate_max());	// 최대 등록일 까지
				pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));	// 최소 가격대 부터
				pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));	// 최대 가격대 까지
				System.out.println(pstmt + "<-- pstmt   gSearchJoinSort()   Gdao.java");
				
				rs = pstmt.executeQuery();
				System.out.println(rs + "<-- rs   gSearchJoinSort()   Gdao.java");
				
				map = new HashMap<String, Object>();
				
				ArrayList<User> mlist = new ArrayList<>();		// 회원을 저장할 회원 리스트
				ArrayList<Goods> glist = new ArrayList<>();		// 상품을 저장할 상품 리스트
				
				while(rs.next()){
					User mem = new User();		// 반복문의 시작지점부터 객체를 선언하고 추가하는 것을 반복한다.
					Goods goods = new Goods();
					
					mem.setU_id(rs.getString("u_id"));			// 회원 아이디
					mem.setU_name(rs.getString("u_name"));		// 회원 이름
					
					goods.setG_code(rs.getString("g_code"));	// 상품 코드
					goods.setG_name(rs.getString("g_name"));	// 상품 이름
					goods.setG_cate(rs.getString("g_cate"));	// 카테고리
					goods.setG_price(rs.getString("g_price"));	// 가격
					goods.setG_date(rs.getString("g_date"));	// 등록일
					
					mlist.add(mem);		// 회원 객체 하나씩 추가
					glist.add(goods);	// 상품 객체 하나씩 추가
				}
				
				map.put("mlist", mlist);	// 회원 리스트 객체를 "mlist" 키값으로 넣어준다.
				map.put("glist", glist);	// 상품 리스트 객체를 "glist" 키값으로 넣어준다.
				
			} catch(SQLException ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return map;
		}
	
	
	// 상품검색-조인쿼리(상품 테이블만)_기간별_상품명_가격구간
	public Map<String, Object> gSearchJoinDateGnamePrice(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gSearchJoinDateGnamePrice()   Gdao.java");
			
			String sql = "SELECT u.u_id, u.u_name, "
					   + "g.g_code, g.g_name, g.g_cate, g.g_price, g.g_date "
					   + "FROM tb_user AS u INNER JOIN tb_goods AS g "
					   + "ON u.u_id = g.u_id "
					   + "AND g.g_name = ? "
					   + "AND DATE_FORMAT(g_date,'%Y-%m-%d') "
					   + "BETWEEN ? AND ? "
					   + "and g.g_price*1 BETWEEN ? AND ?";
			// 회원 테이블에서 회원 아이디와 회원 이름을 가져오고
			// 상품 테이블에서 상품 코드, 상품 이름, 카테고리, 가격, 등록일을 가져온다.
			// 회원 테이블의 별명을 u로 하고 상품 테이블의 별명을 g로 하여
			// 회원테이블의 회원 아이디와 상품 테이블의 판매자 아이디가 같을 조건으로 inner join을 수행한다.
			// 특정 상품 이름을 넣어주고 등록일을 날짜 포맷하여 기간 구간을 넣어준다.
			// 가격 컬럼이 varchar 이므로 sql 수행하면서 숫자로 변환될 수 있게 *1을 하여 가격 구간을 넣어준다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// 상품 명
			pstmt.setString(2, g.getDate_min());	// 최소 등록일 부터
			pstmt.setString(3, g.getDate_max());	// 최대 등록일 까지
			pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));	// 최소 가격대 부터
			pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));	// 최대 가격대 까지
			System.out.println(pstmt + "<-- pstmt   gSearchJoinDateGnamePrice()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchJoinDateGnamePrice()   Gdao.java");
			
			map = new HashMap<String, Object>();
			
			ArrayList<User> mlist = new ArrayList<>();		// 회원을 저장할 회원 리스트
			ArrayList<Goods> glist = new ArrayList<>();		// 상품을 저장할 상품 리스트
			
			while(rs.next()){
				User mem = new User();		// 반복문의 시작지점부터 객체를 선언하고 추가하는 것을 반복한다.
				Goods goods = new Goods();
				
				mem.setU_id(rs.getString("u_id"));			// 회원 아이디
				mem.setU_name(rs.getString("u_name"));		// 회원 이름
				
				goods.setG_code(rs.getString("g_code"));	// 상품 코드
				goods.setG_name(rs.getString("g_name"));	// 상품 이름
				goods.setG_cate(rs.getString("g_cate"));	// 카테고리
				goods.setG_price(rs.getString("g_price"));	// 가격
				goods.setG_date(rs.getString("g_date"));	// 등록일
				
				mlist.add(mem);		// 회원 객체 하나씩 추가
				glist.add(goods);	// 상품 객체 하나씩 추가
			}
			
			map.put("mlist", mlist);	// 회원 리스트 객체를 "mlist" 키값으로 넣어준다.
			map.put("glist", glist);	// 상품 리스트 객체를 "glist" 키값으로 넣어준다.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// 상품검색-조인쿼리(상품 테이블만)_기간별_상품명
	public Map<String, Object> gSearchJoinDateGname(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gSearchJoinDateGname()   Gdao.java");
			
			String sql = "SELECT u.u_id, u.u_name, "
					   + "g.g_code, g.g_name, g.g_cate, g.g_price, g.g_date "
					   + "FROM tb_user AS u INNER JOIN tb_goods AS g "
					   + "ON u.u_id = g.u_id "
					   + "AND g.g_name = ? "
					   + "AND DATE_FORMAT(g_date,'%Y-%m-%d') "
					   + "BETWEEN ? AND ?";
			// 회원 테이블에서 회원 아이디와 회원 이름을 가져오고
			// 상품 테이블에서 상품 코드, 상품 이름, 카테고리, 가격, 등록일을 가져온다.
			// 회원 테이블의 별명을 u로 하고 상품 테이블의 별명을 g로 하여
			// 회원테이블의 회원 아이디와 상품 테이블의 판매자 아이디가 같을 조건으로 inner join을 수행한다.
			// 특정 상품 이름을 넣어주고 등록일을 날짜 포맷하여 기간 구간을 넣어준다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// 상품 이름
			pstmt.setString(2, g.getDate_min());	// 최소 등록일 부터
			pstmt.setString(3, g.getDate_max());	// 최대 등록일 까지
			System.out.println(pstmt + "<-- pstmt   gSearchJoinDateGname()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchJoinDateGname()   Gdao.java");
			
			map = new HashMap<String, Object>();
			
			ArrayList<User> mlist = new ArrayList<>();		// 회원 정보를 저장할 회원 리스트
			ArrayList<Goods> glist = new ArrayList<>();		// 상품 정보를 저장할 상품 리스트
			
			while(rs.next()){
				User mem = new User();		// 반복문의 시작지점부터 객체를 선언하고 추가하는 것을 반복한다.
				Goods goods = new Goods();
				
				mem.setU_id(rs.getString("u_id"));				// 회원 아이디
				mem.setU_name(rs.getString("u_name"));			// 회원 이름
				
				goods.setG_code(rs.getString("g_code"));		// 상품 코드
				goods.setG_name(rs.getString("g_name"));		// 상품 명
				goods.setG_cate(rs.getString("g_cate"));		// 카테고리
				goods.setG_price(rs.getString("g_price"));		// 가격
				goods.setG_date(rs.getString("g_date"));		// 등록일
				
				mlist.add(mem);		// 회원 객체를 하나씩 리스트에 추가
				glist.add(goods);	// 상품 객체를 하나씩 리스트에 추가
			}
			
			map.put("mlist", mlist);	// 회원 리스트 객체를 "mlist" 키값으로 넣어준다.
			map.put("glist", glist);	// 상품 리스트 객체를 "glist" 키값으로 넣어준다.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// 상품검색-조인쿼리(상품 테이블만)_기간별_
	public Map<String, Object> gSearchJoinDate(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gSearchJoinDate()   Gdao.java");
			
			String sql = "SELECT u.u_id, u.u_name, "
					   + "g.g_code, g.g_name, g.g_cate, g.g_price, g.g_date "
					   + "FROM tb_user AS u INNER JOIN tb_goods AS g "
					   + "ON u.u_id = g.u_id "
					   + "AND DATE_FORMAT(g_date,'%Y-%m-%d') "
					   + "BETWEEN ? AND ?";
			// 회원 테이블에서 회원 아이디와 회원 이름을 가져오고
			// 상품 테이블에서 상품 코드, 상품 이름, 카테고리, 가격, 등록일을 가져온다.
			// 회원 테이블의 별명을 u로 하고 상품 테이블의 별명을 g로 하여
			// 회원테이블의 회원 아이디와 상품 테이블의 판매자 아이디가 같을 조건으로 inner join을 수행한다.
			// 등록일을 날짜 포맷하여 기간 구간을 넣어준다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getDate_min());	// 최소 등록일 부터
			pstmt.setString(2, g.getDate_max());	// 최대 등록일 까지
			System.out.println(pstmt + "<-- pstmt   gSearchJoinDate()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchJoinDate()   Gdao.java");
			
			map = new HashMap<String, Object>();
			
			ArrayList<User> mlist = new ArrayList<>();		// 회원 정보를 저장할 회원 리스트
			ArrayList<Goods> glist = new ArrayList<>();		// 상품 정보를 저장할 상품 리스트
			
			while(rs.next()){
				User mem = new User();			// 반복문의 시작지점부터 객체를 선언하고 추가하는 것을 반복한다.
				Goods goods = new Goods();		
				
				mem.setU_id(rs.getString("u_id"));				// 회원 아이디
				mem.setU_name(rs.getString("u_name"));			// 회원 이름
				
				goods.setG_code(rs.getString("g_code"));		// 상품 코드
				goods.setG_name(rs.getString("g_name"));		// 상품 이름
				goods.setG_cate(rs.getString("g_cate"));		// 카테고리
				goods.setG_price(rs.getString("g_price"));		// 가격
				goods.setG_date(rs.getString("g_date"));		// 등록일
				
				mlist.add(mem);		// 회원 객체를 하나씩 리스트에 추가
				glist.add(goods);	// 상품 객체를 하나씩 리스트에 추기
			}
			
			map.put("mlist", mlist);	// 회원 리스트 객체를 "mlist" 키값으로 넣어준다.
			map.put("glist", glist);	// 상품 리스트 객체를 "glist" 키값으로 넣어준다.
			
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
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   goodsAllSelect()   Gdao.java");
			
			String sql = "SELECT g_code, u_id, g_name, g_price, g_color, g_size from tb_goods";
			// 상품 테이블에서 상품 코드, 판매자 아이디, 상품명, 가격, 색상, 사이즈를 구해온다.
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   goodsAllSelect()   Gdao.java");
			
			glist = new ArrayList<>();
			
			while(rs.next()){
				Goods g = new Goods();		// 반복문의 시작지점부터 객체를 선언하고 추가하는 것을 반복한다.
				
				g.setG_code(rs.getString("g_code"));		// 상품 코드
				g.setG_name(rs.getString("g_name"));		// 상품 이름
				g.setU_id(rs.getString("u_id"));			// 판매자 아이디
				g.setG_price(rs.getString("g_price"));		// 가격
				g.setG_color(rs.getString("g_color"));		// 색상
				g.setG_size(rs.getString("g_size"));		// 사이즈
				
				glist.add(g);	// 상품 객체를 하나씩 리스트에 추가한다.
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
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gDelete()   Gdao.java");
			
			pstmt = conn.prepareStatement("DELETE FROM tb_goods WHERE g_code=?");
			// 상품 테이블의 기본키인 상품 코드로 특정한 상품을 삭제한다.
			
			pstmt.setString(1, g_code);	// 상품 코드 삽입
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
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gUpdate()   Gdao.java");
			
			String sql = "UPDATE tb_goods SET " 
						+"g_name=?, g_cate=?, g_price=?, g_color=?, g_size=?, g_date=now(), g_desc=? WHERE g_code=?";
			// 상품 코드와 일치하는 레코드의 상품 이름, 카테고리, 가격, 색상, 사이즈, 날짜는 현재날짜로, 상세설명을 수정한다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// 상품 이름
			pstmt.setString(2, g.getG_cate());		// 카테고리
			pstmt.setString(3, g.getG_price());		// 가격
			pstmt.setString(4, g.getG_color());		// 색상
			pstmt.setString(5, g.getG_size());		// 사이즈
			pstmt.setString(6, g.getG_desc());		// 상세 설명
			pstmt.setString(7, g.getG_code());		// 상품 코드
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
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gDetail()   Gdao.java");
			
			pstmt = conn.prepareStatement("SELECT * FROM tb_goods WHERE g_code=?");
			// 상품 코드와 일치하는 레코드를 구해온다.
			pstmt.setString(1, g_code);	// 상품 코드 삽입
			System.out.println(pstmt + " : pstmt   gDetail()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " : rs   gDetail()   Gdao.java");
			
			if(rs.next()) {
				goods.setG_code(rs.getString("g_code"));		// 상품 코드
				goods.setU_id(rs.getString("u_id"));			// 판매자 아이디
				goods.setG_name(rs.getString("g_name"));		// 상품 이름
				goods.setG_cate(rs.getString("g_cate"));		// 카테고리
				goods.setG_price(rs.getString("g_price"));		// 가격
				goods.setG_color(rs.getString("g_color"));		// 색상
				goods.setG_size(rs.getString("g_size"));		// 사이즈
				goods.setG_date(rs.getString("g_date"));		// 등록일
				goods.setG_desc(rs.getString("g_desc"));		// 상세 설명
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
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gSearchSort(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE g_name=? AND DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? "
					   + "AND g_price*1 BETWEEN ? AND ? "
					   + "ORDER BY g_price*1 " + g.getSort();
			// 상품 테이블에서 상품 코드, 상품 이름, 카테고리, 색상, 등록 날짜를 조회한다.
			// 특정 상품의 이름과 일치하고 기간 구간 내에 등록을 했으며
			// 가격 컬럼이 varchar 이므로 *1을 하여 SQL에서 숫자로 처리하도록 하여 특정 가격 구간 내에 있는 상품을 조회한다.
			// 가격을 오름차순이나 내림차순으로 정렬한다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// 상품 이름
			pstmt.setString(2, g.getDate_min());	// 최소 등록일 부터
			pstmt.setString(3, g.getDate_max());	// 최대 등록일 까지
			pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));	// 최소 가격대 부터
			pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));	// 최대 가격대 까지
			System.out.println(pstmt + "<-- pstmt   gSearchSort(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchSort(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();			// 반복문의 시작지점부터 객체를 선언하고 추가하는 것을 반복한다.
				goods.setG_code(rs.getString("g_code"));		// 상품 코드
				goods.setG_name(rs.getString("g_name"));		// 상품 이름
				goods.setG_cate(rs.getString("g_cate"));		// 카테고리
				goods.setG_price(rs.getString("g_price"));		// 가격
				goods.setG_date(rs.getString("g_date"));		// 등록일자
				
				goodlist.add(goods);	// 상품 객체를 하나씩 리스트에 추가
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
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gSearchDateGnamePrice(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE g_name=? AND DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? "
					   + "AND g_price*1 BETWEEN ? AND ?";
			// 상품 코드, 상품 이름, 카테고리, 가격, 등록일자를 상품 테이블로 부터 조회한다.
			// 특정 상품 이름과 일치하고 등록일자의 구간을 정하여 그 기간 내에 등록된 상품이어야 한다.
			// 가격 컬럼이 varchar 이므로 *1을 하여 숫자로 처리되게 하여 가격 구간을 정하여 조회한다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// 상품 이름
			pstmt.setString(2, g.getDate_min());	// 최소 등록일 부터
			pstmt.setString(3, g.getDate_max());	// 최대 등록일 까지
			pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));	// 최소 가격대 부터
			pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));	// 최대 가격대 까지
			System.out.println(pstmt + "<-- pstmt   gSearchDateGnamePrice(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchDateGnamePrice(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();		// 반복문의 시작지점부터 객체를 선언하고 추가하는 것을 반복한다.
				goods.setG_code(rs.getString("g_code"));		// 상품 코드
				goods.setG_name(rs.getString("g_name"));		// 상품 이름
				goods.setG_cate(rs.getString("g_cate"));		// 카테고리
				goods.setG_price(rs.getString("g_price"));		// 가격
				goods.setG_date(rs.getString("g_date"));		// 등록일자
				
				goodlist.add(goods);	// 상품 객체를 하나씩 리스트에 추가한다.
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
			// 커넥션 객체를 구해온다.
			System.out.println(conn + "<-- conn   gSearchDateGname(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE g_name=? AND DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? ";
			// 상품 코드, 상품 이름, 카테고리, 가격, 등록일자를 상품 테이블에서 조회한다.
			// 특정 상품 이름과 일치해야 하며 입력한 등록일자 사이의 상품을 조회한다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// 상품 이름
			pstmt.setString(2, g.getDate_min());	// 최소 등록일 부터
			pstmt.setString(3, g.getDate_max());	// 최대 등록일 까지
			System.out.println(pstmt + "<-- pstmt   gSearchDateGname(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchDateGname(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();		// 반복문의 시작지점부터 객체를 선언하고 추가하는 것을 반복한다.
				goods.setG_code(rs.getString("g_code"));		// 상품 코드
				goods.setG_name(rs.getString("g_name"));		// 상품 이름
				goods.setG_cate(rs.getString("g_cate"));		// 카테고리
				goods.setG_price(rs.getString("g_price"));		// 가격
				goods.setG_date(rs.getString("g_date"));		// 등록일자
				
				goodlist.add(goods);	// 상품 객체를 하나씩 리스트에 추가한다.
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
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gSearchDate(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? ";
			// 상품 테이블에서 상품 코드, 상품 이름, 카테고리, 가격, 등록일자를 조회한다.
			// 입력한 등록일자 내에 있는 상품들을 조회한다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getDate_min());		// 최소 등록일 부터
			pstmt.setString(2, g.getDate_max());		// 최대 등록일 까지
			System.out.println(pstmt + "<-- pstmt   gSearchDate(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchDate(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();			// 반복문의 시작지점부터 객체를 선언하고 추가하는 것을 반복한다.
				goods.setG_code(rs.getString("g_code"));		// 상품 코드
				goods.setG_name(rs.getString("g_name"));		// 상품 이름
				goods.setG_cate(rs.getString("g_cate"));		// 카테고리
				goods.setG_price(rs.getString("g_price"));		// 가격
				goods.setG_date(rs.getString("g_date"));		// 등록일자
				
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
	public void gInsert(Goods g) throws ClassNotFoundException {
		// 1,2 단계 드라이버 로딩, 디비 연결부터 시작
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// 커넥션 객체를 얻어온다.
			System.out.println(conn + "<-- conn   gInsert(Goods)   Gdao.java");
			
			String sql = "SELECT DISTINCT SUBSTR(g_code,1,6) AS temp_code FROM tb_goods";
			// 상품 테이블에서 상품 코드 부분에서 똑같이 반복되는 goods_ 부분을 중복을 제거하여 temp_code 별명으로 조회한다.
			// goods_1, goods_2, goods_3 ...
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + " <- pstmt   gInsert(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " <- rs   gInsert(Goods)   Gdao.java");
			
			rs.next();
			String temp_g_code = "";
			if(rs.next()) {
				temp_g_code = rs.getString("temp_code"); // 별명인 temp_code으로 반복되는 문자열을 추출한다.
			}
			System.out.println(temp_g_code + " <- temp_g_code   gInsert(Goods)   Gdao.java");
			
			rs.close();
			pstmt.close();
			// 한번 더 사용해야하므로 닫아준다.
			
			pstmt = conn.prepareStatement("SELECT MAX(SUBSTR(g_code,7)) AS max_code FROM tb_goods");
			// 상품 코드에서 맨 뒷부분의 숫자 부분만 추출하여 최대 값을 max_code 별명으로 얻어온다.
			
			System.out.println(pstmt + " <- pstmt   gInsert(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " <- rs   gInsert(Goods)   Gdao.java");
			
			String temp_max = "";
			if(rs.next()) {
				temp_max = rs.getString("max_code"); // 별명을 이용하여 최대 값을 추출한다.
			}
			System.out.println(temp_max + " <- temp_max   gInsert(Goods)   Gdao.java");
			
			int max_code = Integer.parseInt(temp_max);	// 문자열로된 숫자를 정수형으로 변환한다.
			System.out.println(max_code + " <- max_code   gInsert(Goods)   Gdao.java");
			
			max_code++;		// 최대 값에서 1을 더한 값으로 추가해야 하므로 증감 연산을 수행한다.
			System.out.println(max_code + " <- max_code++   gInsert(Goods)   Gdao.java");
			
			String g_code = temp_g_code+max_code; // 반복되는 코드인 "gcode_" 부분과 최대 숫자값을 더한다.
			System.out.println(g_code + " <- g_code   gInsert(Goods)   Gdao.java");
			
			
			rs.close();
			pstmt.close();
			// 한번 더 사용해야하므로 닫아준다.
			
			pstmt = conn.prepareStatement("INSERT INTO tb_goods VALUES(?, ?, ?, ?, ?, ?, ?, NOW(), ?)");
			pstmt.setString(1, g_code);				// 최대 숫자값이 들어있는 상품 코드(기본키)
			pstmt.setString(2, g.getU_id());		// 판매자 아이디
			pstmt.setString(3, g.getG_name());		// 상품 이름
			pstmt.setString(4, g.getG_cate());		// 카테고리
			pstmt.setString(5, g.getG_price());		// 가격
			pstmt.setString(6, g.getG_color());		// 색상
			pstmt.setString(7, g.getG_size());		// 사이즈
			pstmt.setString(8, g.getG_desc());		// 상세 설명
			System.out.println(pstmt + " <- pstmt   gInsert(Goods)   Gdao.java");
			
			int result = pstmt.executeUpdate();
			System.out.println(result + " <- result   gInsert(Goods)   Gdao.java");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
}
