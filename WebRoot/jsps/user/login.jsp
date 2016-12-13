<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/logincenter.css">
<style type="text/css">
.mainclass {
	margin: 0 auto;
	width: 1000px;
	text-align: center;
}
</style>
</head>

<body>
	<%--
1. 显示错误信息
2. 回显
 --%>
 <div class="mainclass">
	<h1>登录</h1>
	<p style="color: red; font-weight: 900">${msg }</p>
	<c:if test="${msg eq '尚未激活！' }">
		<h2>
			<a href='<c:url value='/UserServlet?method=reSendMail&username=${form.username }'/> '>在发送一封验证</a>
		</h2>
	</c:if>
	<form action="<c:url value='/UserServlet'/>" method="post"
		target="_top">
		<input type="hidden" name="method" value="login" /> 用户帐号：<input
			type="text" name="username" value="${form.username }" /><br /> 用户密码：<input
			type="password" name="password" value="${form.password }" /><br /> <input
			type="submit" value="登录" />
	</form>
 </div>
</body>
</html>
