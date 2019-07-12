<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Mdao"%>
<%@ page import="kr.or.ksmart.driverdb.DriverDB"%>
<%@ page import="java.sql.Connection"%>


<%	request.setCharacterEncoding("euc-kr"); %>
<jsp:useBean id="m" class="kr.or.ksmart.dto.User"/>
<jsp:setProperty name="m" property="*"/>


<%
	DriverDB dbcon = new DriverDB();
	Connection conn = dbcon.driverDbcon();
	Mdao dao = new Mdao();
	dao.mInsert(m, conn);
	// 회원 추가 폼에서 입력된 정보가 있는 User 객체를 매개변수로 넘겨주며 회원 추가 메소드 호출
	
	//response.sendRedirect(request.getContextPath() + "/userlist/user_list.jsp");
	response.sendRedirect(request.getContextPath() + "/usersearch/user_search_list.jsp");
	
%>