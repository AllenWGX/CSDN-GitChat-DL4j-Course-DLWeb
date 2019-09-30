<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 
<html> 
<head> 
<base href="<%=basePath%>"> 
<title>Picture Upload</title> 
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0"> 
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> 
<meta http-equiv="description" content="This is my page"> 
<!-- 
<link rel="stylesheet" type="text/css" href="styles.css"> 
--> 
</head> 
<body> 
<form action="/maven-web-demo/upload" method="post" enctype="multipart/form-data"> 
	Please Upload Picture:
	<input type="file" name="uploadFile"/>
	<input type="submit" value="Upload"/> 
</form> 
<!--<p><c:out value="${pictureName}" /> </p>-->
<p>Predict Result：${label}</p>

<img src="${pageContext.request.contextPath}/imgs/temp${timestamp}.jpg"/>

</body> 
</html> 
