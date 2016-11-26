<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'watchme.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/tablecenter.css">
<link rel="stylesheet" type="text/css" href="css/body.css">

</head>

<body>
	<div align="center" class="mainclass">
		<table width="400" border="2" cellspacing="0" cellpadding="0">
			<caption>个人信息</caption>
			<tr>
				<th scope="row">序号:</th>
				<td>${sessionScope.session_user.uid }</td>
			</tr> 
			<tr>
				<th scope="row">姓名：</th>
				<td>${sessionScope.session_user.username }</td>
			</tr>
			<tr>
				<th scope="row">类型：</th>
				<td>${sessionScope.session_user.privilege }</td>
			</tr>
			<tr>
				<th scope="row">邮箱：</th>
				<td>${sessionScope.session_user.email }</td>
		</table>
	</div>
</body>
</html>
