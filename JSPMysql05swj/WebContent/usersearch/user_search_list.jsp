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
			<td><a href='<%=request.getContextPath()%>/userupdate/user_update_form.jsp?send_id=<%=user.getU_id()%>'>수정하기</a></td>
			<td><a href='<%=request.getContextPath()%>/userdelete/user_delete_pro.jsp?send_id=<%=user.getU_id()%>'>삭제하기</a></td>
		</tr>
<%	}%>
</table>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>