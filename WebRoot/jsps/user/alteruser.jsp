<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'AlterUser.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/body.css">
	
	<style>
	td{
		font:18px;
		text-align: center;
	}
	</style>

  </head>
  
  <body>
<%--   ${userList } --%>
  <table border="1" width="100%" cellspacing="0" background="black">
	<tr bgcolor="#87CEFA" bordercolor="#87CEFA" style="color: white;">
		<td >序号 </td><td>用户姓名</td><td>权限</td><td colspan="2">增加或删除卖家</td>
	</tr>
	<c:set var="i" value="1"/>
 	<c:forEach  items="${userList }"  var="temp" >
 		<tr bordercolor="#87CEFA" align="center">
				<c:if test="${temp.privilege != 'admin'}">
					<td>${i} </td><c:set var="i" value="${i+1 }"></c:set>
					<td>${temp.username }</td>
					<td>${temp.privilege }</td>
					<td><a href='<c:url value='/UserServlet?method=alterUser&uid=${temp.uid }&pri=seller'/>'>成为卖家</a></td>	
					<td><a href='<c:url value='/UserServlet?method=alterUser&uid=${temp.uid }&pri=normal'/>'>成为买家</a></td>	
<!-- 					<td></td>	 -->				
			 </c:if>
		</tr> 
	</c:forEach>
  </table>
 
  </body>
</html>
