<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/decorator/decorator2.jsp --%>
<%@ taglib prefix="decorator" 
           uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<title><decorator:title /></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<decorator:head />

<script type="text/javascript" 
   src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js">
</script>
<body>
<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-wide w3-padding w3-card">
    <a href="${path}/model2/board/mainform.do" class="w3-bar-item w3-button"><img src="../member/jpg.png" style="width:10%" class="w3-hover-opacity w3-center">Music cloud</a>
    <!-- Float links to the right. Hide them on small screens -->
    <div class="w3-right w3-hide-small">
      <a class="w3-bar-item w3-button" href="${path}/model2/board/list.do?code=1">openmic</a>
      <a class="w3-bar-item w3-button" href="${path}/model2/board/list.do?code=2">tip</a>
      <a class="w3-bar-item w3-button" href="${path}/model2/board/list.do?code=3">notice</a>
      <c:if test="${!empty sessionScope.login}">
       ${sessionScope.login}님이 로그인 하셨습니다.
       <a href="${path}/model2/member/logout.me">logout</a>
       <a href="${path}/model2/member/info.me?id=${login}">memberinfo</a>
       <a href="${path}/model2/member/list.me">memberlist</a>
       </c:if>
       <c:if test="${empty sessionScope.login}">
       <a href="${path}/model2/member/loginForm.me">login</a>
       <a href="${path}/model2/member/joinForm.me">membership</a>       
       </c:if>
    </div>
  </div>
</div>


<!-- Page content --> 
<div class="w3-content w3-padding" style="max-width:1564px">

  <!-- Project Section -->
  <div class="w3-container w3-padding-32" id="projects">
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16"></h3>
  </div>

  <div class="w3-row-padding">    
  </div>

  <!-- About Section -->
  <div class="w3-container w3-padding-32" id="about">
  <div class="w3-row-padding">
    <decorator:body />
  </div>
    <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">About</h3>
    <p>구디 개인프로젝트 by 조형도
    </p>
  </div>


</body>
</html>
