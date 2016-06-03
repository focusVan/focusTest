function submit()
{
	$("#myfrom").submit();
}

function search(){
	$("#myfrom").submit();
}

function nextDay(){
	var date = $('#datePicker').val();
	var curDate = new Date(date);
	var nextDate = new Date(curDate.setDate(curDate.getDate()+1));
	var dateStr = nextDate.getFullYear()+"-"+(nextDate.getMonth()+1)+"-"+nextDate.getDate();
	$('#datePicker').val(dateStr);
	$("#myfrom").submit();
}

function lastDay(){
	var date = $('#datePicker').val();
	var curDate = new Date(date);
	var lastDate = new Date(curDate.setDate(curDate.getDate()-1));
	var dateStr = lastDate.getFullYear()+"-"+(lastDate.getMonth()+1)+"-"+lastDate.getDate();
	$('#datePicker').val(dateStr);
	$("#myfrom").submit();
}
