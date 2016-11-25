<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>My JSP 'alterpassword.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/body.css">
<link rel="stylesheet" type="text/css" href="css/searchuser.css">
<!-- <script type="text/javascript" src="js/password.js"></script>  -->
</head>
<body>
<c:if test="${!empty newword }">
	<Script Language="JavaScript">
	alert(${newword}) 
	</script>
</c:if>
	<font color="#FF0000">${newword }</font>
	<form action='<c:url value='/UserServlet?method=alterPassword' />' method="post">
		<table>
			<tr>
				<td>登录名：</td>
				<td><input type="text" value="${sessionScope.session_user.username }"
					name="admin.aname" size="16" readonly /></td>
			</tr>
			<tr>
				<td>原密码：</td>
				<td><input type="password" id="oldpassword" name="oldpassword" size="16" /></td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password" id="password" name="password1" size="16"/></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" name="password2" name="password2" size="16" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="确定">
					&nbsp;&nbsp;&nbsp; <input type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
