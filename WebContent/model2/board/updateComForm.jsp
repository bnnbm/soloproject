<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>댓글 수정 화면</title>
<link rel="stylesheet" href="../../css/main.css">
</head>
<body>
<form action="updateCom.do" method="post" name="f">
	<input type="hidden" name="no" value="${param.no}">
	<input type="hidden" name="num" value="${param.num}">
	<input type="hidden" name="code" value="${param.code}">
	<table>
		<caption>댓글수정</caption>
		<tr><td>아이디</td> <td><input type="text" name="id" value="${sessionScope.login}" readonly></td></tr>
		 <tr><td>내용</td> <td ><textarea rows="15" name="content">${param.content}</textarea></td></tr>
		 <tr><td colspan="2"><input type="submit" value="댓글수정"></td></tr>
	</table>
</form>
</body>
</html>