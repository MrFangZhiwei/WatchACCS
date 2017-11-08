<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang='en'>
<head>
<meta charset='UTF-8'>
<title>图表记录</title>
<script src='http://www.ichartjs.com/ichart.latest.min.js'></script>
<script type='text/javascript'>
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
				text : '每日温度监测统计',
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
						color : '#ffffff',
						font : '微软雅黑',
						fontsize : 11,
						fontweight : 600
					},
					scale_color : '#9f9f9f'
				}, {
					position : 'bottom',
					label : {
						color : '#ffffff',
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
										.fillText('温度(℃)', x - 40, y - 12, false, '#ffffff').textBaseline('top')
										.fillText('(时间)', x + w + 12, y + h + 10, false, '#ffffff');

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
				text : '每日湿度监测统计',
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
						color : '#ffffff',
						font : '微软雅黑',
						fontsize : 11,
						fontweight : 600
					},
					scale_color : '#9f9f9f'
				}, {
					position : 'bottom',
					label : {
						color : '#ffffff',
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
										.fillText('湿度(%)', x - 40, y - 12, false, '#ffffff').textBaseline('top')
										.fillText('(时间)', x + w + 12, y + h + 10, false, '#ffffff');

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
				text : '每日二氧化碳浓度监测统计',
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
						color : '#ffffff',
						font : '微软雅黑',
						fontsize : 11,
						fontweight : 600
					},
					scale_color : '#9f9f9f'
				}, {
					position : 'bottom',
					label : {
						color : '#ffffff',
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
										.fillText('二氧化碳浓度(%)', x - 40, y - 12, false, '#ffffff').textBaseline('top')
										.fillText('(时间)', x + w + 12, y + h + 10, false, '#ffffff');

							}
						}));
		//开始画图
		chart.draw();
	});
</script>
</head>
<body style='background-color:#ccc;'>
	<div id='ichart-render' ></div>
	<div id='ichart-render1'></div>
	<div id='ichart-render2'></div>
</body>
</html>