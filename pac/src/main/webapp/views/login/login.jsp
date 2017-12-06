<jsp:include page="../index/index.jsp"></jsp:include>  
<%@ taglib uri="/struts-tags" prefix="s" %>

<div class="root column col-6 col-xs-12">
	<h1>Login</h1>
	<s:form action="loginprocess">
		<div class="form-group">
			<label class="form-label" for="username">Username</label>
			<input class="form-input" id="username" type="text" name="username" placeholder="JohnSmith" />
		</div>
		<div class="form-group">
			<label class="form-label" for="password">Password</label>
			<input class="form-input" id="password" type="password" name="password" placeholder="Password"/>
		</div>

		<div class="form-group">
			<button type="submit" class="btn btn-primary">
				Sign in
			</button>
		</div>
	</s:form>
</div>


<jsp:include page="../index/indexfooter.jsp"></jsp:include>  
