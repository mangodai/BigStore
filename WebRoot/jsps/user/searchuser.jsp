<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/searchuser.css">
	<link rel="stylesheet" type="text/css" href="css/body.css">
  </head>
  
  <body>
  <div class="classmain">
  	<div class="search">
  		<h3>以ID号或姓名查询买家或卖家的信息</h3>
  		<p style="font-weight: 900; color: red">${msg }</p>
  	<form action="<c:url value='/UserServlet' />" method="post">
  	<input type="hidden" name="method" value="search"/>
	<input type="text" maxlength="20" name="index">
	<input type="submit" value="确认搜索" />
	</form>
  	<table class="usertable">
		<tr>
			<th>姓名</th><th>身份</th>
		</tr>
		<tr>
			<td>${userList.username}</td>
			<td>${userList.privilege }</td>
		</tr>
	</table>
  	</div>
  </div>
  </body>
</html>
