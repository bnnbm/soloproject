<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<jsp:useBean id="day" class="java.util.Date" />
<fmt:formatDate var="today" value="${day}" pattern="yyyyMMdd"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table class="w3-table-all" style="font-size:13px;">
<tr>
   <th width="8%">NO</th>
   <th width="8%">제목</th>
   <th width="8%">작성자</th>
</tr>
<c:forEach var="b" items="${board}">
<tr>
   <td>${b.num}</td>
   <td>${b.subject}</td>
   <td>${b.id}</td>
</tr>
</c:forEach>
</table>
</body>
</html>