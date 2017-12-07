var dates = [];
var fpInstances = [];

(function(){
	console.log("Add show script loaded.");
	
	var config = {
			enableTime: true,
			altInput: true,
			minDate: "today",
			dateFormat: "Y-m-d H:i:S",
			onChange: (sd, ds, is) => onDateChange(sd, ds, is, 0)
		};

	var instance = flatpickr(".datepicker0", config);
	fpInstances.push(instance);
	
	dates.push("1000-10-10 12:12:12");
})();

function updateHiddenInput() {
	var str = "[";
	for(var i = 0; i < dates.length; i++) {
		if(dates[i] != "") {
			str += dates[i];
			if(i != dates.length - 1) {
				str += ",";
			}
		}
	}
	str += "]";
	document.getElementById("dateInput").value = str;
}

function removeDate(id) {
	dates[id] = "";
	var el = document.getElementById("datePicker" + id);
	fpInstances[id].destroy();
	el.outerHTML = "";
	delete el;
	updateHiddenInput();
}

var daten = 1;
function addDate() {
	var list = document.getElementById("dateList");
	
	var li = document.createElement('li');
	
	var n = daten;

	li.id = "datePicker" + n; 
	
	var config = {
		enableTime: true,
		altInput: true,
		minDate: "today",
		dateFormat: "Y-m-d H:i:S",
		onChange: (sd, ds, is) => onDateChange(sd, ds, is, n)
	};
	
	
	var template = `<div class="asDiv">`;
	template += `<button type="button" onclick="removeDate(` + n + `)" class="asBtn btn btn-error">X</button>`;
	template += `<span class="asSpan"><input class="form-input datepicker` + daten + `" type="text" name="show.date"/></span>`;
	template += `</div>`;
	
	//div.className = "form-group";
	li.innerHTML = template;
	
	
	list.appendChild(li);
	
	var instance = flatpickr(".datepicker" + daten, config);
	fpInstances.push(instance);
	
	daten++;
	dates.push("1000-10-10 12:12:12");
}


function onDateChange(selectedDates, dateStr, instance, id) {
	dates[id] = dateStr;

	console.log("OnDateChange id " + id + " called with date string: " + dateStr);
	console.log("dates: " + JSON.stringify(dates));
	
	updateHiddenInput();
}


