<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>

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

상품 검색 리스트 <br>
<table width="100%" border="1">
<tr>
	<td>상품코드</td>
	<td>상품명</td>
	<td>카테고리</td>
	<td>가격</td>
	<td>등록일</td>
</tr>
<%
	request.setCharacterEncoding("euc-kr");
	
	String orderby = request.getParameter("orderby");
	String sort = request.getParameter("sort");
	String date_min = request.getParameter("date_min");
	String date_max = request.getParameter("date_max");
	String g_name = request.getParameter("g_name");
	String price_min = request.getParameter("price_min");
	String price_max = request.getParameter("price_max");
	
	System.out.println(orderby + " <- orderby   goods_search_list.jsp");
	System.out.println(sort + " <- sort   goods_search_list.jsp");
	System.out.println(date_min + " <- g_date_min   goods_search_list.jsp");
	System.out.println(date_max + " <- g_date_max   goods_search_list.jsp");
	System.out.println(g_name + " <- g_name   goods_search_list.jsp");
	System.out.println(price_min + " <- price_min   goods_search_list.jsp");
	System.out.println(price_max + " <- price_max   goods_search_list.jsp");
	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	Class.forName("com.mysql.jdbc.Driver");
	
	try{
		String jdbcDriver = "jdbc:mysql://localhost:3306/db05swj?" +
				"useUnicode=true&characterEncoding=euckr";
		String dbUser = "dbid05swj";
		String dbPass = "dbpw05swj";
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		System.out.println(conn + "<-- conn   goods_search_list.jsp");
		if(conn != null){
			out.println("01 DB연결 성공");
		}else{
			out.println("02 DB연결 실패");
		}
		
		String sql = "SELECT g_code, g_name, g_cate, g_price, g_date FROM tb_goods "
				   + "WHERE g_name=? AND "
				   + "DATE_FORMAT(g_date,'%Y-%m-%d') BETWEEN ? AND ? "
				   + "AND g_price*1 BETWEEN ? AND ? "
				   + "ORDER BY " + orderby + " " + sort;
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, g_name);
		pstmt.setString(2, date_min);
		pstmt.setString(3, date_max);
		pstmt.setString(4, price_min);
		pstmt.setString(5, price_max);
		System.out.println(pstmt + "<-- pstmt   goods_search_list.jsp");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   goods_search_list.jsp");
		
		while(rs.next()){
%>
		<tr>
			<td><%= rs.getString("g_code")%></td>
			<td><%= rs.getString("g_name")%></td>
			<td><%= rs.getString("g_cate")%></td>
			<td><%= rs.getString("g_price")%></td>
			<td><%= rs.getString("g_date")%></td>
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
