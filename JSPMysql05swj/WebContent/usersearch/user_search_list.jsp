<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>
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

<%@ include file="/usersearch/user_search_form.jsp" %>
<!-- <br/><br/> 습관적으로 태그를 열었으면 닫는 습관을 들이기. 나중에 중요하다. -->

<%= request.getRequestURI() %> <br>
회원 리스트 <br>
<table width="100%" border="1">
<tr>
	<td>아이디</td>
	<td>비번</td>
	<td>권한</td>
	<td>이름</td>
	<td>이메일</td>
	<td>폰번호</td>
	<td>주소</td>
	<td>수정</td>
	<td>삭제</td>
</tr>
<%
	request.setCharacterEncoding("euc-kr");
	String sk = request.getParameter("sk");
	String sv = request.getParameter("sv");
	
	System.out.println(sk + " <- sk");
	System.out.println(sv + " <- sv");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try{
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		System.out.println(conn + "<-- conn   user_search_list.jsp");
		if(conn != null){
			out.println("01 DB연결 성공");
		}else{
			out.println("02 DB연결 실패");
		}
		
		if(sk == null & sv == null) {
			System.out.println("01   sk == null & sv == null");
			pstmt = conn.prepareStatement("SELECT * FROM tb_user");
			
		} else if(sk != null & sv.equals("")) {
			System.out.println("02   sk != null & sv.equals()");
			pstmt = conn.prepareStatement("SELECT * FROM tb_user");
			
		} else if (sk != null & sv != null) {
			System.out.println("03   sk != null & sv != null");
			if(sk.equals("u_id")) {
				pstmt = conn.prepareStatement("SELECT * FROM tb_user WHERE u_id=?");
				
			} else if(sk.equals("u_level")) {
				pstmt = conn.prepareStatement("SELECT * FROM tb_user WHERE u_level=?");
				
			} else if(sk.equals("u_name")) {
				pstmt = conn.prepareStatement("SELECT * FROM tb_user WHERE u_name=?");
				
			} else if(sk.equals("u_email")) {
				pstmt = conn.prepareStatement("SELECT * FROM tb_user WHERE u_email=?");
			}
			
			pstmt.setString(1, sv);
		}
		
		System.out.println(pstmt + "<-- pstmt   user_search_list.jsp");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   user_search_list.jsp");
		
		while(rs.next()){
%>
		<tr>
			<td><%= rs.getString("u_id")%></td>
			<td><%= rs.getString("u_pw")%></td>
			<td><%= rs.getString("u_level")%></td>
			<td><%= rs.getString("u_name")%></td>
			<td><%= rs.getString("u_email")%></td>
			<td><%= rs.getString("u_phone")%></td>
			<td><%= rs.getString("u_addr")%></td>
			<td><a href='<%=request.getContextPath()%>/userupdate/user_update_form.jsp?send_id=<%=rs.getString("u_id")%>'>수정하기</a></td>
			<td><a href='<%=request.getContextPath()%>/userdelete/user_delete_pro.jsp?send_id=<%=rs.getString("u_id")%>'>삭제하기</a></td>
		</tr>
<%		
		}
		
	} catch(SQLException ex) {
		out.println(ex.getMessage());
		ex.printStackTrace();
	} finally {
		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
%>	

</table>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>