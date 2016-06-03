<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>占卜</title>
	<link href="css/common.css" rel="stylesheet" type="text/css" />
    <script src="js/Jquery.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/base.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=UGgHVlk2BqFoOuQVU1pf8FdyeRU0FON6"></script> 
	<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script> 
	<link href="jquery-weui/css/weui.css" rel="stylesheet" type="text/css" />
	<link href="jquery-weui/css/jquery-weui.css" rel="stylesheet" type="text/css" />
	<title>周边</title>
	<style type="text/css">
		#l-map{height:300px;width:100%;}
	</style>
</head>
<body>
		<div class="weui_search_bar" id="search_bar">
		  <form class="weui_search_outer" action="javascript:losdMap()">
		    <div class="weui_search_inner">
		      <i class="weui_icon_search"></i>
		      <input type="search" class="weui_search_input" id="search_input" placeholder="搜索" required/>
		      <a href="javascript:" class="weui_icon_clear" id="search_clear"></a>
		    </div>
		    <label for="search_input" class="weui_search_text" id="search_text">
		      <i class="weui_icon_search"></i>
		      <span>搜索</span>
		    </label>
		  </form>
		  <a href="javascript:" class="weui_search_cancel" id="search_cancel">取消</a>
		</div>
		<div class="swiper-container" data-space-between='10' data-pagination='.swiper-pagination' data-autoplay="1000">
		  <div class="swiper-wrapper">
		    <div class="swiper-slide" id="l-map"></div>
		  </div>
		</div>
		<div class="weui_panel weui_panel_access">
		  <div class="weui_panel_hd">搜索列表</div>
		  <div class="weui_panel_bd" id="r-result">
		   <%-- <div class="weui_media_box weui_media_text weui_panel_ft" onclick="edite(this)">
		      	<h4 class="weui_media_title">上海国金商场</h4>
		      	<p class="weui_media_desc">已登记</p>
		      	<input type="hidden" value=""/>
		    </div>
		     <div class="weui_media_box weui_media_text weui_panel_ft">
		      <h4 class="weui_media_title">八佰伴第一广场</h4>
		      <p class="weui_media_desc">未登记</p>
		    </div>--%>
		  </div>
		  <a href="javascript:drawList();" class="weui_panel_ft" id="chakan">查看更多</a>
		</div>
		<div id="popup" class="weui-popup-container" style="width: 500px">
			<div class="weui-popup-modal">
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd"><label class="weui_label">场所编码</label></div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="text" placeholder="请输入场所编码" id="csCode" readonly="readonly">
						</div>
					</div>
					<div class="weui_cell">
						<div class="weui_cell_hd"><label class="weui_label">商圈名称</label></div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="text" placeholder="请输入商圈名称" id="csName" readonly="readonly">
						</div>
					</div>
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="select1" id="selectsq">
								<option selected="" value="0">商圈类型</option>
								<option value="1">商业区</option>
								<option value="2">住宅区</option>
								<option value="3">文教区 </option>
								<option value="4">办公区 </option>
								<option value="5">工业区 </option>
								<option value="6">混合区 </option>
							</select>
						</div>
					</div>
					<div class="weui_cell">
						<div class="weui_cell_hd"><label class="weui_label">营业面积</label></div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="text" placeholder="请输入营业面积" id="yyarea">
						</div>
					</div>
					<div class="weui_cell weui_cell_select">
						<div class="weui_cell_bd weui_cell_primary">
							<select class="weui_select" name="select1"  id="cstype">
								<option selected="" value="0">场所类型</option>
								<option value="1">广场</option>
								<option value="2">店铺</option>
							</select>
						</div>
					</div>
					<div class="weui_cell">
						<div class="weui_cell_hd"><label for="" class="weui_label">开业时间</label></div>
						<div class="weui_cell_bd weui_cell_primary">
					      <input class="weui_input" type="date" value="" id="opentime">
					    </div>
					 </div>
					<div class="weui_cell">
						<div class="weui_cell_hd"><label class="weui_label">楼层数量</label></div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="text" placeholder="请输入楼层数量" id="floor">
						</div>
					</div>
				</div>
				<a href="javascript:save();" class="weui_btn weui_btn_mini weui_btn_primary" style="width: 100px; margin-right: 50px; margin-left: 50px;">保存</a>
				<a href="javascript:cancel();" class="weui_btn weui_btn_mini weui_btn_default" style="width: 100px; margin-right: 50px;">取消</a>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="jquery-weui/js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="jquery-weui/js/jquery-weui.js"></script>
	<script type="text/javascript" src="js/test.js"></script>
</html>