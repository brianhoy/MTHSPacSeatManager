<jsp:include page="loggedInIndex.jsp"></jsp:include>  
<%@ taglib uri="/struts-tags" prefix="s" %>  

<h2>Edit Show:</h2>  
<fieldset>
	<h3>
		<s:property value="name"/><br/>
	</h3>
	<p>
		<s:property value="description"/><br/>
	</p>
	<strong>
	<label>Date: </label>		</strong>
		<s:property value="date"/><br/>
</fieldset>  
