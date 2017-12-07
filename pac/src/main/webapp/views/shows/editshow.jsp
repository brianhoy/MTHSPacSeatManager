<%@ taglib uri="/struts-tags" prefix="s"%>

<jsp:include page="../index/loggedInIndex.jsp"></jsp:include>

<script type="text/javascript">
var dates = {};
function initialize() {
	
	<s:iterator value="dates">
		dates[<s:property value="showDateId" />] = <s:property value="date.toString()" />
	</s:iterator>
}
</script>

<div class="root column col-6 col-xs-12">
	<h2>Edit Show</h2>
	<s:form action="finishshowedit">
		<div class="form-group">
			<label class="form-label" for="title">Title</label>
			<input class="form-input" id="username" type="text" name="showForm.title" value="<s:property value="%{currentShow.title}" />"/>
		</div>
		<div class="form-group">
			<label class="form-label" for="description">Description</label>
			<textarea class="form-input" id="description" rows="3" name="showForm.description"><s:property value="%{currentShow.description}"/></textarea>
		</div>
		<div class="form-group">
			<label class="form-label">Duration</label>
			<input class="form-input" type="number" name="showForm.hours" placeholder="Hours" value="<s:property value="%{currentShow.duration.toHours()}"/>"/>
			<input class="form-input" type="number" name="showForm.minutes" placeholder="Minutes" value="<s:property value="%{currentShow.duration.toMinutes() % 60}"/>"/>
		</div>
		<div class="form-group">
			<label class="form-label">Date(s)</label>
			<ol id="dateList">
				<li>
					<div class="asDiv">
						<input class="form-input datepicker0" id="pacdatepicker" type="text"/>
					</div>
				</li>
			</ol>
		</div>
				
		<input type="hidden" id="dateInput" name="showForm.dates" />
		
		<div class="form-group">
			<button type="button" onclick="addDate()" class="btn">Add Date</button>
			<button type="submit" class="btn btn-primary">
				Add Show
			</button>
		</div>
	</s:form>
</div>

<link rel="stylesheet" href="/pac/css/vendor/flatpickr.min.css">
<script src="/pac/js/vendor/flatpickr.js"></script>
<script src="/pac/js/addshow.js"></script>

<jsp:include page="../index/indexfooter.jsp"></jsp:include>  
