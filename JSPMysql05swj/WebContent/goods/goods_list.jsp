<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>

<table>
	<thead>
		<tr>
			<th>상품코드</th>
			<th>상품명</th>
			<th>카테고리</th>
			<th>가격</th>
			<th>색상</th>
			<th>상세보기</th>
			<th>삭제하기</th>
		</tr>
	</thead>
	<tbody>
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String url = "jdbc:mysql://localhost:3306/db05swj?" +
				 "useUnicode=true&characterEncoding=euckr";
	String user = "dbid05swj";
	String password = "dbpw05swj";

	try {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn + " <- conn   goods_insert_pro.jsp");
		
		pstmt = conn.prepareStatement("SELECT g_code, g_name, g_cate, g_price, g_color FROM tb_goods");
		System.out.println(pstmt + " <- pstmt   goods_insert_pro.jsp");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + " <- rs   goods_insert_pro.jsp");
		
		
		while(rs.next()) {
%>
		<tr>
			<td><%=rs.getString("g_code")%></td>
			<td><%=rs.getString("g_name")%></td>
			<td><%=rs.getString("g_cate")%></td>
			<td><%=rs.getString("g_price")%></td>
			<td><%=rs.getString("g_color")%></td>
			<td><a href="<%=request.getContextPath()%>/goods/goods_detail.jsp?send_id=<%=rs.getString("g_code")%>">상세보기</a></td>
			<td><a href="<%=request.getContextPath()%>/goods/goods_delete_pro.jsp?send_id=<%=rs.getString("g_code")%>">삭제하기</a></td>
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
	</tbody>
</table>
<%@ include file="/module/hadan.jsp" %>
</body>
</html>
