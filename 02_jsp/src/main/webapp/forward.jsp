<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>여기에 있는 내 응답은 안보인다.</h1>
<h1>포워드 된 시점에 내껀 다 갖다버림</h1>
<%
	System.out.println(request.getParameter("v"));
	request.getRequestDispatcher("index.jsp").forward(request, response);
%>
</body>
</html>