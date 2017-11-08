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
<title>远程控制登录</title>

<link rel="search" type="application/opensearchdescription+xml" href="https://static-s.bilibili.com/opensearch.xml" title="keyone">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="referrer" content="always">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,minimal-ui">
<link href="css/normalize.css" rel="stylesheet">
<link href="css/login-out.css" rel="stylesheet">
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
		<a class="logo" href="http://www.keyonecn.com/"></a> <a class="logoText">登录</a>
	</header>
	<div class="form">
		<form name="form2" id="regUser" action="https://passport.bilibili.com/register/phone" method="post" onsubmit="return checkForm(this)">


			<div class="input-wrp" style="background: #ffffff">
				<select name="country_code" id="country_code" style="width: 100%;height: 100%">

					<option value="1" code="86">中国大陆</option>

					<option value="5" code="852">香港特别行政区</option>

					<option value="2" code="853">澳门特别行政区</option>

					<option value="3" code="886">台湾地区</option>

					<option value="4" code="1">美国</option>

					<option value="6" code="32">比利时</option>

					<option value="7" code="61">澳大利亚</option>

					<option value="8" code="33">法国</option>

					<option value="9" code="1">加拿大</option>

					<option value="10" code="81">日本</option>

					<option value="11" code="65">新加坡</option>

					<option value="12" code="82">韩国</option>

					<option value="13" code="60">马来西亚</option>

					<option value="14" code="44">英国</option>

					<option value="15" code="39">意大利</option>

					<option value="16" code="49">德国</option>

					<option value="18" code="7">俄罗斯</option>

					<option value="19" code="64">新西兰</option>

				</select>
			</div>
			<input type="hidden" name="gourl" value="http://www.bilibili.com"> <input type="hidden" name="dopost" value="regok">

			<div class="input-wrp">
				<input type="text" name="phone" placeholder="您的手机" autocomplete="off" id="new_phone">
			</div>
			<span class="noticeText" id="tel-msg" style="display:none;">亲，看起来不像手机号呢！</span>

			<div class="input-wrp captcha" style="border: 0;background: none">
				<input type="text" name="identifycode" id="sms-code" placeholder="短信验证码" autocomplete="off" style="width: 60%;border: 1px solid #ddd;"> <input type="button" id="getCaptch" value="获取短信验证码" style="width:38%">
			</div>

			<div>
				<a class="button" id="login">登陆</a>
			</div>
		</form>
	</div>

	<script type="text/javascript">
	var redirectUrl = "${redirect}";
	var countdown = 60;
	var myreg = /^1[3458]\d{9}$/;
	var Idenfity = {
			checkInput:function() {
				if ($("#new_phone").val() == "") {
					alert("手机不能为空");
					$("#new_phone").focus();
					return false;
				}else if(!myreg.exec($("#new_phone").val())) {
					alert("输入的非手机号码");
					$("#new_phone").focus();
					return false;
				}
				return true;
			},
			
			doLogin:function() {
				$.post("/sms/idenfiy", $("#regUser").serialize(),function(data){
					if (data.status == 200) {
						//alert("获取验证码成功！");
						Idenfity.refresh();
					} else {
						alert("登录失败，原因是：" + data.msg);
						$("#new_phone").select();
					}
				});
			},
			login:function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			},
			refresh:function(){
				 if (countdown == 0) {
					 $("#getCaptch").attr("disabled",false);
					 $("#getCaptch").attr("value", "获取短信验证码");
				        countdown = 60;
				    } else {
				    	$("#getCaptch").attr("disabled", true);
				    	$("#getCaptch").attr("value","重新发送(" + countdown + ")") ;
				        countdown--;
				        setTimeout(function() {
							   Idenfity.refresh();
						    }, 1000);
				    }
				 ;
			}
		
	};
	
	$(function(){
		$("#getCaptch").click(function(){
			Idenfity.login();
			
		});
	});
	
	var LOGIN = {
			checkInput:function() {
				if ($("#sms-code").val() == "") {
					alert("验证码不能为空");
					$("#sms-code").focus();
					return false;
				}else if($("#new_phone").val() == "") {
					alert("手机不能为空");
					$("#new_phone").focus();
					return false;
				}else if(!myreg.exec($("#new_phone").val())) {
					alert("输入的非手机号码");
					$("#new_phone").focus();
					return false;
				}
				return true;
			},
			doLogin:function() {
				$.post("/sms/userlogin", $("#regUser").serialize(),function(data){
					if (data.status == 200) {
						alert("验证码通过！");
						location.href = "http://www.keyonecn.com:8082/passage/chooseId";
					} else {
						alert("登录失败，原因是：" + data.msg);
						$("sms-code").select();
					}
				});
			}
			,
			login:function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			}
	};


	
	$(function(){
		$("#login").click(function(){
			LOGIN.login();
		});
	});
</script>

	<script type="text/javascript">

</script>
</body>
</html>
