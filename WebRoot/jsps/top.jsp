<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品购物主页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
body {
	background: #009ad6;

}
.manager{
	height: auto;
}

a , .manager{
	text-transform: none;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>

<body>
	<h1 style="text-align: center;">简易购物项目</h1>
	<div style="font-size: 10pt;">
		<c:choose>
			<c:when test="${empty sessionScope.session_user }">
				<a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登录</a> |&nbsp; 
			<a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册</a>
			</c:when>
			<c:otherwise>
				您好：${sessionScope.session_user.username }&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="<c:url value='/jsps/main.jsp'/>" target="_top">我的主页</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="<c:url value='/jsps/cart/list.jsp'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="<c:url value='/OrderServlet?method=myOrders'/>"
						target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>
				<div class="manager">
				<hr>
				<c:if test="${sessionScope.session_user.privilege ne 'normal'}">
					<a href="<c:url value='/adminjsps/admin/index.jsp'/>" target="b">进入后台界面</a>
					<c:if test="${sessionScope.session_user.privilege eq 'admin'}">
					<a href="<c:url value='/adminjsps/admin/watchuser.jsp'/>" target="left">查看所有用户</a>
					</c:if>
				</c:if>
				<!-- 所有用户都是 基本用户，都具有 查看自己信息和修改密码的能力 -->
				<a href="<c:url value='/jsps/user/watchme.jsp'/> " target="body">查看自己信息</a> 
				<a href="<c:url value='/jsps/user/alterpassword.jsp'/> " target="body">修改自己密码</a>
				<%-- <c:set var="privil" value="${sessionScope.session_user }"></c:set>
			 --%>
				</div>
			 </c:otherwise>
		</c:choose>
	</div>

</body>
</html>
