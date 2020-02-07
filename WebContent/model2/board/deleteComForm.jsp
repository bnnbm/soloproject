<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 삭제 화면</title>
<link rel="stylesheet" href="../../css/main.css">
</head>
<body>
<form action="deleteCom.do" name="f" method="post">
<input type="hidden" name="num" value="${param.no}">
<table>
	<caption>댓글 삭제 화면</caption>
	<tr><td>댓글 비밀번호</td>
		<td><input type="password" name="pass"></td></tr>
	<tr><td colspan="2">
		<input type="submit" value="댓글 삭제"></td></tr>
</table> 
</form>
</body>
</html>