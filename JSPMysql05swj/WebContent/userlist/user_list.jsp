<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="kr.or.ksmart.driverdb.DriverDB"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
														<!--       /Layout32/css/main.css -->
</head>

<body>
<%@ include file="/module/top.jsp" %>
<%@ include file="/module/left.jsp" %>

<table width="100%" border="1">
	<tr>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>권한</td>
		<td>이름</td>
		<td>이메일</td>
		<td>폰번호</td>
		<td>주소</td>
		<td>수정</td>
		<td>삭제</td>
	</tr>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		System.out.println(conn + " <- conn user_list.jsp");
		
		pstmt = conn.prepareStatement("SELECT * FROM tb_user");
		System.out.println(pstmt + " <- pstmt user_list.jsp");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + " <- rs user_list.jsp");
		
		while(rs.next()) {
%>
		<tr>
			<td><%= rs.getString("u_id")%></td>
			<td><%= rs.getString("u_pw")%></td>
			<td><%= rs.getString("u_level")%></td>
			<td><%= rs.getString("u_name")%></td>
			<td><%= rs.getString("u_email")%></td>
			<td><%= rs.getString("u_phone")%></td>
			<td><%= rs.getString("u_addr")%></td>
			<td><a href='<%=request.getContextPath()%>/userupdate/user_update_form.jsp?send_id=<%= rs.getString("u_id")%>'>수정하기</a></td>
			<td><a href='<%=request.getContextPath()%>/userdelete/user_delete_pro.jsp?send_id=<%= rs.getString("u_id")%>'>삭제하기</a></td>
		</tr>
<%
		}
	} catch(SQLException e) {
		out.println(e.getMessage());
		e.printStackTrace();
	} finally {
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
	}
%>
</table>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>
