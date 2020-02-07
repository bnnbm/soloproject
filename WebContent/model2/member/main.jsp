<%@page import="java.util.List"%>
<%@page import="model.BoardDao"%>
<%@page import="model.Board"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="day" class="java.util.Date" />
<fmt:formatDate var="today" value="${day}" pattern="yyyyMMdd"/>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
<title>Music Cloud</title>
<link rel="stylesheet" href="../../css/main.css">
</head>
<body>
<form action="mainform.do" method="post" name="f">
  <!-- First Photo Grid-->
  <div class="w3-row-padding">
    <div class="w3-third w3-container w3-margin-bottom w3-center">
      <img src="../member/jpg.png" style="width:50%" class="w3-hover-opacity w3-center">
    </div>
  </div>
  
  <!-- Second Photo Grid-->
<div class="w3-half">
   <div class="w3-container w3-padding-16">
      <div class="w3-left"><i class="fa fa-comment w3-xxxlarge"></i></div>
      
  <script type="text/javascript">
   function listdo(page) {
      document.sf.pageNum.value=page;
      document.sf.submit();
   }
</script>
<br>
<table><h2>openmic</h2></table>
<table>
	<c:if test="${boardcount == 0}">
	<tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
	</c:if>
	
	<c:if test="${boardcount > 0}">
		<tr><td colspan="5" style="text-align:right">글 개수: ${boardcount}</td></tr>
		<tr><th width="8%">NO</th> <th width="50%">제목</th>
			<th width="14%">작성자</th> <th width="20%">작성일</th>
			<th width="8%">조회</th></tr>
		
		<c:forEach var="b" items="${list}" >
		<input type="hidden" name="num" value="${b.num}">
			<tr><td>${boardnum}</td>
				<td style="text-align: left"> <c:set var="boardnum" value="${boardnum-1}"/>
				
				<c:if test="${b.reflevel > 0}">
					<c:forEach var="i" begin="2" end="${b.reflevel}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>└
				</c:if>
					<a href="info.do?code=1&num=${b.num}">${b.subject}</a><a style="color: red; font-size: 13px">&nbsp;(${b.comcnt})
					
						<fmt:formatDate var="rdate" value="${b.regdate}" pattern="yyyyMMdd"/>
						<c:if test="${today == rdate}">N</c:if></a>
					
						<c:if test="${!empty b.file1}">
							<a href="file/${b.file1}"> <img src="img/clip.png" style="width: 15px; height: 15px;"></a>
						</c:if>
					</td>
					
					
					
				<td>${b.id}</td>
				
				<td>
				<fmt:formatDate var="rdate" value="${b.regdate}" pattern="yyyyMMdd"/>
				<c:if test="${today == rdate}">
					<fmt:formatDate value="${b.regdate}" pattern="HH:mm" />
				</c:if>
				<c:if test="${today != rdate}">
					<fmt:formatDate value="${b.regdate}" pattern="yy.MM.dd" />
				</c:if>
				</td>
				
				<td>${b.readcnt}</td></tr>
		</c:forEach>		
		</c:if>
		
</table>
</div>
   </div>




<div class="w3-half">
   <div class="w3-container w3-padding-16">
      <div class="w3-left"><i class="fa fa-comment w3-xxxlarge"></i></div>
      
  <script type="text/javascript">
   function listdo(page) {
      document.sf.pageNum.value=page;
      document.sf.submit();
   }
</script>
<br>
<table><h2>notice</h2></table>
<table>
	<c:if test="${boardcount3 == 0}">
	<tr><td colspan="5">등록된 게시글이 없습니다.</td></tr>
	</c:if>
	
	<c:if test="${boardcount3 > 0}">
		<tr><td colspan="5" style="text-align:right">글 개수: ${boardcount3}</td></tr>
		<tr><th width="8%">NO</th> <th width="50%">제목</th>
			<th width="14%">작성자</th> <th width="20%">작성일</th>
			<th width="8%">조회</th></tr>
		
		<c:forEach var="b" items="${list3}" >
		<input type="hidden" name="num" value="${b.num}">
			<tr><td>${boardnum}</td>
				<td style="text-align: left"> <c:set var="boardnum" value="${boardnum-1}"/>
				
				<c:if test="${b.reflevel > 0}">
					<c:forEach var="i" begin="2" end="${b.reflevel}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>└
				</c:if>
					<a href="info.do?code=3&num=${b.num}">${b.subject}</a><a style="color: red; font-size: 13px">&nbsp;(${b.comcnt})
					
						<fmt:formatDate var="rdate" value="${b.regdate}" pattern="yyyyMMdd"/>
						<c:if test="${today == rdate}">N</c:if></a>
					
						<c:if test="${!empty b.file1}">
							<a href="file/${b.file1}"> <img src="img/clip.png" style="width: 15px; height: 15px;"></a>
						</c:if>
					</td>
					
					
					
				<td>${b.id}</td>
				
				<td>
				<fmt:formatDate var="rdate" value="${b.regdate}" pattern="yyyyMMdd"/>
				<c:if test="${today == rdate}">
					<fmt:formatDate value="${b.regdate}" pattern="HH:mm" />
				</c:if>
				<c:if test="${today != rdate}">
					<fmt:formatDate value="${b.regdate}" pattern="yy.MM.dd" />
				</c:if>
				</td>
				
				<td>${b.readcnt}</td></tr>
		</c:forEach>		
		</c:if>
		
</table>
</div>
   </div>






</form>
</body>
</html>