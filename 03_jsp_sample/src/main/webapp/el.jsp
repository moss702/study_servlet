<%@page import="domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><%=page == this %></h2>
	<h2><%=request.getSession() == session %></h2>
	<h2><%=request.getServletContext() == application %></h2>
	<!-- 여기서의 this ? el.jsp -->
	<%
		
		//영역객체
		//page request session application
		pageContext.setAttribute("value", 10);
		request.setAttribute("value", 20);
		session.setAttribute("value", 30);
		application.setAttribute("value", 40);
		
	%>
	
	<h3>${value}</h3>
	<!-- 가장 가까운 스코프 pageContext의 value가 나온다. -->
	
	<h3>${sessionScope.value}</h3>
	
	<%
 		request.setAttribute("myValue", new Member());
	%>
	<h3>normal exp : <%=((Member)request.getAttribute("myValue")).getName() %></h3>
	<h3>exp lang : ${myValue["name"]}</h3>
	
	<h3>${'1'+"2"}</h3> <!-- 3 -->
	<h3>${5 / 2}</h3> <!-- 2.5 -->
<%-- 	<h3>${5 div 2}</h3> --%>
	<h3>${5 mod 2}</h3>
	<h3>${myValue.name == '새떵이'}</h3>
	<h3>${myValue.name eq '새떵이'}</h3>
	<h3>${myValue.name != '새떵이'}</h3>
	<h3>${myValue.name ne '새떵이'}</h3> <!-- not equal -->
	<h3>${empty ''}</h3>
	<h3>${empty null}</h3>
	<h3>${!empty null}</h3>
	<h3>${not empty null}</h3>
	
</body>
</html>