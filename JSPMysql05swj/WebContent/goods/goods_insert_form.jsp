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

<h3>상품등록화면</h3>
<form action="<%=request.getContextPath()%>/goods/goods_insert_pro.jsp" method="post">
<input type="hidden" name="u_id" value="<%=S_ID%>">
상품명 : <input type="text" name="g_name"><br/>
카테고리 : <input type="text" name="g_cate"><br/>
가격 : <input type="text" name="g_price"><br/>
색상 : <input type="text" name="g_color"><br/>
사이즈 : <input type="text" name="g_size"><br/>
상세설명 : <input type="text" name="g_desc"><br/>
<button type="submit">등록하기</button>
</form>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>
