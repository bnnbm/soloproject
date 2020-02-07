<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 글쓰기</title>
<link rel="stylesheet" href="../../css/main.css">
<script type="text/javascript">
	function board_submit() {
		f = document.f;
		if(f.name.value==""){
			alert("글쓴이를 입력하세요.");
			f.name.focus();
			return;
		}
		if(f.subject.value==""){
			alert("제목을 입력하세요.");
			f.subject.focus();
			return;
		}
/* 		if(f.content.value==""){
			alert("내용을 입력하세요.");
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
		<caption>게시판 글쓰기</caption>
		<tr><td>아이디</td> <td><input type="text" name="id" value="${sessionScope.login}" readonly></td></tr>
		<tr><td>게시판</td> <td><input type="text" 
		<c:if test="${param.code==1}"> value="openmic"</c:if> 
		<c:if test="${param.code==2}"> value="tip"</c:if> 
		<c:if test="${param.code==3}"> value="notice"</c:if> 
		readonly></tr>
		 <tr><td>제목</td> <td><input type="text" name="subject"></td></tr>
		 <tr><td>내용</td><td><textarea rows="15" name="content" id="content1"></textarea></td></tr>
    <script>CKEDITOR.replace("content1", {
<%-- filebrowserImageUploadUrl : 이미지 업로드 설정
     imgupload.do : 업로드를 위한 url 지정
--%>	   
     filebrowserImageUploadUrl : "imgupload.do"
   });
   </script>
		 <tr><td>첨부파일</td> <td><input type="file" name="file1"></td></tr>
		 <tr><td colspan="2"><a href="javascript:board_submit()">[게시물등록]</a></td></tr>
	</table>
</form>
</body>
</html>