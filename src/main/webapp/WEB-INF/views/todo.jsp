<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
	<h1>Add a Todo</h1>
	
	<form:form method="post" commandName="todo">
	
		<form:hidden path="id"/>
		
		<fieldset class="form-group">
			<form:label path="desc">Description</form:label>
			<form:input path="desc" type="text" class="form-control"
				required="required"></form:input>
			<form:errors path="desc" cssClass="text-warning"/>
		</fieldset>
		
		<fieldset class="form-group">
			<form:label path="targetDate">TargetDate</form:label>
			<form:input path="targetDate" type="text" class="form-control targetDate"
				required="required"></form:input>
			<form:errors path="desc" cssClass="text-warning"/>
		</fieldset>
		
		<input class="btn btn-success" type="submit" value="Submit"/>
	
	</form:form>
</div>
<%@ include file="common/script.jspf" %>
<script>
$(document).ready(function() {
    $(".todo").addClass("active");
}); 
</script>
<%@ include file="common/footer.jspf" %>
