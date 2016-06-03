<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>QQ占卜</title>
	<link href="css/common.css" rel="stylesheet" type="text/css" />
    <script src="js/Jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/base.js"></script>
</head>
<body>
	<form id="myfrom" action="test" method="post">
		<div class="top" id="top">
	    </div>
	    <div class="midtest">
	        <div class="inputdiv">
	            <input type="text" id="txtuser" name="userInfo.userName" value="QQ号" onblur="if(this.value==''){this.value=' QQ号';}"
	                onfocus="if(this.value==this.defaultValue){this.value='';}" /></div>
	    </div>
	    <div class="midtest">
	        <div class="logindiv">
	            <div class="logindivmargin" onclick="submit()">
	               开始占卜</div>
	        </div>
	    </div>
    </form>
</body>
</html>