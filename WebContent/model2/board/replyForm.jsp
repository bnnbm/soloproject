<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%--
    	답변 글 쓰기 화면
    		- 입력 화면 표시
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 답글 쓰기</title>
<link rel="stylesheet" href="../../css/main.css">
</head>
<body>
<form action="reply.do" method="post" name="f">
	<input type="hidden" name="num" value="${b.num}">
	<input type="hidden" name="ref" value="${b.ref}">
	<input type="hidden" name="reflevel" value="${b.reflevel}">
	<input type="hidden" name="refstep" value="${b.refstep}">
	<input type="hidden" name="code" value="${param.code}">
	<table><caption>게시판 답글 등록</caption>
		<tr><td>아이디</td> <td><input type="text" name="id" value="${sessionScope.login}" readonly></td></tr>
		<tr><td>게시판</td> <td><input type="text" 
		<c:if test="${param.code==1}"> value="openmic"</c:if> 
		<c:if test="${param.code==2}"> value="tip"</c:if> 
		<c:if test="${param.code==3}"> value="notice"</c:if> 
		readonly></tr>
		<tr><td >제목</td><td><input type="text" name="subject" value="RE:${b.subject}"></td></tr>
		<tr><td>내용</td><td><textarea name="content" rows="15"></textarea></td></tr>
		<tr><td colspan="2"><a href="javascript:document.f.submit()">[답변글등록]</a></td></tr>
	</table>
</form>
</body>
</html>