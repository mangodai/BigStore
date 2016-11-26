<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<script type="text/javascript" language="javascript" >
	function deliver(){
		alert("确认收获")
	}
</script>

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
* {
	font-size: 11pt;
}

div {
	border: solid 2px #f8aba6;
	width: 75px;
	height: 75px;
	text-align: center;
}

li {
	margin: 10px;
}
</style>
</head>

<body style="background: rgb(254,238,189);">
	<h1>订单列表</h1>
	${msg }
	<c:if test="${!empty msg }">
	</c:if>
	<table border="1" width="100%" cellspacing="0" background="black">
		<!-- tudo -->
		<c:forEach items="${orderList }" var="temp">
			<tr bgcolor="rgb(78,78,78)" bordercolor="rgb(78,78,78)"
				style="color: white;">
				<td colspan="6">订单号：${temp.oid } 成交时间：${temp.ordertime } 金额：<font
					color="#dc143c"><b>${temp.total }</b></font> <c:choose>
						<c:when test="${temp.state eq 1 }">买家未付款</c:when>
						<c:when test="${temp.state eq 2 }">
							<a href="<c:url value='OrderServlet?method=deliver&oid=${temp.oid }'/>">
							等待发货</a>
							 <!-- &nbsp; <a href="javascript:void(0);" onclick="deliver()">脚本方法</a> -->
							 </c:when>
						<c:when test="${temp.state eq 3 }">确认收货</c:when>
						<c:when test="${temp.state eq 4 }">交易成功</c:when>
					</c:choose>
				</td>
			</tr>
 			<c:forEach items="${temp.orderItemList }" var="item">
			<tr bordercolor="rgb(78,78,78)" align="center">
				<td width="15%">
					<div>
						<img src="<c:url value='${item.book.image }'/>" height="75" />
					</div>
				</td>
				<td>${item.book.bname }</td>
				<td>${item.book.price }</td>
				<td>${item.book.author}</td>
				<td>${item.count }</td>
				<td>${item.subtotal }</td>
			</tr>
			</c:forEach> 

		</c:forEach>
	</table>
</body>
</html>
