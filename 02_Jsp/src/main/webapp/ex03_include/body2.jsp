<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%-- 동적 include : 포함되는 페이지에 파라미터를 전달할 수 있다. --%>
<jsp:include page="header.jsp">
	<jsp:param value="body2" name="title"/>
</jsp:include>
	<h1>body2</h1>
	
<%-- 정적 include : 항상 같은 모습의 페이지를 포함한다. (include 지시어) --%>
<%@ include file="footer.jsp" %>
