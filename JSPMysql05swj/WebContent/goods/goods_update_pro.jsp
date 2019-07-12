<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Gdao"%>

<%	request.setCharacterEncoding("euc-kr");%>
<jsp:useBean id="g" class="kr.or.ksmart.dto.Goods"/>
<jsp:setProperty name="g" property="*"/>

<%
	Gdao dao = new Gdao();
	dao.gUpdate(g);
	response.sendRedirect(request.getContextPath() + "/goods/goods_search_form.jsp");
%>


