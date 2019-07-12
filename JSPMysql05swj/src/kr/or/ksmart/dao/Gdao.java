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
	
	// ��ǰ�˻�-��������(��ǰ ���̺�)_�Ⱓ��_��ǰ��_���ݱ���_���� ����
		public Map<String, Object> gSearchJoinSort(Goods g) throws ClassNotFoundException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Map<String, Object> map = null;
			
			try{
				DriverDB db = new DriverDB();
				conn = db.driverDbcon();
				// Ŀ�ؼ� ��ü�� ���´�.
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
				// ȸ�� ���̺��� ȸ�� ���̵�� ȸ�� �̸��� ��������
				// ��ǰ ���̺��� ��ǰ �ڵ�, ��ǰ �̸�, ī�װ�, ����, ������� �����´�.
				// ȸ�� ���̺��� ������ u�� �ϰ� ��ǰ ���̺��� ������ g�� �Ͽ�
				// ȸ�����̺��� ȸ�� ���̵�� ��ǰ ���̺��� �Ǹ��� ���̵� ���� �������� inner join�� �����Ѵ�.
				// Ư�� ��ǰ �̸��� �־��ְ� ������� ��¥ �����Ͽ� �Ⱓ ������ �־��ش�.
				// ���� �÷��� varchar �̹Ƿ� sql �����ϸ鼭 ���ڷ� ��ȯ�� �� �ְ� *1�� �Ͽ� ���� ������ �־��ְ�
				// ���� �÷����� ASC, DESC (��������, ��������) ������ �����Ѵ�.
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, g.getG_name());		// ��ǰ �̸�
				pstmt.setString(2, g.getDate_min());	// �ּ� ����� ����
				pstmt.setString(3, g.getDate_max());	// �ִ� ����� ����
				pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));	// �ּ� ���ݴ� ����
				pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));	// �ִ� ���ݴ� ����
				System.out.println(pstmt + "<-- pstmt   gSearchJoinSort()   Gdao.java");
				
				rs = pstmt.executeQuery();
				System.out.println(rs + "<-- rs   gSearchJoinSort()   Gdao.java");
				
				map = new HashMap<String, Object>();
				
				ArrayList<User> mlist = new ArrayList<>();		// ȸ���� ������ ȸ�� ����Ʈ
				ArrayList<Goods> glist = new ArrayList<>();		// ��ǰ�� ������ ��ǰ ����Ʈ
				
				while(rs.next()){
					User mem = new User();		// �ݺ����� ������������ ��ü�� �����ϰ� �߰��ϴ� ���� �ݺ��Ѵ�.
					Goods goods = new Goods();
					
					mem.setU_id(rs.getString("u_id"));			// ȸ�� ���̵�
					mem.setU_name(rs.getString("u_name"));		// ȸ�� �̸�
					
					goods.setG_code(rs.getString("g_code"));	// ��ǰ �ڵ�
					goods.setG_name(rs.getString("g_name"));	// ��ǰ �̸�
					goods.setG_cate(rs.getString("g_cate"));	// ī�װ�
					goods.setG_price(rs.getString("g_price"));	// ����
					goods.setG_date(rs.getString("g_date"));	// �����
					
					mlist.add(mem);		// ȸ�� ��ü �ϳ��� �߰�
					glist.add(goods);	// ��ǰ ��ü �ϳ��� �߰�
				}
				
				map.put("mlist", mlist);	// ȸ�� ����Ʈ ��ü�� "mlist" Ű������ �־��ش�.
				map.put("glist", glist);	// ��ǰ ����Ʈ ��ü�� "glist" Ű������ �־��ش�.
				
			} catch(SQLException ex) {
				ex.printStackTrace();
			} finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return map;
		}
	
	
	// ��ǰ�˻�-��������(��ǰ ���̺�)_�Ⱓ��_��ǰ��_���ݱ���
	public Map<String, Object> gSearchJoinDateGnamePrice(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gSearchJoinDateGnamePrice()   Gdao.java");
			
			String sql = "SELECT u.u_id, u.u_name, "
					   + "g.g_code, g.g_name, g.g_cate, g.g_price, g.g_date "
					   + "FROM tb_user AS u INNER JOIN tb_goods AS g "
					   + "ON u.u_id = g.u_id "
					   + "AND g.g_name = ? "
					   + "AND DATE_FORMAT(g_date,'%Y-%m-%d') "
					   + "BETWEEN ? AND ? "
					   + "and g.g_price*1 BETWEEN ? AND ?";
			// ȸ�� ���̺��� ȸ�� ���̵�� ȸ�� �̸��� ��������
			// ��ǰ ���̺��� ��ǰ �ڵ�, ��ǰ �̸�, ī�װ�, ����, ������� �����´�.
			// ȸ�� ���̺��� ������ u�� �ϰ� ��ǰ ���̺��� ������ g�� �Ͽ�
			// ȸ�����̺��� ȸ�� ���̵�� ��ǰ ���̺��� �Ǹ��� ���̵� ���� �������� inner join�� �����Ѵ�.
			// Ư�� ��ǰ �̸��� �־��ְ� ������� ��¥ �����Ͽ� �Ⱓ ������ �־��ش�.
			// ���� �÷��� varchar �̹Ƿ� sql �����ϸ鼭 ���ڷ� ��ȯ�� �� �ְ� *1�� �Ͽ� ���� ������ �־��ش�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// ��ǰ ��
			pstmt.setString(2, g.getDate_min());	// �ּ� ����� ����
			pstmt.setString(3, g.getDate_max());	// �ִ� ����� ����
			pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));	// �ּ� ���ݴ� ����
			pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));	// �ִ� ���ݴ� ����
			System.out.println(pstmt + "<-- pstmt   gSearchJoinDateGnamePrice()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchJoinDateGnamePrice()   Gdao.java");
			
			map = new HashMap<String, Object>();
			
			ArrayList<User> mlist = new ArrayList<>();		// ȸ���� ������ ȸ�� ����Ʈ
			ArrayList<Goods> glist = new ArrayList<>();		// ��ǰ�� ������ ��ǰ ����Ʈ
			
			while(rs.next()){
				User mem = new User();		// �ݺ����� ������������ ��ü�� �����ϰ� �߰��ϴ� ���� �ݺ��Ѵ�.
				Goods goods = new Goods();
				
				mem.setU_id(rs.getString("u_id"));			// ȸ�� ���̵�
				mem.setU_name(rs.getString("u_name"));		// ȸ�� �̸�
				
				goods.setG_code(rs.getString("g_code"));	// ��ǰ �ڵ�
				goods.setG_name(rs.getString("g_name"));	// ��ǰ �̸�
				goods.setG_cate(rs.getString("g_cate"));	// ī�װ�
				goods.setG_price(rs.getString("g_price"));	// ����
				goods.setG_date(rs.getString("g_date"));	// �����
				
				mlist.add(mem);		// ȸ�� ��ü �ϳ��� �߰�
				glist.add(goods);	// ��ǰ ��ü �ϳ��� �߰�
			}
			
			map.put("mlist", mlist);	// ȸ�� ����Ʈ ��ü�� "mlist" Ű������ �־��ش�.
			map.put("glist", glist);	// ��ǰ ����Ʈ ��ü�� "glist" Ű������ �־��ش�.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// ��ǰ�˻�-��������(��ǰ ���̺�)_�Ⱓ��_��ǰ��
	public Map<String, Object> gSearchJoinDateGname(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gSearchJoinDateGname()   Gdao.java");
			
			String sql = "SELECT u.u_id, u.u_name, "
					   + "g.g_code, g.g_name, g.g_cate, g.g_price, g.g_date "
					   + "FROM tb_user AS u INNER JOIN tb_goods AS g "
					   + "ON u.u_id = g.u_id "
					   + "AND g.g_name = ? "
					   + "AND DATE_FORMAT(g_date,'%Y-%m-%d') "
					   + "BETWEEN ? AND ?";
			// ȸ�� ���̺��� ȸ�� ���̵�� ȸ�� �̸��� ��������
			// ��ǰ ���̺��� ��ǰ �ڵ�, ��ǰ �̸�, ī�װ�, ����, ������� �����´�.
			// ȸ�� ���̺��� ������ u�� �ϰ� ��ǰ ���̺��� ������ g�� �Ͽ�
			// ȸ�����̺��� ȸ�� ���̵�� ��ǰ ���̺��� �Ǹ��� ���̵� ���� �������� inner join�� �����Ѵ�.
			// Ư�� ��ǰ �̸��� �־��ְ� ������� ��¥ �����Ͽ� �Ⱓ ������ �־��ش�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// ��ǰ �̸�
			pstmt.setString(2, g.getDate_min());	// �ּ� ����� ����
			pstmt.setString(3, g.getDate_max());	// �ִ� ����� ����
			System.out.println(pstmt + "<-- pstmt   gSearchJoinDateGname()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchJoinDateGname()   Gdao.java");
			
			map = new HashMap<String, Object>();
			
			ArrayList<User> mlist = new ArrayList<>();		// ȸ�� ������ ������ ȸ�� ����Ʈ
			ArrayList<Goods> glist = new ArrayList<>();		// ��ǰ ������ ������ ��ǰ ����Ʈ
			
			while(rs.next()){
				User mem = new User();		// �ݺ����� ������������ ��ü�� �����ϰ� �߰��ϴ� ���� �ݺ��Ѵ�.
				Goods goods = new Goods();
				
				mem.setU_id(rs.getString("u_id"));				// ȸ�� ���̵�
				mem.setU_name(rs.getString("u_name"));			// ȸ�� �̸�
				
				goods.setG_code(rs.getString("g_code"));		// ��ǰ �ڵ�
				goods.setG_name(rs.getString("g_name"));		// ��ǰ ��
				goods.setG_cate(rs.getString("g_cate"));		// ī�װ�
				goods.setG_price(rs.getString("g_price"));		// ����
				goods.setG_date(rs.getString("g_date"));		// �����
				
				mlist.add(mem);		// ȸ�� ��ü�� �ϳ��� ����Ʈ�� �߰�
				glist.add(goods);	// ��ǰ ��ü�� �ϳ��� ����Ʈ�� �߰�
			}
			
			map.put("mlist", mlist);	// ȸ�� ����Ʈ ��ü�� "mlist" Ű������ �־��ش�.
			map.put("glist", glist);	// ��ǰ ����Ʈ ��ü�� "glist" Ű������ �־��ش�.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// ��ǰ�˻�-��������(��ǰ ���̺�)_�Ⱓ��_
	public Map<String, Object> gSearchJoinDate(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gSearchJoinDate()   Gdao.java");
			
			String sql = "SELECT u.u_id, u.u_name, "
					   + "g.g_code, g.g_name, g.g_cate, g.g_price, g.g_date "
					   + "FROM tb_user AS u INNER JOIN tb_goods AS g "
					   + "ON u.u_id = g.u_id "
					   + "AND DATE_FORMAT(g_date,'%Y-%m-%d') "
					   + "BETWEEN ? AND ?";
			// ȸ�� ���̺��� ȸ�� ���̵�� ȸ�� �̸��� ��������
			// ��ǰ ���̺��� ��ǰ �ڵ�, ��ǰ �̸�, ī�װ�, ����, ������� �����´�.
			// ȸ�� ���̺��� ������ u�� �ϰ� ��ǰ ���̺��� ������ g�� �Ͽ�
			// ȸ�����̺��� ȸ�� ���̵�� ��ǰ ���̺��� �Ǹ��� ���̵� ���� �������� inner join�� �����Ѵ�.
			// ������� ��¥ �����Ͽ� �Ⱓ ������ �־��ش�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getDate_min());	// �ּ� ����� ����
			pstmt.setString(2, g.getDate_max());	// �ִ� ����� ����
			System.out.println(pstmt + "<-- pstmt   gSearchJoinDate()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchJoinDate()   Gdao.java");
			
			map = new HashMap<String, Object>();
			
			ArrayList<User> mlist = new ArrayList<>();		// ȸ�� ������ ������ ȸ�� ����Ʈ
			ArrayList<Goods> glist = new ArrayList<>();		// ��ǰ ������ ������ ��ǰ ����Ʈ
			
			while(rs.next()){
				User mem = new User();			// �ݺ����� ������������ ��ü�� �����ϰ� �߰��ϴ� ���� �ݺ��Ѵ�.
				Goods goods = new Goods();		
				
				mem.setU_id(rs.getString("u_id"));				// ȸ�� ���̵�
				mem.setU_name(rs.getString("u_name"));			// ȸ�� �̸�
				
				goods.setG_code(rs.getString("g_code"));		// ��ǰ �ڵ�
				goods.setG_name(rs.getString("g_name"));		// ��ǰ �̸�
				goods.setG_cate(rs.getString("g_cate"));		// ī�װ�
				goods.setG_price(rs.getString("g_price"));		// ����
				goods.setG_date(rs.getString("g_date"));		// �����
				
				mlist.add(mem);		// ȸ�� ��ü�� �ϳ��� ����Ʈ�� �߰�
				glist.add(goods);	// ��ǰ ��ü�� �ϳ��� ����Ʈ�� �߱�
			}
			
			map.put("mlist", mlist);	// ȸ�� ����Ʈ ��ü�� "mlist" Ű������ �־��ش�.
			map.put("glist", glist);	// ��ǰ ����Ʈ ��ü�� "glist" Ű������ �־��ش�.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// ��ǰ ����Ʈ ��ü ��ȸ(������, �Ǹ��� ���Ѹ�)
	public ArrayList<Goods> goodsAllSelect() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> glist = null;
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   goodsAllSelect()   Gdao.java");
			
			String sql = "SELECT g_code, u_id, g_name, g_price, g_color, g_size from tb_goods";
			// ��ǰ ���̺��� ��ǰ �ڵ�, �Ǹ��� ���̵�, ��ǰ��, ����, ����, ����� ���ؿ´�.
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   goodsAllSelect()   Gdao.java");
			
			glist = new ArrayList<>();
			
			while(rs.next()){
				Goods g = new Goods();		// �ݺ����� ������������ ��ü�� �����ϰ� �߰��ϴ� ���� �ݺ��Ѵ�.
				
				g.setG_code(rs.getString("g_code"));		// ��ǰ �ڵ�
				g.setG_name(rs.getString("g_name"));		// ��ǰ �̸�
				g.setU_id(rs.getString("u_id"));			// �Ǹ��� ���̵�
				g.setG_price(rs.getString("g_price"));		// ����
				g.setG_color(rs.getString("g_color"));		// ����
				g.setG_size(rs.getString("g_size"));		// ������
				
				glist.add(g);	// ��ǰ ��ü�� �ϳ��� ����Ʈ�� �߰��Ѵ�.
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
	
	
	// ��ǰ ���� ó��
	public void gDelete(String g_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gDelete()   Gdao.java");
			
			pstmt = conn.prepareStatement("DELETE FROM tb_goods WHERE g_code=?");
			// ��ǰ ���̺��� �⺻Ű�� ��ǰ �ڵ�� Ư���� ��ǰ�� �����Ѵ�.
			
			pstmt.setString(1, g_code);	// ��ǰ �ڵ� ����
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
	
	
	// ��ǰ ���� ó��
	public void gUpdate(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gUpdate()   Gdao.java");
			
			String sql = "UPDATE tb_goods SET " 
						+"g_name=?, g_cate=?, g_price=?, g_color=?, g_size=?, g_date=now(), g_desc=? WHERE g_code=?";
			// ��ǰ �ڵ�� ��ġ�ϴ� ���ڵ��� ��ǰ �̸�, ī�װ�, ����, ����, ������, ��¥�� ���糯¥��, �󼼼����� �����Ѵ�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// ��ǰ �̸�
			pstmt.setString(2, g.getG_cate());		// ī�װ�
			pstmt.setString(3, g.getG_price());		// ����
			pstmt.setString(4, g.getG_color());		// ����
			pstmt.setString(5, g.getG_size());		// ������
			pstmt.setString(6, g.getG_desc());		// �� ����
			pstmt.setString(7, g.getG_code());		// ��ǰ �ڵ�
			System.out.println(pstmt + " : pstmt   gUpdate()   Gdao.java");
			
			int result = pstmt.executeUpdate();
			if(result != 0) {
				System.out.println("���������� �����Ǿ����ϴ�.   gUpdate()   Gdao.java");
				System.out.println("���������� ������ �� ���� : " + result + "   gUpdate()   Gdao.java");
			}
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	
	// ��ǰ �󼼺���
	public Goods gDetail(String g_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Goods goods = new Goods();
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gDetail()   Gdao.java");
			
			pstmt = conn.prepareStatement("SELECT * FROM tb_goods WHERE g_code=?");
			// ��ǰ �ڵ�� ��ġ�ϴ� ���ڵ带 ���ؿ´�.
			pstmt.setString(1, g_code);	// ��ǰ �ڵ� ����
			System.out.println(pstmt + " : pstmt   gDetail()   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " : rs   gDetail()   Gdao.java");
			
			if(rs.next()) {
				goods.setG_code(rs.getString("g_code"));		// ��ǰ �ڵ�
				goods.setU_id(rs.getString("u_id"));			// �Ǹ��� ���̵�
				goods.setG_name(rs.getString("g_name"));		// ��ǰ �̸�
				goods.setG_cate(rs.getString("g_cate"));		// ī�װ�
				goods.setG_price(rs.getString("g_price"));		// ����
				goods.setG_color(rs.getString("g_color"));		// ����
				goods.setG_size(rs.getString("g_size"));		// ������
				goods.setG_date(rs.getString("g_date"));		// �����
				goods.setG_desc(rs.getString("g_desc"));		// �� ����
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
	
	
	// ��ǰ�˻�(��ǰ ���̺�)_�Ⱓ��_��ǰ��_���ݱ���_���� ����
	public ArrayList<Goods> gSearchSort(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> goodlist = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gSearchSort(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE g_name=? AND DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? "
					   + "AND g_price*1 BETWEEN ? AND ? "
					   + "ORDER BY g_price*1 " + g.getSort();
			// ��ǰ ���̺��� ��ǰ �ڵ�, ��ǰ �̸�, ī�װ�, ����, ��� ��¥�� ��ȸ�Ѵ�.
			// Ư�� ��ǰ�� �̸��� ��ġ�ϰ� �Ⱓ ���� ���� ����� ������
			// ���� �÷��� varchar �̹Ƿ� *1�� �Ͽ� SQL���� ���ڷ� ó���ϵ��� �Ͽ� Ư�� ���� ���� ���� �ִ� ��ǰ�� ��ȸ�Ѵ�.
			// ������ ���������̳� ������������ �����Ѵ�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// ��ǰ �̸�
			pstmt.setString(2, g.getDate_min());	// �ּ� ����� ����
			pstmt.setString(3, g.getDate_max());	// �ִ� ����� ����
			pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));	// �ּ� ���ݴ� ����
			pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));	// �ִ� ���ݴ� ����
			System.out.println(pstmt + "<-- pstmt   gSearchSort(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchSort(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();			// �ݺ����� ������������ ��ü�� �����ϰ� �߰��ϴ� ���� �ݺ��Ѵ�.
				goods.setG_code(rs.getString("g_code"));		// ��ǰ �ڵ�
				goods.setG_name(rs.getString("g_name"));		// ��ǰ �̸�
				goods.setG_cate(rs.getString("g_cate"));		// ī�װ�
				goods.setG_price(rs.getString("g_price"));		// ����
				goods.setG_date(rs.getString("g_date"));		// �������
				
				goodlist.add(goods);	// ��ǰ ��ü�� �ϳ��� ����Ʈ�� �߰�
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
	
	
	// ��ǰ�˻�(��ǰ ���̺�)_�Ⱓ��_��ǰ��_���ݱ���
	public ArrayList<Goods> gSearchDateGnamePrice(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> goodlist = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gSearchDateGnamePrice(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE g_name=? AND DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? "
					   + "AND g_price*1 BETWEEN ? AND ?";
			// ��ǰ �ڵ�, ��ǰ �̸�, ī�װ�, ����, ������ڸ� ��ǰ ���̺�� ���� ��ȸ�Ѵ�.
			// Ư�� ��ǰ �̸��� ��ġ�ϰ� ��������� ������ ���Ͽ� �� �Ⱓ ���� ��ϵ� ��ǰ�̾�� �Ѵ�.
			// ���� �÷��� varchar �̹Ƿ� *1�� �Ͽ� ���ڷ� ó���ǰ� �Ͽ� ���� ������ ���Ͽ� ��ȸ�Ѵ�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// ��ǰ �̸�
			pstmt.setString(2, g.getDate_min());	// �ּ� ����� ����
			pstmt.setString(3, g.getDate_max());	// �ִ� ����� ����
			pstmt.setInt(4, Integer.parseInt(g.getPrice_min()));	// �ּ� ���ݴ� ����
			pstmt.setInt(5, Integer.parseInt(g.getPrice_max()));	// �ִ� ���ݴ� ����
			System.out.println(pstmt + "<-- pstmt   gSearchDateGnamePrice(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchDateGnamePrice(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();		// �ݺ����� ������������ ��ü�� �����ϰ� �߰��ϴ� ���� �ݺ��Ѵ�.
				goods.setG_code(rs.getString("g_code"));		// ��ǰ �ڵ�
				goods.setG_name(rs.getString("g_name"));		// ��ǰ �̸�
				goods.setG_cate(rs.getString("g_cate"));		// ī�װ�
				goods.setG_price(rs.getString("g_price"));		// ����
				goods.setG_date(rs.getString("g_date"));		// �������
				
				goodlist.add(goods);	// ��ǰ ��ü�� �ϳ��� ����Ʈ�� �߰��Ѵ�.
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
	
	
	// ��ǰ�˻�(��ǰ ���̺�)_�Ⱓ��_��ǰ��
	public ArrayList<Goods> gSearchDateGname(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> goodlist = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���ؿ´�.
			System.out.println(conn + "<-- conn   gSearchDateGname(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE g_name=? AND DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? ";
			// ��ǰ �ڵ�, ��ǰ �̸�, ī�װ�, ����, ������ڸ� ��ǰ ���̺��� ��ȸ�Ѵ�.
			// Ư�� ��ǰ �̸��� ��ġ�ؾ� �ϸ� �Է��� ������� ������ ��ǰ�� ��ȸ�Ѵ�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getG_name());		// ��ǰ �̸�
			pstmt.setString(2, g.getDate_min());	// �ּ� ����� ����
			pstmt.setString(3, g.getDate_max());	// �ִ� ����� ����
			System.out.println(pstmt + "<-- pstmt   gSearchDateGname(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchDateGname(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();		// �ݺ����� ������������ ��ü�� �����ϰ� �߰��ϴ� ���� �ݺ��Ѵ�.
				goods.setG_code(rs.getString("g_code"));		// ��ǰ �ڵ�
				goods.setG_name(rs.getString("g_name"));		// ��ǰ �̸�
				goods.setG_cate(rs.getString("g_cate"));		// ī�װ�
				goods.setG_price(rs.getString("g_price"));		// ����
				goods.setG_date(rs.getString("g_date"));		// �������
				
				goodlist.add(goods);	// ��ǰ ��ü�� �ϳ��� ����Ʈ�� �߰��Ѵ�.
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
	
	
	// ��ǰ�˻�(��ǰ ���̺�)_�Ⱓ��
	public ArrayList<Goods> gSearchDate(Goods g) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Goods> goodlist = null;
		
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gSearchDate(Goods)   Gdao.java");
			
			String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
					   + "WHERE DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? ";
			// ��ǰ ���̺��� ��ǰ �ڵ�, ��ǰ �̸�, ī�װ�, ����, ������ڸ� ��ȸ�Ѵ�.
			// �Է��� ������� ���� �ִ� ��ǰ���� ��ȸ�Ѵ�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, g.getDate_min());		// �ּ� ����� ����
			pstmt.setString(2, g.getDate_max());		// �ִ� ����� ����
			System.out.println(pstmt + "<-- pstmt   gSearchDate(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gSearchDate(Goods)   Gdao.java");
			
			goodlist = new ArrayList<>();
			
			while(rs.next()){
				Goods goods = new Goods();			// �ݺ����� ������������ ��ü�� �����ϰ� �߰��ϴ� ���� �ݺ��Ѵ�.
				goods.setG_code(rs.getString("g_code"));		// ��ǰ �ڵ�
				goods.setG_name(rs.getString("g_name"));		// ��ǰ �̸�
				goods.setG_cate(rs.getString("g_cate"));		// ī�װ�
				goods.setG_price(rs.getString("g_price"));		// ����
				goods.setG_date(rs.getString("g_date"));		// �������
				
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
	
	
	// ��ǰ ��� �ϱ�
	public void gInsert(Goods g) throws ClassNotFoundException {
		// 1,2 �ܰ� ����̹� �ε�, ��� ������� ����
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			DriverDB db = new DriverDB();
			conn = db.driverDbcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			System.out.println(conn + "<-- conn   gInsert(Goods)   Gdao.java");
			
			String sql = "SELECT DISTINCT SUBSTR(g_code,1,6) AS temp_code FROM tb_goods";
			// ��ǰ ���̺��� ��ǰ �ڵ� �κп��� �Ȱ��� �ݺ��Ǵ� goods_ �κ��� �ߺ��� �����Ͽ� temp_code �������� ��ȸ�Ѵ�.
			// goods_1, goods_2, goods_3 ...
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + " <- pstmt   gInsert(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " <- rs   gInsert(Goods)   Gdao.java");
			
			rs.next();
			String temp_g_code = "";
			if(rs.next()) {
				temp_g_code = rs.getString("temp_code"); // ������ temp_code���� �ݺ��Ǵ� ���ڿ��� �����Ѵ�.
			}
			System.out.println(temp_g_code + " <- temp_g_code   gInsert(Goods)   Gdao.java");
			
			rs.close();
			pstmt.close();
			// �ѹ� �� ����ؾ��ϹǷ� �ݾ��ش�.
			
			pstmt = conn.prepareStatement("SELECT MAX(SUBSTR(g_code,7)) AS max_code FROM tb_goods");
			// ��ǰ �ڵ忡�� �� �޺κ��� ���� �κи� �����Ͽ� �ִ� ���� max_code �������� ���´�.
			
			System.out.println(pstmt + " <- pstmt   gInsert(Goods)   Gdao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + " <- rs   gInsert(Goods)   Gdao.java");
			
			String temp_max = "";
			if(rs.next()) {
				temp_max = rs.getString("max_code"); // ������ �̿��Ͽ� �ִ� ���� �����Ѵ�.
			}
			System.out.println(temp_max + " <- temp_max   gInsert(Goods)   Gdao.java");
			
			int max_code = Integer.parseInt(temp_max);	// ���ڿ��ε� ���ڸ� ���������� ��ȯ�Ѵ�.
			System.out.println(max_code + " <- max_code   gInsert(Goods)   Gdao.java");
			
			max_code++;		// �ִ� ������ 1�� ���� ������ �߰��ؾ� �ϹǷ� ���� ������ �����Ѵ�.
			System.out.println(max_code + " <- max_code++   gInsert(Goods)   Gdao.java");
			
			String g_code = temp_g_code+max_code; // �ݺ��Ǵ� �ڵ��� "gcode_" �κа� �ִ� ���ڰ��� ���Ѵ�.
			System.out.println(g_code + " <- g_code   gInsert(Goods)   Gdao.java");
			
			
			rs.close();
			pstmt.close();
			// �ѹ� �� ����ؾ��ϹǷ� �ݾ��ش�.
			
			pstmt = conn.prepareStatement("INSERT INTO tb_goods VALUES(?, ?, ?, ?, ?, ?, ?, NOW(), ?)");
			pstmt.setString(1, g_code);				// �ִ� ���ڰ��� ����ִ� ��ǰ �ڵ�(�⺻Ű)
			pstmt.setString(2, g.getU_id());		// �Ǹ��� ���̵�
			pstmt.setString(3, g.getG_name());		// ��ǰ �̸�
			pstmt.setString(4, g.getG_cate());		// ī�װ�
			pstmt.setString(5, g.getG_price());		// ����
			pstmt.setString(6, g.getG_color());		// ����
			pstmt.setString(7, g.getG_size());		// ������
			pstmt.setString(8, g.getG_desc());		// �� ����
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
