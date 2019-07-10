<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>

<%
	request.setCharacterEncoding("euc-kr");
	String g_code = request.getParameter("g_code");
	String g_name = request.getParameter("g_name");
	String g_cate = request.getParameter("g_cate");
	String g_price = request.getParameter("g_price");
	String g_color = request.getParameter("g_color");
	String g_size = request.getParameter("g_size");
	String g_desc = request.getParameter("g_desc");
	
	System.out.println(g_code + " : g_code   goods_update_pro.jsp");
	System.out.println(g_name + " : g_name   goods_update_pro.jsp");
	System.out.println(g_cate + " : g_cate   goods_update_pro.jsp");
	System.out.println(g_price + " : g_price   goods_update_pro.jsp");
	System.out.println(g_color + " : g_color   goods_update_pro.jsp");
	System.out.println(g_size + " : g_size   goods_update_pro.jsp");
	System.out.println(g_desc + " : g_desc   goods_update_pro.jsp");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/db05swj?" +
				 "useUnicode=true&characterEncoding=euckr";
		String user = "dbid05swj";
		String password = "dbpw05swj";
		
		conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn + " : conn   goods_update_pro.jsp");
		
		String sql = "UPDATE tb_goods SET " 
					+"g_name=?, g_cate=?, g_price=?, g_color=?, g_size=?, g_date=now(), g_desc=? WHERE g_code=?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, g_name);
		pstmt.setString(2, g_cate);
		pstmt.setString(3, g_price);
		pstmt.setString(4, g_color);
		pstmt.setString(5, g_size);
		pstmt.setString(6, g_desc);
		pstmt.setString(7, g_code);
		System.out.println(pstmt + " : pstmt   goods_update_pro.jsp");
		
		int result = pstmt.executeUpdate();
		if(result != 0) {
			System.out.println("성공적으로 수정되었습니다.");
			System.out.println("성공적으로 수정된 행 갯수 : " + result);
		}
		
		response.sendRedirect(request.getContextPath() + "/goods/goods_search_form.jsp");
	} catch(SQLException ex) {
		out.println(ex.getMessage());
		ex.printStackTrace();
	} finally {
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
%>





