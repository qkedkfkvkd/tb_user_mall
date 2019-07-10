<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>

<%
	String send_id = request.getParameter("send_id");
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/db05swj?" +
				 "useUnicode=true&characterEncoding=euckr";
		String user = "dbid05swj";
		String password = "dbpw05swj";
		conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn + " : conn   goods_delete_pro.jsp");
		
		pstmt = conn.prepareStatement("DELETE FROM tb_goods WHERE g_code=?");
		pstmt.setString(1, send_id);
		System.out.println(pstmt + " : pstmt   goods_delete_pro.jsp");
		
		int result = pstmt.executeUpdate();
		System.out.println(result + " : result   goods_delete_pro.jsp");
		
		response.sendRedirect(request.getContextPath() + "/goods/goods_search_form.jsp");
		
	} catch(SQLException e) {
		e.printStackTrace();
	} finally {
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
	}
%>



