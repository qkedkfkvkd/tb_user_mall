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
	// 입력한 아이디와 비밀번호를 넘겨줘서 로그인 성공시 "로그인성공", 비번 불일치시 "비번불일치",
	// 아이디 불일치시 "아이디불일치"의 문자열을 리턴하는 메소드 호출
	
	if(alert.equals("로그인성공")) {	// 아이디와 비번이 데이터베이스에 있는 정보와 일치할 경우
		User user = dao.mGetForSession(in_id);
		// 이름과 권한을 얻어보기 위하여 아이디로 이름과 권한 값을 조회하여 객체를 리턴하는 메소드 호출
		
		session.setAttribute("S_ID", in_id);
		session.setAttribute("S_NAME", user.getU_name());		// 이름값 저장
		session.setAttribute("S_LEVEL", user.getU_level());		// 권한값 저장
	}
	//response.sendRedirect(request.getContextPath()+"/index.jsp");
%>
<script type="text/javascript">
	alert('<%= alert %>');
	location.href='<%= request.getContextPath()%>/index.jsp';
</script>


