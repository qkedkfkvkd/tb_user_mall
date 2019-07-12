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

<form action="<%=request.getContextPath()%>/goods/goods_search_list.jsp" method="post">
	기간 검색 :
	<input type="text" name="date_min"> ~
	<input type="text" name="date_max"><br/>
	상품명 : <input type="text" name="g_name"><br/>
	<button type="submit">검색버튼</button>
</form>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>