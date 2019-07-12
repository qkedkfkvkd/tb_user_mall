<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Gdao"%>
<%@ page import="kr.or.ksmart.dto.Goods"%>
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

<%= request.getRequestURI() %> <br>
상품 리스트 <br>
<table width="100%" border="1">
<tr>
	<td>상품명</td>
	<td>판매자</td>
	<td>가격</td>
	<td>색상</td>
	<td>사이즈</td>
	<td>상세보기</td>
	<td>삭제</td>
</tr>
<%
	Gdao dao = new Gdao();
	ArrayList<Goods> glist = dao.goodsAllSelect();
	
	for(int i=0; i<glist.size(); i++) {
		Goods g = glist.get(i);
%>
		<tr>
			<td><%=g.getG_name() %></td>
			<td><%=g.getU_id() %></td>
			<td><%=g.getG_price() %></td>
			<td><%=g.getG_color() %></td>
			<td><%=g.getG_size() %></td>
			<td><a href='<%=request.getContextPath()%>/goods/goods_detail.jsp?send_id=<%=g.getG_code()%>'>상세보기</a></td>
			<td><a href='<%=request.getContextPath()%>/goods/goods_delete_pro.jsp?send_id=<%=g.getG_code()%>'>삭제하기</a></td>
		</tr>
<%	}%>

</table>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>