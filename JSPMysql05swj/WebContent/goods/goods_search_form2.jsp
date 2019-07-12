<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/main.css" />
  															  <!-- /Layout32/css/main.css -->

<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

<!-- jQuery UI 국제화 대응을 위한 라이브러리 (다국어) -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/jquery-ui-i18n.min.js"></script>

<!-- datepicker 한국어로 -->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script> -->
<script>
$(function() {
	$.datepicker.setDefaults($.datepicker.regional['ko']); //datepicker 한국어로 사용하기 위한 언어설정
	$('#g_date_min').datepicker();
	$('#g_date_max').datepicker();
});
</script>
</head>

<body>
<%@ include file="/module/top.jsp" %>
<%@ include file="/module/left.jsp" %>

<h3>조인쿼리를 이용하여 검색하기</h3>
<form action="<%=request.getContextPath()%>/goods/goods_search_list2.jsp" method="post">
	기간 검색 :
	<input type="text" name="date_min"> ~
	<input type="text" name="date_max"><br/>
	상품명 : <input type="text" name="g_name"><br/>
	가격 검색 :
	<input type="text" name="price_min"> ~
	<input type="text" name="price_max"><br/>
	<select name="sort">
		<option value="asc" selected="selected">가격 오름차순</option>
		<option value="desc">가격 내림차순</option>
	</select><br/>
	<button type="submit">검색버튼</button>
</form>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>