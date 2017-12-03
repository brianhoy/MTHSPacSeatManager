<jsp:include page="../index/index.jsp"></jsp:include>  
<%@ taglib uri="/struts-tags" prefix="s" %>

<div class="root column col-6 col-xs-12">
	<h1>Login</h1>
	<s:form action="loginprocess">
		<s:textfield cssClass="form-input" name="username" label="Name"></s:textfield>  
		<s:password cssClass="form-input" name="userpass" label="Password"></s:password>  
		<s:submit cssClass="btn btn-primary" value="Login"></s:submit>  
	</s:form>
	<br />
	<div class="text-error">
		<s:property value="errorMsg"/><br/>
	</div> 
</div>


<jsp:include page="../index/indexfooter.jsp"></jsp:include>  
