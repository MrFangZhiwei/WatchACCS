<%@page import="java.util.Date"%>
<%@page import="com.keyone.common.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<!DOCTYPE html>
<!--[if lt IE 7]><html class="ie ie6"><![endif]-->
<!--[if IE 7]><html class="ie ie7"><![endif]-->
<!--[if IE 8]><html class="ie ie8"><![endif]-->
<!--[if gt IE 8]><html class="ie ie9 mta_wechat" ><![endif]-->
<!--[if !IE]> -->
<html class='mta_wechat'>
<!-- <![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Language" content="zh-cn">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="viewport" content="width=1024,maximum-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" media="screen">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.qtip.min.css" media="screen">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.tagsinput.css" media="screen">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/push_style.css" media="screen">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<title>数据分析</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/push.css?v=r3.5.2" media="screen">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/push-sent.css?v=r3.5.2" media="screen">
<%-- <link rel="stylesheet" type="text/css" href="css/jtable_basic.css?v=r3.5.2" media="screen"> --%>


<style type="text/css">
label.contrast {
	float: left;
	margin: 4px 8px 0 8px;
	line-height: 20px;
	color: #666;
	cursor: pointer;
}

label.contrast .pc {
	vertical-align: -2px;
	margin: 2px
}

#div_feedback {
	display: none;
	background:
		url(https://mta.qq.com/mta/resource/imgcache/images/scrollTop.png)
		no-repeat 0px 0px;
	text-indent: -99999px;
	box-shadow: 0px 0px 1px #EEE;
	position: fixed;
	z-index: 150;
	right: 30px;
	bottom: 10px;
	width: 48px;
	height: 48px;
	cursor: pointer;
	display: none;
}

#div_feedback:hover {
	background-position: -48px 0px
}

.gri_fdate {
	width: 180px;
}

.gri_Date {
	width: 180px;
}

.gri_order {
	width: 60px;
}
.gri_wrapper table thead th {
	text-align: center;
}

td.gri_td_rowSpan {
	background: #F7F7F7;
}
</style>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/analysis/global.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/analysis/wechat_v3.css?t=1413787387">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/analysis/gri.controls.css?t=1413787387">



<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/layer/extend/layer.ext.js"></script>

<%-- <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script> --%>
<script src='http://www.ichartjs.com/ichart.latest.min.js'></script>
<%--<script type="text/javascript" src="js/ichart/ichart.1.2.min.js"></script>

