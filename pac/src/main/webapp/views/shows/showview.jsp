<jsp:include page="../index/index.jsp"></jsp:include>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div>
	<s:iterator value="shows">
		<div class="card left spacing">
			<fieldset>
 				<div class="card-header">
 					<div class="card-title h5">
						<s:property value="name" />
					</div>
					<div class="card-subtitle text-gray">
						<s:property value="date" />
					</div>
				</div>
				<div class="card-body">
					<s:property value="description" />
				</div>
			</fieldset>
		</div> 
	</s:iterator>
</div>
