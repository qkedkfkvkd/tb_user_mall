<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Mdao"%>

<%
	String u_id = request.getParameter("send_id");
	Mdao dao = new Mdao();
	dao.mDelete(u_id);
	//response.sendRedirect(request.getContextPath() + "/userlist/user_list.jsp");
	response.sendRedirect(request.getContextPath() + "/usersearch/user_search_list.jsp");
%>