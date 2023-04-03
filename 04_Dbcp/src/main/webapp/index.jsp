<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<%-- 
	동일한 주소 4번 입력한 상태
	<a href="/04_Dbcp/getAllBoardList.do">게시판</a>
	<a href="<%=request.getContextPath()%>getAllBoardList.do">게시판</a>
	
	<%
		pageContext.setAttribute("contextPath", request.getContextPath());
	%>
	
	<a href="${contextPath}/getAllBoardList.do">게시판</a> 
	
--%>

	<%--수업시간에는 이 방법으로 진행할 예정--%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />  
	<a href="${contextPath}/getAllBoardList.do">게시판</a>
	
	<a href="<c:url value="/getAllBoardList.do" />">게시판</a>
</body>
</html>