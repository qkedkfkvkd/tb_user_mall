<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Gdao"%>
<%@ page import="kr.or.ksmart.dto.Goods"%>
<%@ page import="kr.or.ksmart.dto.User"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>

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

<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="g" class="kr.or.ksmart.dto.Goods"/>
<jsp:setProperty name="g" property="*"/>

��ǰ �˻� ����Ʈ <br>
<table width="100%" border="1">
<tr>
	<td>��ǰ�ڵ�</td>
	<td>��ǰ��</td>
	<td>ī�װ�</td>
	<td>����</td>
	<td>�����</td>
	<td>�Ǹ��� ���̵�</td>
	<td>�Ǹ��� �̸�</td>
</tr>
<%
	request.setCharacterEncoding("euc-kr");
	Gdao dao = new Gdao();
	Map<String, Object> map = dao.gSearchJoinDateGname(g);
	
	ArrayList<User> mlist = (ArrayList<User>)map.get("mlist");
	ArrayList<Goods> glist = (ArrayList<Goods>)map.get("glist");
	// ����Ʈ ��ü�� ���̰� �� �� ����.
	
	
	for(int i=0; i<mlist.size(); i++) {
		User mem = mlist.get(i);
		Goods goods = glist.get(i);
%>
		<tr>
			<td><%=goods.getG_code()%></td>
			<td><%=goods.getG_name()%></td>
			<td><%=goods.getG_cate()%></td>
			<td><%=goods.getG_price()%></td>
			<td><%=goods.getG_date()%></td>
			<td><%=mem.getU_id()%></td>
			<td><%=mem.getU_name()%></td>
		</tr>
<%	}%>

</table>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>
