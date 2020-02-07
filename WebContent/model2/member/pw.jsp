<%@page import="model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>비밀번호 찾기</title>
<script type="text/javascript">
function self_close() {
	   self.close();
   }
</script>
</head>
<body>
<table>
   <tr><th>비밀번호 </th>
   <td>**${pass}</td></tr>
   <input type="button" value="닫기" onclick="self_close();">
</table>
</body>
</html>
