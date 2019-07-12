<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Gdao"%>

<%	request.setCharacterEncoding("euc-kr");%>
<jsp:useBean id="g" class="kr.or.ksmart.dto.Goods"/>
<jsp:setProperty name="g" property="*"/>

<%
	Gdao dao = new Gdao();
	dao.gUpdate(g);		// 수정 폼에서 변경한 정보가 있는 Goods 객체를 넘겨주면서 해당 레코드 수정
	response.sendRedirect(request.getContextPath() + "/goods/goods_search_form.jsp");
%>


