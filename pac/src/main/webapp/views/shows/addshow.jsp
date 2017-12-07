<jsp:include page="../index/loggedInIndex.jsp"></jsp:include>

<%@ taglib uri="/struts-tags" prefix="s" %>  
 
<div class="root column col-6 col-xs-12">
	<h2>Add Show</h2>
	<s:form action="addshow">
		<div class="form-group">
			<label class="form-label" for="title">Title</label>
			<input class="form-input" type="text" name="showForm.title" placeholder="Aladdin" />
		</div>
		<div class="form-group">
			<label class="form-label" for="description">Description</label>
			<textarea class="form-input" rows="3" name="showForm.description" placeholder="Aladdin is a 1992 American animated comedy musical romantic fantasy adventure film ..."></textarea>
		</div>
		<div class="form-group">
			<label class="form-label">Duration</label>
			<input class="form-input" type="number" name="showForm.hours" placeholder="Hours" />
			<input class="form-input" type="number" name="showForm.minutes" placeholder="Minutes" />
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
