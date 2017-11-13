<jsp:include page="loggedInIndex.jsp"></jsp:include>  
<hr/>  
<%@ taglib uri="/struts-tags" prefix="s" %>  
 
<h2>Add Show</h2>
<s:property value="msg"/>
<s:property value="errorMsg"/>
<s:form action="addshow">
	<s:textfield name="show.name" label="Title"></s:textfield>
	<s:textfield name="show.description" label="Description"></s:textfield>  
	<s:textfield name="show.date" label="Show Date (Format: 1000-01-01 00:00:00)"></s:textfield>
	<s:submit value="addshow"></s:submit>  
</s:form>