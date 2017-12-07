<jsp:include page="../index/loggedInIndex.jsp"></jsp:include>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div>
	<s:iterator value="shows">
		<div class="card left spacing">
			<fieldset>
 				<div class="card-header">
 					<div class="card-title h5">
						<s:property value="title" />
					</div>
					<div class="card-subtitle text-gray">
						<div class="float-left">
							<s:property value="firstDate" />
							<s:if test="lastDate != null">
								to <s:property value="lastDate" />
							</s:if>
						</div>
						<div class="float-right">
							<s:property value="duration.toHours()" /> hours
							<s:if test="duration.toMinutes() % 60 != 0">
								, <s:property value="duration.toMinutes() % 60" /> minutes
							</s:if>
						</div>
						&nbsp
					</div>									
				</div>
				<div class="card-body">
					<s:property value="description" />
				</div>
				<div class="card-footer">
					<s:url var="editshowbtn" action="editshow">
						<s:param name="showId" value="%{showId}" />
					</s:url>
					<s:a href="%{editshowbtn}">
						<s:property value="'Edit'"></s:property>
					</s:a>
					<s:url var="removeshowbtn" action="removeshow">
						<s:param name="showId" value="%{showId}" />
					</s:url>
					<s:a href="%{removeshowbtn}">
						<s:property value="'Remove'"></s:property>
					</s:a>
				</div>
			</fieldset>
		</div> 
	</s:iterator>
</div>


<jsp:include page="../index/indexfooter.jsp"></jsp:include>
