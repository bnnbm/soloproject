<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="day" class="java.util.Date" />
<fmt:formatDate var="today" value="${day}" pattern="yyyyMMdd"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խù� �󼼺���</title>
<link rel="stylesheet" href="../../css/main.css">
<script>
	function listcall(page) {
		document.sf.pageNum.value = page;
		document.sf.submit();
	}
	function up(no,num,name,content,code){
		if(confirm("����� �����Ͻðڽ��ϱ�?")){
			location.href="updateComForm.do?no=" + no + "&num=" + num + "&name=" + name + "&content=" + content + "&code="+code;
			
		}
	}
	function recom(no,num,ref,reflevel,refstep,code){
			location.href="replyComForm.do?no=" + no + "&num=" + num + "&ref="+ ref + "&reflevel="+ reflevel + "&refstep="+ refstep+"&code=" + code;
	}
	function del(no,num){
		if(confirm("����� �����Ͻðڽ��ϱ�?")){
			location.href="deleteCom.do?no=" + no +"&num=" + num;
		}
	}
</script>
</head>
<body>
<form action="info.do" method="post">
<input type="hidden" name="num" value="${b.num}">
<input type="hidden" name="id" value="${b.id}">

<table style="font-weight:bold">
<caption>�Խù� �� ����</caption>
</table>
<p>
<table>
<tr><td colspan="4">${b.subject}</td>
	<td>
	<c:if test="${b.code == 1}">
		<a href="list.do?code=1" style="font-size:13px">openmic</a> 
	</c:if>
	<c:if test="${b.code == 2}">
		<a href="list.do?code=2" style="font-size:13px">tip</a> 
	</c:if>
	<c:if test="${b.code == 3}">
		<a href="list.do?code=3" style="font-size:13px">notice</a> 
	</c:if>
	</td></tr>
	
<tr>
	<td>�ۼ���</td>
	<td colspan="2" style="text-align: left;">${b.id}</td>
	<td>��ȸ<a style="color: red">${b.readcnt}</a>&nbsp;&nbsp;
		���<a style="color: red">${commentcount}</a></td>
	<td style="width:40%;">
		<fmt:formatDate var="rdate" value="${b.regdate}" pattern="yyyyMMdd"/>
				<c:if test="${today == rdate}">
					<fmt:formatDate value="${b.regdate}" pattern="HH:mm" />
				</c:if>
				<c:if test="${today != rdate}">
					<fmt:formatDate value="${b.regdate}" pattern="yyyy.MM.dd" />
				</c:if>
	</td>
</tr>
<tr><td style="width:20%;">����</td><td colspan="4">
	<table style="width:100%; height:250px;">
	<tr><td style="border-width: 0px; vertical-align: top; text-align: left;">${b.content}</td></tr>
	</table></td></tr>
	
<tr><td>÷������</td>
	<td style="text-align: left; font-size:13px;" colspan="4">
	<c:if test="${empty b.file1}">÷������ ����</c:if>
	<c:if test="${!empty b.file1}"><a href="file/${b.file1}">${b.file1}</a></c:if>
	</td></tr>

</table>
</form>


<!-- ���ƿ� -->
<form action="likecheck.do" method="post">
<input type="hidden" name="id" value='${sessionScope.login}'>
<input type="hidden" name="num" value='${param.num}'>
<input type="hidden" name="code" value="${b.code}">
<input type="hidden" name="likenum" value="1">
<center><tr><button class="likebtn" id="btn" type="submit"><img class="btn-img" src="img/good.jpg" style="width: 100px; height: 100px; background-clolor:white"></button></tr></center>
</form>
<center><tr><h2>${likecnt}</h2></tr></center>

<p>
<form action="writeCom.do">
<input type="hidden" name="code" value="${b.code}">
<input type="hidden" name="num" value="${b.num}">
<input type="hidden" name="id" value="${sessionScope.login}">
	<table>
		<tr><td style="text-align: left;">�ۼ��� : <a style="font-size: 18px;">${sessionScope.login}</a>��<br>
				<input type="text" name="content" style="width:90%;  height: 90px; " ><input type="submit" class="button" style="height: 80px; width: 80px" value="���"></td></tr>
	</table>
