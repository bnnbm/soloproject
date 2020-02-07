<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ��� �۾���</title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
	function board_submit() {
		f = document.f;
		if(f.name.value==""){
			alert("�۾��̸� �Է��ϼ���.");
			f.name.focus();
			return;
		}
		if(f.subject.value==""){
			alert("������ �Է��ϼ���.");
			f.subject.focus();
			return;
		}
/* 		if(f.content.value==""){
			alert("������ �Է��ϼ���.");
			f.content.focus();
			return;
		} */
		f.submit();
	}
</script>
</head>
<body>
<form action="write.do" method="post" enctype="multipart/form-data" name="f">
	<input type="hidden" name="num" value="${param.num}">
	<input type="hidden" name="code" value="${param.code}">
	<table>
		<caption>�Խ��� �۾���</caption>
		<tr><td>���̵�</td> <td><input type="text" name="id" value="${sessionScope.login}" readonly></td></tr>
		<tr><td>�Խ���</td> <td><input type="text" 
		<c:if test="${param.code==1}"> value="openmic"</c:if> 
		<c:if test="${param.code==2}"> value="tip"</c:if> 
		<c:if test="${param.code==3}"> value="notice"</c:if> 
		readonly></tr>
		 <tr><td>����</td> <td><input type="text" name="subject"></td></tr>
		 <tr><td>����</td><td><textarea rows="15" name="content" id="content1"></textarea></td></tr>
    <script>CKEDITOR.replace("content1", {
<%-- filebrowserImageUploadUrl : �̹��� ���ε� ����
     imgupload.do : ���ε带 ���� url ����
--%>	   
     filebrowserImageUploadUrl : "imgupload.do"
   });
   </script>
		 <tr><td>÷������</td> <td><input type="file" name="file1"></td></tr>
		 <tr><td colspan="2"><a href="javascript:board_submit()">[�Խù����]</a></td></tr>
	</table>
</form>
</body>
</html>