<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>댓글작성</title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
	function comment_submit() {
		f = document.f;
		if(f.content.value==""){
			alert("내용을 입력하세요.");
			f.content.focus();
			return;
		}
		f.submit();
	}
</script>
</head>
<body>
<form action="writeCom.do" method="post" name="f">
<input type="hidden" name="num" value="${param.num}">
<input type="hidden" name="code" value="${param.code}">
	<table>
		<caption>댓글작성</caption>
		<tr><td>아이디</td> <td><input type="text" name="id" value="${sessionScope.login}" readonly></td></tr>
		 <tr><td>내용</td> <td ><textarea rows="15" name="content"></textarea></td></tr>
		 <tr><td colspan="2"><a href="javascript:comment_submit()">[댓글등록]</a></td></tr>
	</table>
</form>
</body>
</html>