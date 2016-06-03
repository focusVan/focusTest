var resultItem = new Array();
var poiModels;
var listNum = 0;
$(function(){
	
for(var j = 0; j < 7; j++){
	var itemObject = new Object();
	itemObject["uid"] = j;
	itemObject["title"] = "名称"+j;
	itemObject["lat"] = "lat";
	itemObject["lng"] = "lng";
	itemObject["address"] = "地址"+j;
	resultItem.push(itemObject);
}
loadList();


//losdMap();
});
function losdMap(){
	resultItem = new Array();
	listNum = 0;
	$("#r-result").html("");
	$("#chakan").show();
	navigator.geolocation.getCurrentPosition(translatePoint); //定位 
}
function translatePoint(position){
	var currentLat = position.coords.latitude; 
	var currentLon = position.coords.longitude; 
	var gpsPoint = new BMap.Point(currentLon, currentLat); 
	BMap.Convertor.translate(gpsPoint, 0, initMap); //转换坐标 
} 
function initMap(point){ 
//初始化地图 
	map = new BMap.Map("l-map"); 
	map.addControl(new BMap.NavigationControl()); 
	map.addControl(new BMap.ScaleControl()); 
	map.addControl(new BMap.OverviewMapControl()); 
	map.centerAndZoom(point, 15); 
// 	map.addOverlay(new BMap.Marker(point));
	var myKeys ;
	var search_input = $("#search_input").val();
	if(search_input == null || search_input =="" ){
		myKeys =["商场"]
	}else{
		myKeys =[search_input];
	}
	var local = new BMap.LocalSearch(map, {
		renderOptions:{map: map},
		pageCapacity:5,
		onSearchComplete: function(results){
			console.log(results);
		   if (local.getStatus() == BMAP_STATUS_SUCCESS){
		     // 判断状态是否正确    
		     for (var i = 0; i < results.length; i ++){
		    	 var  keyType = results[i];
		    	 var poilist = keyType.wr;
		    	 for(var j = 0; j < poilist.length; j++){
				 	var itemObject = new Object();
					itemObject["uid"] = poilist[j].uid;
					itemObject["title"] = poilist[j].title;
					itemObject["lat"] = poilist[j].point.lat;
					itemObject["lng"] = poilist[j].point.lng;
					itemObject["address"] = poilist[j].address;
					resultItem.push(itemObject);
		    	 }
		     }
			 loadList();
		   } 
		 }
	});
	
	local.searchInBounds(myKeys, map.getBounds());
}


function loadList(){
	var uids = "";
	for(var i = 0 ; i<resultItem.length ; i++){
		uids = uids + resultItem[i].uid +","; 
	}
	uids = uids.substring(0,uids.length-1);
	$.showLoading("正在检测");
	$.ajax({
	url:"listPoi",
	type:"get",
	contentType:"application/json",
	dataType : 'json',
	async:true,
	data:{uids:uids},
	success:function(ret){
		poiModels = ret;
		drawList();
		$.hideLoading();
	},
	error : function(mm, a, b, c, d, e) {
		$.hideLoading();
		$.alert("服务器好像开小差了，刷新一下试试吧！", function() {
		});
	}
	})
}

function drawList(){
	var resultdraw = "";
	var begain = listNum;
	for(var i=begain;i<begain+3;i++){
		if(i>=resultItem.length){
			$("#chakan").hide();
			break;
		}
		var uidtemp = resultItem[i].uid;
		var sign = "0";
		if(findInList(poiModels,uidtemp)){
			sign = "1";
		}
		resultdraw = resultdraw + getdrawItem(resultItem[i],sign)
		listNum++;
	}
	$("#r-result").append(resultdraw);
}
function findInList(pois,uidtemp){
	for (var i = 0; i < pois.length; i++) {
		var temp = pois[i].uid
		if(uidtemp == temp){
			return true;
		}
	}
	return false;
}

function getdrawItem(resultItem,sign){
	var retStr = "";
	retStr = retStr +"<div class='weui_media_box weui_media_text weui_panel_ft'  onclick='edite(this)'>";
  	retStr = retStr +"<h4 class='weui_media_title'>"+resultItem.title+"</h4>";
  	if(sign=="1"){
		retStr = retStr +"<p class='weui_media_desc'>已登记</p>";
	}else{
		retStr = retStr +"<p class='weui_media_desc'>未登记</p>";
	}
  	retStr = retStr +"<input type='hidden' value='"+resultItem.uid+"'/>";
    retStr = retStr +"</div>";
	return retStr;
}
var editresultItem ="";
var domthis;
function edite(This){
	domthis = This;
	var edituid = $(This).children("input").val();
	
	for (var i = 0; i < resultItem.length; i++) {
		var temp = resultItem[i].uid
		if(edituid == temp){
			editresultItem =  resultItem[i];
			break;
		}
	}
	$("#csCode").val(editresultItem.uid);
	$("#csName").val(editresultItem.title);
	
	var uids = editresultItem.uid;
	if($(This).children("p").html()=="已登记"){
		$.ajax({
			url:"listPoi",
			type:"get",
			contentType:"application/json",
			dataType : 'json',
			async:true,
			data:{uids:uids},
			success:function(ret){
				poiModels = ret;
				var poiModel = poiModels[0];
				$("#selectsq").val(poiModel.selectsq);
				$("#yyarea").val(poiModel.yyarea);
				$("#cstype").val(poiModel.cstype);
				$("#opentime").val(poiModel.opentime);
				$("#floor").val(poiModel.floor);
			},
			error : function(mm, a, b, c, d, e) {
				$.alert("服务器好像开小差了，刷新一下试试吧！", function() {
				});
			}
		})
	}else{
		$("#selectsq").val("");
		$("#yyarea").val("");
		$("#cstype").val("");
		$("#opentime").val("");
		$("#floor").val("");
	}
	
	$("#popup").popup();
	
}
function save(){
	var uid = $("#csCode").val();
	var title = $("#csName").val();
	var lat = editresultItem.lat;
	var lng = editresultItem.lng;
	var address = editresultItem.address;
	var selectsq = $("#selectsq").val();
	var yyarea = $("#yyarea").val();
	var cstype = $("#cstype").val();
	var opentime = $("#opentime").val();
	var floor = $("#floor").val();
	
	title = encodeURI(title);
	address = encodeURI(address);
	/****/
	$.showLoading("正在保存");
	$.ajax({
		url:"addPoi",
		type:"get",
		contentType:"application/json",
		dataType : 'json',
		async:true,
		data:{uid:uid,title:title,lat:lat,lng:lng,address:address,selectsq:selectsq,yyarea:yyarea,cstype:cstype,opentime:opentime,floor:floor,},
		success:function(ret){
			$.hideLoading();
			
			$.toast(ret.msg);
		},
		error : function(mm, a, b, c, d, e) {
			$.hideLoading();
			$.alert("服务器好像开小差了，刷新一下试试吧！", function() {
			});
		}
	});
	$(domthis).children("p").html("已登记");
	editresultItem ="";
	$.closePopup();
}
function cancel(){
	editresultItem ="";
	$.closePopup();
	
}
