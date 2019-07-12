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

<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="g" class="kr.or.ksmart.dto.Goods"/>
<jsp:setProperty name="g" property="*"/>

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
	Gdao dao = new Gdao();
	ArrayList<Goods> goodlist = dao.gSearchSort(g);
	
	System.out.println(goodlist + " <- goodlist   goods_search_list.jsp");
	System.out.println(goodlist.size() + " <- goodlist.size()   goods_search_list.jsp");
	
	for(int i=0; i<goodlist.size(); i++) {
		Goods goods = goodlist.get(i);
%>
	<tr>
		<td><%=goods.getG_code() %></td>
		<td><%=goods.getG_name() %></td>
		<td><%=goods.getG_cate() %></td>
		<td><%=goods.getG_price() %></td>
		<td><%=goods.getG_date() %></td>
	</tr>
<%	}%>
</table>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>
