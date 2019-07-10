<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import="kr.or.ksmart.driverdb.DriverDB"%>


<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="u" class="kr.or.ksmart.dto.User"/>
<jsp:setProperty name="u" property="*"/>


<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		System.out.println(conn + "<-- conn");
		System.out.println(conn.getClass() + "<-- conn.getClass()");
		//02단계 :DB연결(Connection)끝
		
		//03단계 :Query실행을 위한 statement 또는 prepareStatement객체생성 시작
		pstmt = conn.prepareStatement("insert into tb_user values(?, ?, ?, ?, ?, ?, ?)");
		
		System.out.println(pstmt + "<-- pstmt 1");
		System.out.println(pstmt.getClass() + "<-- pstmt.getClass() 1");
		
		pstmt.setString(1, u.getU_id());
		pstmt.setString(2, u.getU_pw());
		pstmt.setString(3, u.getU_level());
		
		System.out.println(pstmt + "<-- pstmt 2");
		
		pstmt.setString(4, u.getU_name());
		pstmt.setString(5, u.getU_email());
		pstmt.setString(6, u.getU_phone());
		pstmt.setString(7, u.getU_addr());
		
		System.out.println(pstmt + "<-- pstmt 3");
		//03단계 :Query실행을 위한 statement 또는 prepareStatement 객체 생성 끝
		
		//04단계 :Query실행 시작
		int result = pstmt.executeUpdate();
		System.out.println(result + "<-- result");
		//04단계 :Query실행 끝
		//05단계 :Query실행결과 사용 (insert의 경우 생략 가능단계)
		
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