<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<script>
if(confirm("${msg}")){
	location.href="${url}";
}
	//alert("${msg}");
	//location.href = "${url}";
</script>