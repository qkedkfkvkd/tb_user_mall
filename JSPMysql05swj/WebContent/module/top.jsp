<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String S_ID = (String)session.getAttribute("S_ID");
String S_NAME = (String)session.getAttribute("S_NAME");
String S_LEVEL = (String)session.getAttribute("S_LEVEL");
System.out.println(S_NAME+"<- S_NAME top.jsp");
System.out.println(S_LEVEL+"<- S_LEVEL top.jsp");
%>
   <!-- Begin Wrapper -->
   <div id="wrapper">
   
         <!-- Begin Header -->
         <div id="header">
		 
/module/top.jsp		       상단 메뉴	<br><br>
<%
if(S_LEVEL != null){
	if(S_LEVEL.equals("구매자")){
%>
<a href="<%= request.getContextPath() %>/userinsert/user_insert_form.jsp">01 회원등록</a>
<a href="<%= request.getContextPath() %>/goods/goods_search_form.jsp">04 상품검색</a>
<a href="<%= request.getContextPath() %>/goods/goods_search_form2.jsp">05 조인쿼리 상품검색</a>
<%		
	}else if(S_LEVEL.equals("판매자")){
%>
<a href="<%= request.getContextPath() %>/userinsert/user_insert_form.jsp">01 회원등록</a>
<a href="<%= request.getContextPath() %>/goods/goods_insert_form.jsp">03 상품등록</a>
<a href="<%= request.getContextPath() %>/goods/goods_search_form.jsp">04 상품검색</a>
<a href="<%= request.getContextPath() %>/goods/goods_search_form2.jsp">05 조인쿼리 상품검색</a>
<a href="<%= request.getContextPath() %>/goods/goods_list.jsp">06 상품리스트</a>
<%		
	}else if(S_LEVEL.equals("관리자")){
%>
<a href="<%= request.getContextPath() %>/userinsert/user_insert_form.jsp">01 회원등록</a>
<a href="<%= request.getContextPath() %>/usersearch/user_search_list.jsp">02 회원검색</a>
<a href="<%= request.getContextPath() %>/goods/goods_insert_form.jsp">03 상품등록</a>
<a href="<%= request.getContextPath() %>/goods/goods_search_form.jsp">04 상품검색</a>
<a href="<%= request.getContextPath() %>/goods/goods_search_form2.jsp">05 조인쿼리 상품검색</a>
<a href="<%= request.getContextPath() %>/goods/goods_list.jsp">06 상품리스트</a>
<%		
	}
%>
	<br/><br/>
	<%= S_NAME %> 님 <%= S_LEVEL %> 권한 로그인 중
	<a href="<%= request.getContextPath() %>/login/logout.jsp">로그아웃</a>
<%	
}else{
%>
<a href="<%= request.getContextPath() %>/userinsert/user_insert_form.jsp">01회원등록	</a>
<!-- 로그인 전 화면 시작 -->
<br/><br/>
<form action="<%= request.getContextPath() %>/login/login_pro.jsp" method="post">
	아이디 : <input type="text" name="id">
	비 번   : <input type="password" name="pw">
	<input type="submit" value="로그인">
</form>
<!-- 로그인 전 화면 End -->	 
<%
}
%>
	 
	 
	       		 
			   
		 </div>
		 <!-- End Header -->
		 
		 
		 
		 
		 
		 
		 
		 
		 