--%><script type='text/javascript'>
	$(function() {
		var flow = ${temperature};
		var data = [ {
			name : 'PV',
			value : flow,
			color : '#ec4646',
			line_width : 2
		} ];

		var labels = ${tp_createtime};

		var chart = new iChart.LineBasic2D({
			render : 'ichart-render',
			data : data,
			align : 'center',
			title : {
				text : '${createtime}温度监测统计',
				font : '微软雅黑',
				fontsize : 24,
				color : '#b4b4b4'
			},
			subtitle : {
				text : '11:00-12:00温度达到最大值',
				font : '微软雅黑',
				color : '#b4b4b4'
			},
			width : 800,
			height : 400,
			shadow : true,
			shadow_color : '#202020',
			shadow_blur : 8,
			shadow_offsetx : 0,
			shadow_offsety : 0,
			background_color : '#2e2e2e',
			tip : {
				enable : true,
				shadow : true,
				listeners : {
					//tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
					parseText : function(tip, name, value, text, i) {
						return "<span style='color:#005268;font-size:12px;'>" + labels[i] + ":00温度约:<br/>"
								+ "</span><span style='color:#005268;font-size:20px;'>" + value + "℃</span>";
					}
				}
			},
			crosshair : {
				enable : true,
				line_color : '#ec4646'
			},
			sub_option : {
				smooth : true,
				label : false,
				hollow : false,
				hollow_inside : false,
				point_size : 8
			},
			coordinate : {
				width : 640,
				height : 260,
				striped_factor : 0.18,
				grid_color : '#4e4e4e',
				axis : {
					color : '#252525',
					width : [ 0, 0, 4, 4 ]
				},
				scale : [ {
					position : 'left',
					start_scale : 36,
					end_scale : 38,
					scale_space : 10,
					scale_size : 2,
					scale_enable : false,
					label : {
						color : '#fff',
						font : '微软雅黑',
						fontsize : 11,
						fontweight : 600
					},
					scale_color : '#9f9f9f'
				}, {
					position : 'bottom',
					label : {
						color : '#fff',
						font : '微软雅黑',
						fontsize : 11,
						fontweight : 600
					},
					scale_enable : false,
					labels : labels
				} ]
			}
		});
		//利用自定义组件构造左侧说明文本
		chart
				.plugin(new iChart.Custom(
						{
							drawFn : function() {
								//计算位置
								var coo = chart.getCoordinate(), x = coo.get('originx'), y = coo.get('originy'), w = coo.width, h = coo.height;
								//在左上侧的位置，渲染一个单位的文字
								chart.target.textAlign('start').textBaseline('bottom').textFont('600 11px 微软雅黑')
										.fillText('温度(℃)', x - 40, y - 12, false, '#fff').textBaseline('top')
										.fillText('(时间)', x + w + 12, y + h + 10, false, '#fff');

							}
						}));
		//开始画图
		chart.draw();
	});
	$(function() {
		var flow = ${humidity};
		var data = [ {
			name : 'PV',
			value : flow,
			color : '#ec4646',
			line_width : 2
		} ];

		var labels = ${hm_createtime};

		var chart = new iChart.LineBasic2D({
			render : 'ichart-render1',
			data : data,
			align : 'center',
			title : {
				text : '${createtime}湿度监测统计',
				font : '微软雅黑',
				fontsize : 24,
				color : '#b4b4b4'
			},
			subtitle : {
				text : '08:00-09:00湿度达到最大值',
				font : '微软雅黑',
				color : '#b4b4b4'
			},
			width : 800,
			height : 400,
			shadow : true,
			shadow_color : '#202020',
			shadow_blur : 8,
			shadow_offsetx : 0,
			shadow_offsety : 0,
			background_color : '#2e2e2e',
			tip : {
				enable : true,
				shadow : true,
				listeners : {
					//tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
					parseText : function(tip, name, value, text, i) {
						return "<span style='color:#005268;font-size:12px;'>" + labels[i] + ":00湿度约:<br/>"
								+ "</span><span style='color:#005268;font-size:20px;'>" + value + "%</span>";
					}
				}
			},
			crosshair : {
				enable : true,
				line_color : '#ec4646'
			},
			sub_option : {
				smooth : true,
				label : false,
				hollow : false,
				hollow_inside : false,
				point_size : 8
			},
			coordinate : {
				width : 640,
				height : 260,
				striped_factor : 0.18,
				grid_color : '#4e4e4e',
				axis : {
					color : '#252525',
					width : [ 0, 0, 4, 4 ]
				},
				scale : [ {
					position : 'left',
					start_scale : 97,
					end_scale : 100,
					scale_space : 10,
					scale_size : 2,
					scale_enable : false,
					label : {
						color : '#fff',
						font : '微软雅黑',
						fontsize : 11,
						fontweight : 600
					},
					scale_color : '#9f9f9f'
				}, {
					position : 'bottom',
					label : {
						color : '#fff',
						font : '微软雅黑',
						fontsize : 11,
						fontweight : 600
					},
					scale_enable : false,
					labels : labels
				} ]
			}
		});
		//利用自定义组件构造左侧说明文本
		chart
				.plugin(new iChart.Custom(
						{
							drawFn : function() {
								//计算位置
								var coo = chart.getCoordinate(), x = coo.get('originx'), y = coo.get('originy'), w = coo.width, h = coo.height;
								//在左上侧的位置，渲染一个单位的文字
								chart.target.textAlign('start').textBaseline('bottom').textFont('600 11px 微软雅黑')
										.fillText('湿度(%)', x - 40, y - 12, false, '#fff').textBaseline('top')
										.fillText('(时间)', x + w + 12, y + h + 10, false, '#fff');

							}
						}));
		//开始画图
		chart.draw();
	});
	$(function() {
		var flow = ${carbon};

		var data = [ {
			name : 'PV',
			value : flow,
			color : '#ec4646',
			line_width : 2
		} ];

		var labels = ${cb_createtime};

		var chart = new iChart.LineBasic2D({
			render : 'ichart-render2',
			data : data,
			align : 'center',
			title : {
				text : '${createtime}二氧化碳浓度监测统计',
				font : '微软雅黑',
				fontsize : 24,
				color : '#b4b4b4'
			},
			subtitle : {
				text : '12:00-13:00二氧化碳浓度达到最大值',
				font : '微软雅黑',
				color : '#b4b4b4'
			},
			width : 800,
			height : 400,
			shadow : true,
			shadow_color : '#202020',
			shadow_blur : 8,
			shadow_offsetx : 0,
			shadow_offsety : 0,
			background_color : '#2e2e2e',
			tip : {
				enable : true,
				shadow : true,
				listeners : {
					//tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
					parseText : function(tip, name, value, text, i) {
						return "<span style='color:#005268;font-size:12px;'>" + labels[i] + ":00二氧化碳浓度约:<br/>"
								+ "</span><span style='color:#005268;font-size:20px;'>" + value + "%</span>";
					}
				}
			},
			crosshair : {
				enable : true,
				line_color : '#ec4646'
			},
			sub_option : {
				smooth : true,
				label : false,
				hollow : false,
				hollow_inside : false,
				point_size : 8
			},
			coordinate : {
				width : 640,
				height : 260,
				striped_factor : 0.18,
				grid_color : '#4e4e4e',
				axis : {
					color : '#252525',
					width : [ 0, 0, 4, 4 ]
				},
				scale : [ {
					position : 'left',
					start_scale : 4,
					end_scale : 6,
					scale_space : 10,
					scale_size : 2,
					scale_enable : false,
					label : {
						color : '#fff',
						font : '微软雅黑',
						fontsize : 11,
						fontweight : 600
					},
					scale_color : '#9f9f9f'
				}, {
					position : 'bottom',
					label : {
						color : '#fff',
						font : '微软雅黑',
						fontsize : 11,
						fontweight : 600
					},
					scale_enable : false,
					labels : labels
				} ]
			}
		});
		//利用自定义组件构造左侧说明文本
		chart
				.plugin(new iChart.Custom(
						{
							drawFn : function() {
								//计算位置
								var coo = chart.getCoordinate(), x = coo.get('originx'), y = coo.get('originy'), w = coo.width, h = coo.height;
								//在左上侧的位置，渲染一个单位的文字
								chart.target.textAlign('start').textBaseline('bottom').textFont('600 11px 微软雅黑')
										.fillText('二氧化碳浓度(%)', x - 40, y - 12, false, '#fff').textBaseline('top')
										.fillText('(时间)', x + w + 12, y + h + 10, false, '#fff');

							}
						}));
		//开始画图
		chart.draw();
	});
