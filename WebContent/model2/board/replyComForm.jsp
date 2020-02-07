<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%--
    	답변 글 쓰기 화면
    		- 입력 화면 표시
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>답 댓글 쓰기</title>
<link rel="stylesheet" href="../../css/main.css">
</head>
<body>
<form action="replyCom.do" method="post" name="f">
	<input type="hidden" name="no" value="${param.no}">
	<input type="hidden" name="num" value="${param.num}">
	<input type="hidden" name="code" value="${param.code}">
	<input type="hidden" name="ref" value="${param.ref}">
	<input type="hidden" name="reflevel" value="${param.reflevel}">
	<input type="hidden" name="refstep" value="${param.refstep}">
	<table>
		<caption>답 댓글작성</caption>
		<tr><td>아이디</td> <td><input type="text" name="id" value="${sessionScope.login}" readonly></td></tr>
		 <tr><td>내용</td> <td ><textarea rows="15" name="content"></textarea></td></tr>
		 <tr><td colspan="2"><a href="javascript:document.f.submit()">[댓글등록]</a></td></tr>
	</table>
</form>
</body>
</html>