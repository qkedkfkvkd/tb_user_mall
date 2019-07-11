<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>


<%@ page import="kr.or.ksmart.dao.Gdao"%>

<%	request.setCharacterEncoding("euc-kr");%>
<jsp:useBean id="g" class="kr.or.ksmart.dto.Goods"/>
<jsp:setProperty name="g" property="*"/>

<%
	Gdao dao = new Gdao();
	dao.gInsert(g);
	response.sendRedirect(request.getContextPath() + "/goods/goods_list.jsp");
%>