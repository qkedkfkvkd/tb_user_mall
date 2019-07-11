<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Mdao"%>
<%@ page import="kr.or.ksmart.dto.User"%>
<%@ page import="java.util.ArrayList"%>

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
		<td>���̵�</td>
		<td>��й�ȣ</td>
		<td>����</td>
		<td>�̸�</td>
		<td>�̸���</td>
		<td>����ȣ</td>
		<td>�ּ�</td>
		<td>����</td>
		<td>����</td>
	</tr>
<%
	Mdao dao = new Mdao();
	ArrayList<User> memlist = dao.mAllSelect();
	
	System.out.println(memlist + " <- memlist   user_list.jsp");
	System.out.println(memlist.size() + " <- memlist.size()   user_list.jsp");
	
	for(int i=0; i<memlist.size(); i++) {
		User mem = memlist.get(i);
%>
	<tr>
		<td><%=mem.getU_id()%></td>
		<td><%=mem.getU_pw()%></td>
		<td><%=mem.getU_level()%></td>
		<td><%=mem.getU_name()%></td>
		<td><%=mem.getU_email()%></td>
		<td><%=mem.getU_phone()%></td>
		<td><%=mem.getU_addr()%></td>
		<td><a href='<%=request.getContextPath()%>/mupdate/m_update_form.jsp?send_id=<%=mem.getU_id()%>'>�����ϱ�</a></td>
		<td><a href='<%=request.getContextPath()%>/mdelete/m_delete_pro.jsp?send_id=<%=mem.getU_id()%>'>�����ϱ�</a></td>
	</tr>
<%	}%>
</table>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>
