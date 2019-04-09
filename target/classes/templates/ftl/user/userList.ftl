<#import "spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="<@spring.url '/js/jquery-1.10.2.min.js'/>"></script>
<script type="text/javascript">
	$(function() {
		alert("12345");
	});
</script>
</head>
<body>
	<h1>freemark</h1>
	<img src="<@spring.url '/images/1.jpg'/>">
	<table>
		<tr>
			<td>用户名</td>
			<td>密码</td>
			<td>年龄</td>
			<td>创建时间</td>
		</tr>
		
		<#list userList as user>
		<tr>
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td>${user.age}</td>
			<td>${user.createTime}</td>
		</tr>
		</#list>
	</table>
</body>
</html>