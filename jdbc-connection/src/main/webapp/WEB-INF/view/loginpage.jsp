<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Custom Login Page</title>
<style>
	.failed{
		color:red;
		font-style: italic;
	}
</style>
</head>
<body>
	<h3>My Custom Login Page</h3>
	<form:form action="${pageContext.request.contextPath }/authenticateTheUser"
		method="POST">
		
			<c:if test="${param.error != null }">
				<div class="failed">Incorrect username or password</div>
			</c:if>
			
			<c:if test="${param.logout != null }">
				<div >You have been logged out</div>
			</c:if>
		
			<p>
				UserName: <input type="text" name="username">
			</p>
			<p>
				Password: <input type="password" name="password">
			</p>
			<br>
			<input type="submit" value="Login">
	</form:form>
</body>
</html>