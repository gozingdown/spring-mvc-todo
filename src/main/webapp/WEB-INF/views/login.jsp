<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<h1>Login</h1>
	
	<div>${error}</div>
	<div>${welcome_msg}</div>
	
	<form:form action="/login" method="post">
	
		<fieldset class="form-group">
			<label>Name</label>
			<input type="text" name="username" class="form-control"/>
		</fieldset>
		
		<fieldset class="form-group">
			<label>Password</label>
			<input type="password" name="password" class="form-control"/>
		</fieldset>
		
		<input class="btn btn-success" type="submit" name="submit"/>
		
	</form:form>
	<div><a href="/register">Register</a></div>
</div>

<%@ include file="common/script.jspf" %>
<%@ include file="common/footer.jspf" %>