</form>
<p>
<form action="info.do" method="post" name="sf">
<input type="hidden" name="pageNum" value="1">
<table>
<!----------------- ��ۺκ� ------------------------->
	<c:if test="${commentcount == 0}">
		<tr><td colspan="5">��ϵ� ����� �����ϴ�.</td></tr>
	</c:if>
	
	
	<c:if test="${commentcount > 0}">  
		<c:forEach var="c" items="${list}" >
			<input type="hidden" name="no" value="${c.no}">
			<input type="hidden" name="num" value="${c.num}">
			<input type="hidden" name="code" value="${c.code}">
			<input type="hidden" name="name" value="${c.name}">
			<input type="hidden" name="content" value="${c.content}">
			
			<tr><td style="width:20%;">
					<c:if test="${c.reflevel > 0}">
						<c:forEach var="i" begin="2" end="${c.reflevel}">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>��
					</c:if>id: ${c.id}</td>
				
				<td style="width:50%; text-align: left;" >
				${c.content}&nbsp;&nbsp;
				
					<fmt:formatDate var="rdate" value="${c.regdate}" pattern="yyyyMMdd"/>
					<c:if test="${today == rdate}">
						<a style="color: red; font-size: 13px;">N</a>
					</c:if>
				
					<a href="javascript:recom('${c.no}','${c.num}','${c.ref}','${c.reflevel}','${c.refstep}','${b.code}')">[���]</td>
				<td>
					<fmt:formatDate var="rdate" value="${c.regdate}" pattern="yyyyMMdd"/>
					<c:if test="${today == rdate}">
						<fmt:formatDate value="${c.regdate}" pattern="HH:mm" />
					</c:if>
					<c:if test="${today != rdate}">
						<fmt:formatDate value="${c.regdate}" pattern="yyyy.MM.dd" />
					</c:if>
				
				</td>
				<td style="font-size:13px"> 
					<c:if test="${sessionScope.login == c.id}">
						<a href="javascript:up('${c.no}','${c.num}','${c.name}','${c.content}','${c.code}')">[����]</a>
						<a href="javascript:del('${c.no}','${c.num}')">[����]</a>
					</c:if>
				</td></tr>
		</c:forEach>		
		</c:if>
		
		<%-- ������ ó�� �κ� --%>
		<tr><td colspan="5">
			<div class="w3-container">
			<c:if test="${pageNum <= 1}"><a>��</a></c:if>
			<c:if test="${pageNum > 1}">
				<a href="javascript:listcall(${pageNum - 1})">��</a>
			</c:if>
			
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == pageNum}"><a>[${a}]</a></c:if>
				<c:if test="${a != pageNum}">
					<a href="javascript:listcall(${a})">[${a}]</a></c:if>
			</c:forEach>
			
			<c:if test="${pageNum >= maxpage}"><a >��</a></c:if>
			<c:if test="${pageNum < maxpage}">
				<a href="javascript:listcall(${pageNum + 1})">��</a></c:if>
			</div></td></tr>
			
		<%----------------%>
</table>
<p>
<table>
		
<!-------------------------------------------------->

<tr><td colspan="5">
	<a href="replyForm.do?code=${b.code}&num=${b.num}">[�亯]</a>
	
	<!-- �����ڰ� �ƴϸ鼭 �ڽ��� �� �Խñ� -->
	<c:if test="${!sessionScope.login.equals('admin')}">
		<c:if test="${sessionScope.login == b.id}">
			<a href="updateForm.do?code=${b.code}&num=${b.num}">[����]</a>
			<a href="delete.do?code=${b.code}&num=${b.num}">[����]</a>
		</c:if>
	</c:if>
	
	<!-- �����ڶ�� -->
	<c:if test="${sessionScope.login.equals('admin')}">
		<a href="updateForm.do?code=${b.code}&num=${b.num}">[����]</a>
		<a href="delete.do?code=${b.code}&num=${b.num}">[����]</a>
	</c:if>
	
	<a href="list.do?code=${b.code}">[���]</a>
	</td></tr>
</table>
</form>
</body>
</html>