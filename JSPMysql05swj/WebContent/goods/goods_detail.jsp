<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>

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

<%
	String g_code = request.getParameter("send_id");
	System.out.println(g_code + " <- g_code");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String dbcode = null;
	String dbuid = null;
	String dbname = null;
	String dbcate = null;
	String dbprice = null;
	String dbcolor = null;
	String dbsize = null;
	String dbdate = null;
	String dbdesc = null;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/db05swj?" +
				 "useUnicode=true&characterEncoding=euckr";
		String user = "dbid05swj";
		String password = "dbpw05swj";
		conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn + " : conn   goods_detail.jsp");
		
		pstmt = conn.prepareStatement("SELECT * FROM tb_goods WHERE g_code=?");
		pstmt.setString(1, g_code);
		System.out.println(pstmt + " : pstmt   goods_detail.jsp");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + " : rs   goods_detail.jsp");
		
		if(rs.next()) {
			dbcode = rs.getString("g_code");
			dbuid = rs.getString("u_id");
			dbname = rs.getString("g_name");
			dbcate = rs.getString("g_cate");
			dbprice = rs.getString("g_price");
			dbcolor = rs.getString("g_color");
			dbsize = rs.getString("g_size");
			dbdate = rs.getString("g_date");
			dbdesc = rs.getString("g_desc");
			
			System.out.println(dbcode + "<-- dbcode");
			System.out.println(dbuid + "<-- dbuid");
			System.out.println(dbname + "<-- dbname");
			System.out.println(dbcate + "<-- dbcate");
			System.out.println(dbprice + "<-- dbprice");
			System.out.println(dbcolor + "<-- dbcolor");
			System.out.println(dbsize + "<-- dbsize");
			System.out.println(dbdate + "<-- dbdate");
			System.out.println(dbdesc + "<-- dbdesc");
		}
	} catch(ClassNotFoundException e) {
		out.println(e.getMessage());
		e.printStackTrace();
	} catch(SQLException e) {
		out.println(e.getMessage());
		e.printStackTrace();
	} finally {
		if(rs != null) try {rs.close();} catch(SQLException e){}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e){}
		if(conn != null) try {conn.close();} catch(SQLException e){}
	}
%>

<h3>제품 상세보기</h3>
<form action="<%=request.getContextPath()%>/goods/goods_update_form.jsp" method="post">
<table border="1">
	<tr>
		<td>상품코드</td>
		<td><input type="text" name="g_code" value="<%=dbcode%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>판매자</td>
		<td><input type="text" name="u_id" value="<%=dbuid%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>상품명</td>
		<td><input type="text" name="g_name" value="<%=dbname%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>카테고리</td>
		<td><input type="text" name="g_cate" value="<%=dbcate%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input type="text" name="g_price" value="<%=dbprice%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>색상</td>
		<td><input type="text" name="g_color" value="<%=dbcolor%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>사이즈</td>
		<td><input type="text" name="g_size" value="<%=dbsize%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>등록일</td>
		<td><input type="text" name="g_date" value="<%=dbdate%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>제품 상세</td>
		<td><input type="text" name="g_desc" value="<%=dbdesc%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="submit" value="수정화면으로">
		</td>
	</tr>
</table>
</form>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>




