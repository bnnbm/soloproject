<%@page import="model.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��й�ȣ ã��</title>
<script type="text/javascript">
function self_close() {
	   self.close();
   }
</script>
</head>
<body>
<table>
   <tr><th>��й�ȣ </th>
   <td>**${pass}</td></tr>
   <input type="button" value="�ݱ�" onclick="self_close();">
</table>
</body>
</html>
