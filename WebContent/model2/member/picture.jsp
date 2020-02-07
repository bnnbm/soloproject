<%@page import="java.io.File"%>
<%@page import="java.io.IOException"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- /WebContent/model2/member/picture.jsp 
   1. 파일 업로드 => java
   2. opener 화면에 결과 전달 => javascript
   3. 현재 화면 close() => javascript
--%>       
<script type="text/javascript">
   img = opener.document.getElementById("pic");
   img.src = "picture/${fname}";
   opener.document.f.picture.value="${fname}";
   self.close();
</script>