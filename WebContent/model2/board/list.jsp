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
<title>게시물 목록 보기</title>
<link rel="stylesheet" href="../../css/main.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script type="text/javascript">
	function listcall(page) {
		document.sf.pageNum.value = page;
		document.sf.submit();
	}
</script>
</head>
<body>
<table style="font-weight:bold">
	<c:if test="${code == 1}">
		<caption>openmic</caption>
	</c:if>
	<c:if test="${code == 2}">
		<caption>tip</caption>
	</c:if>
	<c:if test="${code == 3}">
		<caption>notice</caption>
    </c:if>
</table>
	<p>
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
			 <c:set var="boardnum" value="${boardnum-1}"/>
				<td style="text-align: left">
				
				<c:if test="${b.reflevel > 0}">
					<c:forEach var="i" begin="2" end="${b.reflevel}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>└
				</c:if>
					<a href="info.do?code=${b.code}&num=${b.num}">${b.subject}</a><a style="color: red; font-size: 13px">&nbsp;(${b.comcnt})
					
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
				
				<td>${b.readcnt}</td>

		</c:forEach>		
		</c:if>
		
		<%-- 페이지 처리 부분 --%>
		<tr><td colspan="5">
			<div class="w3-container">
			<c:if test="${pageNum <= 1}"><a class="w3-button w3-white w3-border">＜</a></c:if>
			<c:if test="${pageNum > 1}">
				<a class="w3-button w3-white w3-border" href="javascript:listcall(${pageNum - 1})">＜</a>
			</c:if>
			
			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == pageNum}"><a class="w3-button w3-light-gray w3-border">${a}</a></c:if>
				<c:if test="${a != pageNum}">
					<a class="w3-button w3-white w3-border" href="javascript:listcall(${a})">${a}</a></c:if>
			</c:forEach>
			
			<c:if test="${pageNum >= maxpage}"><a class="w3-button w3-white w3-border">＞</a></c:if>
			<c:if test="${pageNum < maxpage}">
				<a class="w3-button w3-white w3-border" href="javascript:listcall(${pageNum + 1})">＞</a></c:if>
			</div>
		<%----------------%>
	<c:if test="${code == 3}">
		<c:if test="${sessionScope.login.equals('admin')}">
			<tr><td colspan="5" style="text-align:right"><a href="writeForm.do?code=${code}">[글쓰기]</a></td></tr>
		</c:if>
	</c:if>
	<c:if test="${code != 3}">

			<tr><td colspan="5" style="text-align:right"><a href="writeForm.do?code=${code}">[글쓰기]</a></td></tr>

	</c:if>
</table>
<p>
<form action="list.do?code=${code}" method="post" name="sf">
<input type="hidden" name="pageNum" value="1">
<input type="hidden" name="code" value="${param.code}">
<input type="hidden" name="id" value="${sessionScope.login}">

	<table><tr><td style="boader-width: 0px;">
		<select name="column"><option value="">선택하세요</option>
			<option value="subject">제목</option>
			<option value="id">작성자</option>
			<option value="content">내용</option>
			<option value="subject,content">제목+내용</option>
			<option value="id,content">작성자+내용</option>
			
		</select>
		<script>
			document.sf.column.value = "${param.column}";
		</script>
		<input type="text" name="find" value="${param.find}" style="width:300px;">
		<input type="submit" value="검색"></td></tr>
	</table>
</form>
</body>
</html>