<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
<%@ include file="common/header.jsp" %>
<%@ include file="common/nav.jsp" %>
<main>
	<h1>index</h1>
	<h2>텍스트</h2>
	<h2>java 코드 호출 테스트</h2>
	<c:if test="${empty member}">
		<a href="member/register">회원가입</a>
		<a href="member/login">로그인</a>
	</c:if>
	<c:if test="${not empty member}">
		<span>${member.name} 님 환영합니다.</span>
		<a href="member/logout">로그아웃</a>
	</c:if>
</main>


<%@ include file="common/footer.jsp" %>
<script>
	$("p").attr("", function () {
		return "";
	});
</script>
</body>
</html>