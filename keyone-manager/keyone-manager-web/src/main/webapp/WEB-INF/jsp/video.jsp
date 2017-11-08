<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%--指令元素--%>
<%@ include file="/WEB-INF/jsp/common/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
<title>视频查看</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src='${pageContext.request.contextPath}/js/jquery.min.js'></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/js/bootstrap.min.js'></script>
<%@ include file="/WEB-INF/jsp/common/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/common/common_js.jsp"%>

<style type="text/css">
#scroll {
	position: fixed;
	top: 300px;
	right: 0px;
}

.scrollItem {
	background-color: #fff;
	color: #000;
	font-weight: bold;
	width: 19px;
	height: 100px;
	border: #e1e1e1 1px solid;
	cursor: pointer;
	text-align: center;
	padding-top: 10px;
}
</style>
<script type="text/javascript">
	$(function() {

		var speed = 1000; //滚动速度

		var h = document.body.offsetHeight;
		//回到顶部
		$("#toTop").click(function() {

			$('html,body').animate({

				scrollTop : '0px'

			}, speed);
		});
		
   
		
		//去到某个点

		//${jsStr}
	});
</script>
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="http://show.keyonecn.com/new" class="navbar-brand logo"> <img src="${pageContext.request.contextPath}/img/logo.png" alt=""> </a>
				<button class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<%
						for (int i = 0; i > -5; i--)
						{
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DATE, i); //得到前一天
							Date d = calendar.getTime();
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					%>
					<li ><a href="#<%=df.format(d)%>"><span class="glyphicon glyphicon-hand-right"></span>&nbsp;&nbsp;&nbsp;<%=df.format(d)%></a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<hgroup>
				<h1>视频查看</h1>
				<h4>细胞的最新动态、数据等</h4>
			</hgroup>
		</div>
	</div>
	<div id="case">
		<div class="container">
			<div class="row">
		
			</div>
		</div>
	</div>
	<footer id="footer">
		<div class="container">
			<p>企业介绍 | 合作事宜 | 版权投诉</p>
			<p>© 2009-2016 杭州键一生物科技有限公司</p>
		</div>
	</footer>
	<div id="scroll">
		<div id="toTop" class="scrollItem">回到顶部</div>
</body>
<script type="text/javascript">
//更改playUrl根据屏幕大小更换playUrl
if (window.screen.width > 768) {
	layui.use('flow', function(){
		var $ = layui.jquery; //独立版的layer无需执行这一句
		var flow = layui.flow;
		  flow.load({
			    elem: '.row' //指定列表容器
			    ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
			      var lis = [];
			      //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
			      $.get('${baseurl}/html/show/videolist?page='+page, function(res){
			        //假设你的列表返回在data集合中
			        layui.each(res.videolist, function(index, item){
			          lis.push(   '<a href='+item.videoUrl+'>' +
			        		  '    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3" id='+item.createTime+'>' +
			        		  '        <div class="thumbnail">' +
			        		  '            <img src='+item.pictureUrl+' alt="" style="width:300px;height:180px">' +
			        		  '            <div class="caption">' +
			        		  '                <h4><i class="glyphicon glyphicon-facetime-video"></i>&nbsp;&nbsp;'+item.videoName+'</h4>' +
			        		  '                <p><i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;时长:'+item.duration+'</p>' +
			        		  '                 <p><i class="glyphicon glyphicon-star"></i>&nbsp;&nbsp;'+item.createTime+'</p>' +
			        		  '            </div>'+
			        		  '        </div>'+
			        		  '    </div>'+
			        		  '</a>');
			        }); 
			        //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
			        //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
			        next(lis.join(''), page < res.pages);    
			      });
			    }
			  });
	});
}else{
	layui.use('flow', function(){
		var $ = layui.jquery; //独立版的layer无需执行这一句
		var flow = layui.flow;
		  flow.load({
			    elem: '.row' //指定列表容器
			    ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
			      var lis = [];
			      //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
			      $.get('${baseurl}/html/show/mobilevideolist?page='+page, function(res){
			        //假设你的列表返回在data集合中
			        layui.each(res.videolist, function(index, item){
			          lis.push(   '<a href='+item.videoUrl+'>' +
			        		  '    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3" id='+item.createTime+'>' +
			        		  '        <div class="thumbnail">' +
			        		  '            <img src='+item.pictureUrl+' alt="" style="width:300px;height:180px">' +
			        		  '            <div class="caption">' +
			        		  '                <h4><i class="glyphicon glyphicon-facetime-video"></i>&nbsp;&nbsp;'+item.videoName+'</h4>' +
			        		  '                <p><i class="glyphicon glyphicon-time"></i>&nbsp;&nbsp;时长:'+item.duration+'</p>' +
			        		  '                 <p><i class="glyphicon glyphicon-star"></i>&nbsp;&nbsp;'+item.createTime+'</p>' +
			        		  '            </div>'+
			        		  '        </div>'+
			        		  '    </div>'+
			        		  '</a>');
			        }); 
			        //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
			        //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
			        next(lis.join(''), page < res.pages);    
			      });
			    }
			  });
	});
}

</script>
</html>
