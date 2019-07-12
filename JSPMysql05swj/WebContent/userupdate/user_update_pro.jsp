<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Mdao"%>

<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="m" class="kr.or.ksmart.dto.User"/>
<jsp:setProperty name="m" property="*"/>


<%
	Mdao dao = new Mdao();
	dao.mUpdate(m);		// 수정 폼에서 변경한 정보가 있는 User 객체를 넘겨주면서 해당 레코드 수정
	response.sendRedirect(request.getContextPath() + "/usersearch/user_search_list.jsp");
%>