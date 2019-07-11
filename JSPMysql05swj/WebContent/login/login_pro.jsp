<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.ksmart.dao.Mdao"%>
<%@ page import="kr.or.ksmart.dto.User"%>

<%
	request.setCharacterEncoding("euc-kr");
	String in_id = request.getParameter("id");
	String in_pw = request.getParameter("pw");
	
	System.out.println(in_id + "<- in_id");
	System.out.println(in_pw + "<- in_pw");
	
	Mdao dao = new Mdao();
	String alert = dao.mLoginCheck(in_id, in_pw);
	
	if(alert.equals("로그인성공")) {
		User user = dao.mGetForSession(in_id);
		
		session.setAttribute("S_ID", in_id);
		session.setAttribute("S_NAME", user.getU_name());
		session.setAttribute("S_LEVEL", user.getU_level());
	}
	//response.sendRedirect(request.getContextPath()+"/index.jsp");
%>
<script type="text/javascript">
	alert('<%= alert %>');
	location.href='<%= request.getContextPath()%>/index.jsp';
</script>


