<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>老黄历</title>
	<link href="css/common.css" rel="stylesheet" type="text/css" />
    <script src="js/Jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/base.js"></script>
    <script type="text/javascript" src="js/DatePicker.js"></script>
</head>
<body>
	<form id="myfrom" action="ancient" method="post">
		<div class="top" id="top">
	    </div>
	    <div class="datePick" id="datePick">
			<div class="yesterday" onclick="lastDay()">上一天</div>
			<div class="current">
				<input name="userInfo.date" id="datePicker"
													onfocus="setday(this,
													'yyyy-MM-dd','2010-01-01','2010-12-30',1)"
												value="${currentDate}"
													type="text" id="" style="width:90%;height:80%;border:none;font-size:1.6em"
													/>
			</div>
			<div  class="tomorrow" onclick="nextDay()">后一天</div>
		</div>
	    <div class="mids">
	        <div class="search">
	            <div class="searchInfo" onclick="submit()">
	               查看</div>
	        </div>
	    </div>
    </form>
</body>
</html>