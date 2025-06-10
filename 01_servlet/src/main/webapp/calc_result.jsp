<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% request.setCharacterEncoding("utf-8"); %>
	
	<!-- // jsp가 html보다 빠르기 때문에 구간주석 무시
	<%	// 자바 구간입니다.. 자바 코드 사용합니다..
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
	%>
 	-->
	<p><%=num1 + num2 %></p>
	<p><%=num1 - num2 %></p>
	<p><%=num1 * num2 %></p>
	<p><%=num1 / num2 %></p>
	
</body>
</html>