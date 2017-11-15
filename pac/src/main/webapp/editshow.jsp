<jsp:include page="loggedInIndex.jsp"></jsp:include>  
<%@ taglib uri="/struts-tags" prefix="s" %>  

<h2>Edit Show:</h2>  
<fieldset>
	<s:form action="finishshowedit">
		<s:hidden name="show.id" value="%{currentlyEditedShow.id}" />
		<s:textfield name="show.name" label="Title" value="%{currentlyEditedShow.name}"></s:textfield>
		<s:textfield name="show.description" label="Description" value="%{currentlyEditedShow.description}"></s:textfield>  
		<s:textfield name="show.date" label="Show Date (Format: 1000-01-01 00:00:00)" value="%{currentlyEditedShow.date}"></s:textfield>
		<s:submit value="Submit Edit"></s:submit>  
	</s:form>

	<s:url var="removeshowbtn" action="removeshow">
		<s:param name="showid" value="%{id}" />
	</s:url>
	<td><s:a href="%{removeshowbtn}">
		<s:property value="'Remove'"></s:property>
	</s:a></td>
</fieldset>  
