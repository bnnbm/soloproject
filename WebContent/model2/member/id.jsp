<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/member/id.jsp --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>id 찾기</title>
<script type="text/javascript">
   function sendID(id){
	  opener.document.f.id.value=id
	  self.close();
	 }
</script>
</head>
<body>
<table>
   <tr><th>아이디 </th>
   <td>${id}**</td></tr>
   <tr><td coslpan="2"><input type="button" value="아이디전송" onclick="sendID('${id}')"></td></tr>
</table>
</body>
</html>