<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.SQLException" %>
<%@ page import="kr.or.ksmart.driverdb.DriverDB"%>
<%
	request.setCharacterEncoding("euc-kr");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	System.out.println(id + "<- id");
	System.out.println(pw + "<- pw");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String dbid = null;
	String dbpw = null;
	String dbname = null;
	String dblevel = null;
	String alert = null;
	
	try {
		DriverDB db = new DriverDB();
		conn = db.driverDbcon();
		System.out.println(conn + "<-- conn   login_pro.jsp");
		
		if(conn != null){
			out.println("01 DB연결 성공");
		}else{
			out.println("02 DB연결 실패");
		}
		
		pstmt = conn.prepareStatement("SELECT u_id, u_pw, u_name, u_level FROM tb_user WHERE u_id=?");
		pstmt.setString(1, id);
		System.out.println(pstmt + "<-- pstmt   login_pro.jsp");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   login_pro.jsp");
		
		if(rs.next()){
			System.out.println("01_01 아이디 일치");
			
			dbid = rs.getString("u_id");
			dbpw = rs.getString("u_pw");
			dbname = rs.getString("u_name");
			dblevel = rs.getString("u_level");
			
			System.out.println(dbid + "<-- dbid   login_pro.jsp");
			System.out.println(dbpw + "<-- dbpw   login_pro.jsp");
			System.out.println(dbname + "<-- dbname   login_pro.jsp");
			System.out.println(dblevel + "<-- dblevel   login_pro.jsp");
			
			if(dbpw.equals(pw)){
				System.out.println("02_01 로그인 성공");
				System.out.println(session);
				
				//session 영역에 셋팅
				session.setAttribute("S_ID", dbid);
				session.setAttribute("S_NAME", dbname);
				session.setAttribute("S_LEVEL", dblevel);
				alert="로그인성공";
			}else{
				System.out.println("02_02 비번 불일치");
				alert="비번 불일치";
			}
		}else{
			System.out.println("01_02 아이디 불일치");
			alert="아이디 불일치";
		}
		
	} catch(SQLException ex) {
		out.println(ex.getMessage());
		ex.printStackTrace();
	} finally {
		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	}
	
	//response.sendRedirect(request.getContextPath()+"/index.jsp");
%>
<script type="text/javascript">
	alert('<%= alert %>');
	location.href='<%= request.getContextPath()%>/index.jsp';
</script>


