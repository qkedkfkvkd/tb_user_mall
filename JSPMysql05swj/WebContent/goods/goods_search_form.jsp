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
	���� ���� ���� :
	<select name="orderby">
		<option value="g_date">�Ⱓ��</option>
		<option value="g_name">��ǰ��</option>
		<option value="g_price*1">����</option>
	</select>
	<select name="sort">
		<option value="asc" selected="selected">��������</option>
		<option value="desc">��������</option>
	</select><br/>
	�Ⱓ �˻� :
	<input type="text" name="date_min"> ~
	<input type="text" name="date_max"><br/>
	��ǰ�� : <input type="text" name="g_name"><br/>
	���� �˻� :
	<input type="text" name="price_min"> ~
	<input type="text" name="price_max"><br/>
	<button type="submit">�˻���ư</button>
</form>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>