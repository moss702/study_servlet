<%@page import="java.text.SimpleDateFormat"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import= "java.util.*" %>
<%--
	<%@  %> : directive : 지시어
	<%   %> : scriptlet : 스크립트 구문
	<%!  %> : declare : 선언부
	<%=  %> : expression : 표현식
	
	contentType : 브라우저에서 해석될 MIME TYPE * charset 생략시 기본값 iso-8859-1
	pageEncoding : file이 어떤 charset으로 저장될지 선택 * 기본값 ms949
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><%=new Date() %></h2>
	<h3><%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %></h3>
	<h3>한글한글</h3>
	<%!
		static int si = 10;
		String m() {
			return "abcd";
		}
	%>
	<h3><%=m() %></h3>
</body>
</html>