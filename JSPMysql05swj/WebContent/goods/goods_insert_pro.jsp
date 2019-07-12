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
	dao.gInsert(g);		// 상품 등록 폼에서 입력된 정보가 있는 Goods 객체를 매개변수로 넘겨주며 상품 추가 메소드 호출
	response.sendRedirect(request.getContextPath() + "/goods/goods_list.jsp");
%>