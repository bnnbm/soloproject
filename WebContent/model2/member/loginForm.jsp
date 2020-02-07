<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/member/loginForm.jsp --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인 화면</title>
<link rel="stylesheet" href="../../css/hdcss.css">
<script type="text/javascript">
   function win_open(page) {
	   var op = "width=500, height=150, left=50, top=150";
	   open(page+".me","",op);
   }
</script>
</head>
<body>
<form action="login.me" method="post" name="f">
<table>
   <caption>로그인</caption>
   <tr><th>아이디</th><td><input type="text" name="id" value=""></td></tr>
   <tr><th>비밀번호</th><td><input type="password" name="pass"></td></tr>
   <tr><td colspan="2">
       <input type="submit" value="로그인">
       <input type="button" value="회원가입" onclick="location.href='joinForm.me'">
       <input type="button" value="아이디찾기" onclick="win_open('idForm')">
       <input type="button" value="비밀번호찾기" onclick="win_open('pwForm')">
   </td></tr>
</table>
</form>
</body>
</html>