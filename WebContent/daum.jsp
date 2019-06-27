<%@page import="com.gg.vo.MovieVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="menu.jsp" %>
<%
	ArrayList<MovieVO> list = (ArrayList<MovieVO>)request.getAttribute("mvlist");
%>
<div class="container-fluid">
다음 영화 리스트<br>
<% for (MovieVO vo : list) { %>
	<a href="<%=vo.getLnk() %>">
	<img width="240" height="320" src="<%=vo.getImg() %>">
	</a><br>
	<%=vo.getTitle() %><br>
<% } %>
</div>

</body>
</html>
    