</script>

<script type="text/javascript">
	function toType(index){
		//alert(index)
		$("#type").val(index);
		//alert($("#type").val());
		document.getElementById("myform_user_analysis").submit();
	}
</script>




	  <%-- <link href="js/daterangepicker/bootstrap.min.css" rel="stylesheet"> --%>
      <link href="${pageContext.request.contextPath}/js/daterangepicker/font-awesome.min.css" rel="stylesheet">
      <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/js/daterangepicker/daterangepicker-bs3.css" />
     <%--  <script type="text/javascript" src="js/daterangepicker/jquery-1.8.3.min.js"></script> --%>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker/bootstrap.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker/moment.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/daterangepicker/daterangepicker.js"></script>
</head>
<body>
	<!-- 头部   -->
	<nav class="navbar navbar-default navbar-fixed-top" >
		<div class="container">
			<div class="navbar-header">
				<a href="http://show.keyonecn.com/new" class="logo"> <img src="${pageContext.request.contextPath}/img/logo.png" alt=""> </a>
				<button class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse" >
				<ul class="nav navbar-nav navbar-right">
					<%
						for (int i = 0; i > -5; i--)
						{
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DATE, i); //得到前一天
							Date d = calendar.getTime();
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					%>
					<li ><a href="analysis.html?time=<%=df.format(d)%>"><span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;&nbsp;<%=df.format(d)%></a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</nav>


	<div class="content-wrap">
		<div class="container container-main">
		

	<div id="content">

		<div id="main" min-height: 610px">


			<div class="mod_basic">
				<!--标题栏目:start-->
				<div class="title">
					<div class="sub_menu">
						<div class="ui_filter">
							<div class="ui_filter_bar">
								<div class="group">
									<!-- 独立的日期选择器 -->
									<!--==时间范围start==-->
									<div class="tool_date cf">
										<strong class="lable time_lable">时间:</strong>
<!-- 										<div class="setup">
											<button id="btn_compare" class="button  button_primary">按时间对比</button>
										</div> -->
										<div style="line-height: 31px;float: left;" >
										${createtime}
											<img src="${pageContext.request.contextPath}/img/excel.png" width="20" height="20"/>
											<a href="#">导出数据</a>
										</div>
									</div>
									<!--==时间范围end==-->
								</div>
							</div>

						</div>
					</div>
				</div>
				<!--标题栏目:end-->

				<div class="content">
					<h4 class="sub_title">数据图</h4>
					<div class="sub_content">
						<!--图控件的模版，这里目前内容较少，后面方便扩展，直接在这里修改-->
						<div  id="chartContainer" class="time-chart" data-highcharts-chart="0">
							<div id='ichart-render'></div>
							<div id='ichart-render1'></div>
							<div id='ichart-render2'></div>
						</div>
					</div>
			

				</div>
			</div>
		
		</div>
		<!-- end of main -->
	</div>


			</div>

		
		</div>
		
</body>
</html>
