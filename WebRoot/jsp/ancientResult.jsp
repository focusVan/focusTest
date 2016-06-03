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
</head>
<body>
	<form id="myfrom" action="test" method="post">
		<div class="top" id="top">
	    </div>
	    <div class="midancient">
	        <div class="ancient">
	            阳历：${yangli}</div>
	    </div>
	    <div class="midancient">
	        <div class="ancient">
	            阴历：  ${yinli}</div>
	    </div>
	    <div class="midancient">
	        <div class="ancient">
	            五行：  ${wuxing}</div>
	    </div>
	    <div class="midancient">
	        <div class="ancient">
	            冲煞：  ${chongsha}</div>
	    </div>
	    <div class="midancient">
	        <div class="ancientend">
	            吉神：  ${jishen}</div>
	    </div>
	    <div class="midancient">
	        <div class="ancientend">
	            凶神：  ${xiongshen}</div>
	    </div>
	    <div class="midancient">
	        <div class="ancientend">
	            宜：  ${yi}</div>
	    </div>
	    <div class="midancient">
	        <div class="ancientend">
	            忌：  ${ji}</div>
	    </div>
    </form>
</body>
</html>