<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
	<h1>Create a new user</h1>
	<div><a href="/login">Go back to login page</a></div>
	<div>${error}</div>
	<form:form action="/register" method="post" commandName="user">
	
		<fieldset class="form-group">
			<form:label path="username">User Name</form:label>
			<form:input path="username" type="text" class="form-control"
				required="required"></form:input>
			<form:errors path="username" cssClass="text-warning"/>
		</fieldset>
		
		<fieldset class="form-group">
			<form:label path="password">Password</form:label>
			<form:input path="password" type="password" class="form-control"
				required="required"></form:input>
			<form:errors path="password" cssClass="text-warning"/>
		</fieldset>
		
		<input class="btn btn-success" type="submit" value="Submit"/>
	</form:form>
	<%-- <form action="/register" method="post" commandName="user">
	
		<fieldset class="form-group">
			<label path="username">User Name</label>
			<input path="username" type="text" class="form-control"
				required="required"></input>
			<errors path="username" cssClass="text-warning"/>
		</fieldset>
		
		<fieldset class="form-group">
			<label path="password">Password</label>
			<input path="password" type="password" class="form-control"
				required="required"></input>
			<errors path="password" cssClass="text-warning"/>
		</fieldset>
		<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
		<input class="btn btn-success" type="submit" value="Submit"/>
	</form> --%>
</div>


<%@ include file="common/script.jspf" %>
<%@ include file="common/footer.jspf" %>
