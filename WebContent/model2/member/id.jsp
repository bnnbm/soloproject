<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/member/id.jsp --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>id ã��</title>
<script type="text/javascript">
   function sendID(id){
	  opener.document.f.id.value=id
	  self.close();
	 }
</script>
</head>
<body>
<table>
   <tr><th>���̵� </th>
   <td>${id}**</td></tr>
   <tr><td coslpan="2"><input type="button" value="���̵�����" onclick="sendID('${id}')"></td></tr>
</table>
</body>
</html>