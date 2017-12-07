<jsp:include page="../index/index.jsp"></jsp:include>
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
				<div class="card-body"><s:property value="description" /></div>
			</fieldset>
		</div> 
	</s:iterator>
</div>
