<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<title>My JSP 'showuser.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/flowlayout.css">


<script>
	function doAction(index) {
		var obj = document.getElementById('u' + index);
		if (obj.style.display == 'none') {
			obj.style.display = 'block';
			if (index == '1') {
				document.getElementById("img1").src = 'images/downV.png';
			} else {
				document.getElementById("img2").src = 'images/downV.png';
			}
		} else {
			obj.style.display = 'none';
			if (index == '1') {
				document.getElementById("img1").src = 'images/rightV.png';
			} else {
				document.getElementById("img2").src = 'images/rightV.png';
			}
		}
	}
	function test() {
		var obj = document.getElementById('l1');
		alert(obj.innerHTML);
	}
</script>
</head>
<body>
<!-- 查看 -->
<div class="out_div">
	<div id="d1" class="in_div">
		<%--  ${userList }
		<hr/> --%>
		<ul class="list">
			<li><span onclick="doAction(1);"><img
					src="images/rightV.png" id="img1" height="20" width="20" />买家</span></li>
			<ol id="u1">
				<c:forEach items="${userList }" var="temp">
				<c:if test="${temp.privilege eq 'normal'}">
					<li>${temp.username }</li>
				</c:if>
				</c:forEach>
			</ol>
<!-- 			<li><span onclick="doAction(2);"><img
					src="images/rightV.png" id="img2" height="20" width="20" />卖家</span></li>
			<ul style="display:none;" id="u2">

			</ul> -->
			<li><span onclick="doAction(2);"><img
					src="images/rightV.png" id="img2" height="20" width="20" />卖家</span></li>
				<ol  id="u2">
				<c:forEach items="${userList }" var="temp">
					<c:if test="${temp.privilege eq 'seller'}">
					<li>${temp.username } </li>
					</c:if>
				</c:forEach>
				</ol>
		</ul>
	</div>
<%-- 	<div class="in_div">
		${userList }
	</div> --%>
</div>
</body>
</html>



