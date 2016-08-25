<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<div class="container">
	Welcome ${name}!<br>
	Now, you can <a href="/list-todos">manage your todos</a>
</div>



<%@ include file="common/script.jspf" %>
<script>
$(document).ready(function() {
    $(".login").addClass("active");
}); 
</script>
<%@ include file="common/footer.jspf" %>