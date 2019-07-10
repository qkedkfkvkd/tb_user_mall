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

<%@ page import="kr.or.ksmart.dao.Mdao" %>
<%@ page import="kr.or.ksmart.dto.User" %>

<%
	String u_id = request.getParameter("send_id");
	System.out.println(u_id + " <- m_id");
	
	Mdao mdao = new Mdao();
	User mem = mdao.mSelectforUpdate(u_id);
%>

<form action="<%= request.getContextPath() %>/userupdate/user_update_pro.jsp" method="post">
<table border="1">
<tr>
	<td>아이디</td>
	<td><input type="text" name="u_id" size="20" value="<%=mem.getU_id()%>" readonly="readonly"></td>
<tr>
<tr>
	<td>암호</td>
	<td><input type="text" name="u_pw" size="20" value="<%=mem.getU_pw()%>"></td>
<tr>
<tr>
	<td>권한</td>
	<td><input type="text" name="u_level" size="20" value="<%=mem.getU_level()%>"></td>
<tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="u_name" size="20" value="<%=mem.getU_name()%>"></td>
<tr>
<tr>
	<td>이메일</td>
	<td><input type="text" name="u_email" size="20" value="<%=mem.getU_email()%>"></td>
<tr>
<tr>
	<td>휴대폰</td>
	<td><input type="text" name="u_phone" size="20" value="<%=mem.getU_phone()%>"></td>
<tr>
<tr>
	<td>주소</td>
	<td><input type="text" name="u_addr" size="20" value="<%=mem.getU_addr()%>"></td>
<tr>
<tr>
	<td colspan="4"><input type="submit" value="수정"></td>
</tr>
</table>
</form>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>
