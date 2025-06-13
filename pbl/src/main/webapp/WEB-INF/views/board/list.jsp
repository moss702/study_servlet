<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>
    <div class="container p-0">
        <main>
            <div class="clearfix py-0">
                <a href="board_write.html" class="btn btn-secondary btn-sm float-end"><i class="fa-solid fa-pen"></i> 글쓰기</a>
            </div>
            <div class="list-group">
            <div class="list-group-item">
                <div class="row text-center align-items-center small text-muted">
                    <div class="col-1 small">글번호</div>
                    <div class="col-1 small">카테고리</div>
                    <div class="col text-start small">제목</div>
                    <div class="col-1 small">날짜</div>
                    <div class="col-1 small">조회수</div>
                </div>
            </div>
           <c:forEach items="${boards}" var="board">
            <a href="view?bno=${board.bno}" class="list-group-item list-group-item-action list-group-item-secondary">
	            <div class="row text-center align-items-center small text-muted">
	                <div class="col-1 small">${board.bno}</div>
	                <div class="col-1 small">${board.cno}</div>
	                <div class="col text-start text-black">${board.title}<span class="small text-danger fw-bold"> 1</span></div>
	                <div class="col-1 small">
		                <fmt:parseDate value= "${board.regdate}" pattern="yyyy-MM-dd HH:mm:ss" var="parseDate" />
		                <fmt:formatDate value="${parseDate}" pattern="yy.MM.dd" />
	                </div>
	                <div class="col-1 small">${board.cnt}</div>
	            </div>
            </a>
            </c:forEach>
        </main>
    </div>
<%@ include file="../common/footer.jsp" %>
</body>
</html>