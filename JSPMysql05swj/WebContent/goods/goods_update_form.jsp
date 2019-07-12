<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Gdao"%>
<%@ page import="kr.or.ksmart.dto.Goods"%>

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
	String g_code = request.getParameter("g_code");
	System.out.println(g_code + " <- g_code");
	
	Gdao dao = new Gdao();
	Goods g = dao.gDetail(g_code);
%>

<h3>�����ϱ�</h3>
<form action="<%=request.getContextPath()%>/goods/goods_update_pro.jsp" method="post">
<table border="1">
	<tr>
		<td>��ǰ�ڵ�</td>
		<td><input type="text" name="g_code" value="<%=g.getG_code()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>�Ǹ���</td>
		<td><input type="text" name="m_id" value="<%=g.getU_id()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>��ǰ��</td>
		<td><input type="text" name="g_name" value="<%=g.getG_name()%>"></td>
	</tr>
	<tr>
		<td>ī�װ�</td>
		<td><input type="text" name="g_cate" value="<%=g.getG_cate()%>"></td>
	</tr>
	<tr>
		<td>����</td>
		<td><input type="text" name="g_price" value="<%=g.getG_price()%>"></td>
	</tr>
	<tr>
		<td>����</td>
		<td><input type="text" name="g_color" value="<%=g.getG_color()%>"></td>
	</tr>
	<tr>
		<td>������</td>
		<td><input type="text" name="g_size" value="<%=g.getG_size()%>"></td>
	</tr>
	<tr>
		<td>�����</td>
		<td><input type="text" name="g_date" value="<%=g.getG_date()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>��ǰ ��</td>
		<td><input type="text" name="g_desc" value="<%=g.getG_desc()%>"></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="submit" value="�����ϱ�">
		</td>
	</tr>
</table>
</form>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>




