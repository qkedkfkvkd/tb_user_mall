<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Mdao"%>

<%
	String u_id = request.getParameter("send_id");
	Mdao dao = new Mdao();
	dao.mDelete(u_id);		// 넘겨 받은 회원 아이디에 해당하는 회원 레코드 삭제 메소드 호출
	//response.sendRedirect(request.getContextPath() + "/userlist/user_list.jsp");
	response.sendRedirect(request.getContextPath() + "/usersearch/user_search_list.jsp");
%>