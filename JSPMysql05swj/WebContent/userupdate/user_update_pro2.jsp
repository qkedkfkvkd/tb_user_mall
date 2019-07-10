<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="kr.or.ksmart.driverdb.DriverDB"%>
<%@ page import="kr.or.ksmart.dto.User"%>

<%
	String u_id = request.getParameter("u_id");
	String u_pw = request.getParameter("u_pw");
	String u_level = request.getParameter("u_level");
	String u_name = request.getParameter("u_name");
	String u_email = request.getParameter("u_email");
	String u_phone = request.getParameter("u_phone");
	String u_addr = request.getParameter("u_addr");
	
	System.out.println(u_id + " <- u_id   user_update_pro.jsp");
	System.out.println(u_pw + " <- u_pw   user_update_pro.jsp");
	System.out.println(u_level + " <- u_level   user_update_pro.jsp");
	System.out.println(u_name + " <- u_name   user_update_pro.jsp");
	System.out.println(u_email + " <- u_email   user_update_pro.jsp");
	System.out.println(u_phone + " <- u_phone   user_update_pro.jsp");
	System.out.println(u_addr + " <- u_addr   user_update_pro.jsp");
	
	User user = new User();
	user.setU_id(u_id);
	user.setU_pw(u_pw);
	user.setU_level(u_level);
	user.setU_name(u_name);
	user.setU_email(u_email);
	user.setU_phone(u_phone);
	user.setU_addr(u_addr);
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		System.out.println(conn + " <- conn   user_update_pro.jsp");
		
		pstmt = conn.prepareStatement("UPDATE tb_user SET u_pw=?, u_level=?, u_name=?, u_email=?, u_phone=?, u_addr=? WHERE u_id=?");
		pstmt.setString(1, user.getU_pw());
		pstmt.setString(2, user.getU_level());
		pstmt.setString(3, user.getU_name());
		pstmt.setString(4, user.getU_email());
		pstmt.setString(5, user.getU_phone());
		pstmt.setString(6, user.getU_addr());
		pstmt.setString(7, user.getU_id());
		
		System.out.println(pstmt + " <- pstmt   user_update_pro.jsp");
		
		int result = pstmt.executeUpdate();
		System.out.println(result + " <- result   user_update_pro.jsp");
		
		//response.sendRedirect(request.getContextPath() + "/userlist/user_list.jsp");
		response.sendRedirect(request.getContextPath() + "/usersearch/user_search_list.jsp");
	} catch(SQLException e) {
		out.println(e.getMessage());
		e.printStackTrace();
	} finally {
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
	}
%>