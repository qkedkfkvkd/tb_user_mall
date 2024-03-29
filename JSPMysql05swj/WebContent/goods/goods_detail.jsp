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
	String g_code = request.getParameter("send_id");
	System.out.println(g_code + " <- g_code");
	
	Gdao dao = new Gdao();
	Goods g = dao.gDetail(g_code);
	// 넘겨 받은 상품 코드에 해당하는 상품 레코드 객체 리턴해주는 메소드 호출
%>

<h3>제품 상세보기</h3>
<form action="<%=request.getContextPath()%>/goods/goods_update_form.jsp" method="post">
<table border="1">
	<tr>
		<td>상품코드</td>
		<td><input type="text" name="g_code" value="<%=g.getG_code()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>판매자</td>
		<td><input type="text" name="m_id" value="<%=g.getU_id()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>상품명</td>
		<td><input type="text" name="g_name" value="<%=g.getG_name()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>카테고리</td>
		<td><input type="text" name="g_cate" value="<%=g.getG_cate()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>가격</td>
		<td><input type="text" name="g_price" value="<%=g.getG_price()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>색상</td>
		<td><input type="text" name="g_color" value="<%=g.getG_color()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>사이즈</td>
		<td><input type="text" name="g_size" value="<%=g.getG_size()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>등록일</td>
		<td><input type="text" name="g_date" value="<%=g.getG_date()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>제품 상세</td>
		<td><input type="text" name="g_desc" value="<%=g.getG_desc()%>" readonly="readonly"></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="submit" value="수정화면으로">
		</td>
	</tr>
</table>
</form>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>




