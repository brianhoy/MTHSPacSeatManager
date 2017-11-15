<jsp:include page="loggedInIndex.jsp"></jsp:include>
<%@ taglib uri="/struts-tags" prefix="s"%>

<h2>Shows (admin view):</h2>
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

		<s:url var="editshowbtn" action="editshow">
			<s:param name="showid" value="%{id}" />
		</s:url>
		<td><s:a href="%{editshowbtn}">
				<s:property value="'Edit'"></s:property>
			</s:a></td>
		<s:url var="removeshowbtn" action="removeshow">
			<s:param name="showid" value="%{id}" />
		</s:url>
		<td><s:a href="%{removeshowbtn}">
				<s:property value="'Remove'"></s:property>
			</s:a></td>

	</fieldset>
</s:iterator>
