<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<h2>Hello World!</h2>
<hr>
<security:authorize access="hasRole('MANAGER')">
	<p>
		<a href="${pageContext.request.contextPath }/leaders">Leadership Meeting(Only Managers can see this page)</a>
	</p>
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
	<p>
		<a href="${pageContext.request.contextPath }/system">Admin Meeting(Only Admin can see this page)</a>
	</p>
</security:authorize>

<form:form  action="${pageContext.request.contextPath }/logout" method="post">
	<input type="submit" value="Logout">
</form:form>

	<hr>
	<!-- Display the user name -->
	<p>
		User: <security:authentication property="principal.username"/>
		Role: <security:authentication property="principal.authorities"/>
	</p>
	<hr>

</body>
</html>
