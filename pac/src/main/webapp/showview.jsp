<jsp:include page="index.jsp"></jsp:include>
<%@ taglib uri="/struts-tags" prefix="s"%>

<h2>Shows:</h2>
<s:property value="msg"/>
<s:property value="errorMsg"/>
<s:iterator value="shows">
	<fieldset>
		<h3>
			<s:property value="name" />
			<br />
		</h3>
		<p>
			<s:property value="description" />
			<br />
		</p>
		<strong> 
			<label>Date: </label>
		</strong>
		<s:property value="date" />
		<br />
	</fieldset>
</s:iterator>
