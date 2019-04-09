<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
    <h1>Jsp</h1>
	<table>
		<tr>
			<td>用户名</td>
			<td>密码</td>
			<td>年龄</td>
			<td>创建时间</td>
		</tr>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.username}</td>
				<td>${user.password}</td>
				<td>${user.age}</td>
				<td>${user.createTime}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>