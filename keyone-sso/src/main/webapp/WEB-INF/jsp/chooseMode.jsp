<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!-- saved from url=(0044)https://passport.bilibili.com/register/phone -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>培养瓶选择</title>


<link rel="search" type="application/opensearchdescription+xml" href="https://static-s.bilibili.com/opensearch.xml" title="keyone">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="referrer" content="always">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,minimal-ui">
<link href="/css/normalize.css" rel="stylesheet">
<link href="/css/login-out.css" rel="stylesheet">
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<style>
.input-alert {
	display: none;
}
</style>
<link charset="utf-8" rel="stylesheet" href="css/white.css">
</head>
<body>
	<header>
		<a class="logo" href="http://www.keyonecn.com/"></a> <a class="logoText">培养瓶选择</a>
	</header>
	<div class="form">
		<form name="form2" id="passageCell" action="https://passport.bilibili.com/register/phone" method="post" onsubmit="return checkForm(this)">


			<div class="input-wrp" style="background: #ffffff">
				<select name="mode" id="mode" style="width: 100%;height: 100%">

					<option value="1" code="86">Back</option>
					<option value="2" code="86">Passage</option>
					<option value="3" code="86">Medium Change</option>
					<option value="4" code="86">Sale</option>
				</select>
			</div>
			<input type="hidden" name="gourl" value="http://www.bilibili.com"> <input type="hidden" name="dopost" value="regok">

			<div>
				<a class="button" id="sure">确认</a>
			</div>
		</form>
	</div>

	<script type="text/javascript">
	var redirectUrl = "${redirect}";
	var countdown = 60;
	var Passage = {
			doChooseId:function() {
				$.post("/passage/chooseModeImpl", $("#passageCell").serialize(),function(data){
					if (data.status == 200) {
						alert("返回");
					} else {
						alert("失败，原因是：" + data.msg);
					}
				});
			}
			,
			chooseId:function() {
					this.doChooseId();
			}
	};

	$(function(){
		$("#sure").click(function(){
			Passage.chooseId();
		});
	});
</script>

</body>
</html>
