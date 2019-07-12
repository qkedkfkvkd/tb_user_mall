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

<!-- jQuery UI ����ȭ ������ ���� ���̺귯�� (�ٱ���) -->
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/jquery-ui-i18n.min.js"></script>

<!-- datepicker �ѱ���� -->
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script> -->
<script>
$(function() {
	$.datepicker.setDefaults($.datepicker.regional['ko']); //datepicker �ѱ���� ����ϱ� ���� ����
	$('#g_date_min').datepicker();
	$('#g_date_max').datepicker();
});
</script>
</head>

<body>
<%@ include file="/module/top.jsp" %>
<%@ include file="/module/left.jsp" %>

<h3>���������� �̿��Ͽ� �˻��ϱ�</h3>
<form action="<%=request.getContextPath()%>/goods/goods_search_list2.jsp" method="post">
	�Ⱓ �˻� :
	<input type="text" name="date_min"> ~
	<input type="text" name="date_max"><br/>
	��ǰ�� : <input type="text" name="g_name"><br/>
	���� �˻� :
	<input type="text" name="price_min"> ~
	<input type="text" name="price_max"><br/>
	<select name="sort">
		<option value="asc" selected="selected">���� ��������</option>
		<option value="desc">���� ��������</option>
	</select><br/>
	<button type="submit">�˻���ư</button>
</form>

<%@ include file="/module/hadan.jsp" %>
</body>
</html>