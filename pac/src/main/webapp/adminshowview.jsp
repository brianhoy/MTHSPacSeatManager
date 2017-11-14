<jsp:include page="loggedInIndex.jsp"></jsp:include>
<%@ taglib uri="/struts-tags" prefix="s"%>

<h2>Shows (admin view):</h2>
<s:property value="msg" />
<s:property value="errorMsg" />
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
		<strong> <label>Date: </label>
		</strong>
		<s:property value="date" />
		<br />

		<s:url var="aLink" action="editshow">
			<s:param name="showid" value="%{id}" />
		</s:url>
		<td><s:a href="%{aLink}">
				<s:property value="%{id}" />
			</s:a></td>

	</fieldset>
</s:iterator>
