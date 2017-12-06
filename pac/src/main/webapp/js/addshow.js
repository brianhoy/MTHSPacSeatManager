var dates = [];

(function(){
	console.log("Add show script loaded.");
	
	var config = {
			enableTime: true,
			altInput: true,
			minDate: "today",
			dateFormat: "Y-m-d H:i:S",
			onChange: (sd, ds, is) => onDateChange(sd, ds, is, 0)
		};

	flatpickr(".datepicker0", config);
	dates.push("1000-10-10 12:12:12");
})();


var daten = 1;
function addDate() {
	var list = document.getElementById("dateList");
	
	var li = document.createElement('li');
	
	var n = daten;

	var config = {
		enableTime: true,
		altInput: true,
		minDate: "today",
		dateFormat: "Y-m-d H:i:S",
		onChange: (sd, ds, is) => onDateChange(sd, ds, is, n)
	};
	
	
	var template = `<div class="asDiv">`;
	template += `<button type="button" onclick="" class="asBtn btn btn-error">X</button>`;
	template += `<span class="asSpan"><input class="form-input datepicker` + daten + `" type="text" name="show.date"/></span>`;
	template += `</div>`;
	
	//div.className = "form-group";
	li.innerHTML = template;
	
	
	list.appendChild(li);
	
	flatpickr(".datepicker" + daten, config);
	daten++;
	dates.push("1000-10-10 12:12:12");
}


function onDateChange(selectedDates, dateStr, instance, id) {
	console.log("OnDateChange id " + id + " called with date string: " + dateStr);
	dates[id] = dateStr;
	console.log("dates: " + JSON.stringify(dates));
	document.getElementById("dateInput").value = JSON.stringify(dates);
}

