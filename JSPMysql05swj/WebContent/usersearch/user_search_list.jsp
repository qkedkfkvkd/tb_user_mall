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

<%@ include file="/usersearch/user_search_form.jsp" %>
<!-- <br/><br/> ���������� �±׸� �������� �ݴ� ������ ���̱�. ���߿� �߿��ϴ�. -->

<%= request.getRequestURI() %> <br>
ȸ�� ����Ʈ <br>
<table width="100%" border="1">
<tr>
	<td>���̵�</td>
	<td>���</td>
	<td>����</td>
	<td>�̸�</td>
	<td>�̸���</td>
	<td>����ȣ</td>
	<td>�ּ�</td>
	<td>����</td>
	<td>����</td>
</tr>
<%
	request.setCharacterEncoding("euc-kr");
	String sk = request.getParameter("sk");
	String sv = request.getParameter("sv");
	
	System.out.println(sk + " <- sk");
	System.out.println(sv + " <- sv");
	
	Mdao dao = new Mdao();
	ArrayList<User> memlist = dao.mSearch(sk, sv);
	
	for(int i=0; i<memlist.size(); i++) {
		User user = memlist.get(i);
%>
		<tr>
			<td><%=user.getU_id()%></td>
			<td><%=user.getU_pw()%></td>
			<td><%=user.getU_level()%></td>
			<td><%=user.getU_name()%></td>
			<td><%=user.getU_email()%></td>
			<td><%=user.getU_phone()%></td>
			<td><%=user.getU_addr()%></td>
			<td><a href='<%=request.getContextPath()%>/userupdate/user_update_form.jsp?send_id=<%=user.getU_id()%>'>�����ϱ�</a></td>
			<td><a href='<%=request.getContextPath()%>/userdelete/user_delete_pro.jsp?send_id=<%=user.getU_id()%>'>�����ϱ�</a></td>
		</tr>
<%	}%>
</table>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>