package com.keyone.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.keyone.common.pojo.KeyoneResult;
import com.keyone.common.pojo.SMSConstants;
import com.keyone.common.pojo.SmsParam;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/** 
 * @class:SmsUtil 
 * @descript:java 使用阿里大于短信通知工具类  
 * @date:2017年3月7日 下午2:47:38 
 * @author fzw 
 */
public class SmsUtil
{
	
	//请求地址    
	private static String URL = SMSConstants.URL;
	//TOP分配给应用的AppKey    
	private static String APP_KEY = SMSConstants.APP_KEY;
	//短信签名AppKey对应的secret值  
	private static String SECRET = SMSConstants.SECRET;
	//短信类型，传入值请填写normal     
	private static String SMS_TYPE = SMSConstants.SMS_TYPE;
	//阿里大于账户配置的短信签名    
	private static String SMS_SIGN = SMSConstants.SMS_SIGN;
	//阿里大于账户配置的短信模板ID    
	private static String SMS_TEMPLATE_CODE = SMSConstants.SMS_TEMPLATE_CODE;

	/** 
	 * @param phone 必填参数，手机号码 
	 * @return 
	 * @throws Exception 
	 */

	public KeyoneResult  sendMsgCode(String phone) throws Exception
	{
		//随机生成6位数字作为验证码  
		String code = getCode();
		//System.out.println("验证码code:" + code);
		//获得第三方阿里云短信通知接口  
		TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET);
		//获得短信通知请求头  
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//短信通知类型  
		req.setSmsType(SMS_TYPE);
		//短信通知签名  
		req.setSmsFreeSignName(SMS_SIGN);
		//短信接收号码:传入号码为11位手机号码不能加0或+86,最多传入200个号码,多个号码以逗号分隔  
		req.setRecNum(phone);
		//短信通知参数json格式  
		SmsParam smsParamVo = new SmsParam();
		smsParamVo.setNumber(code);
		smsParamVo.setProduct(SMS_SIGN);
		String smsParam = JSONObject.toJSONString(smsParamVo);
		//System.out.println("短信通知参数smsParam:" + smsParam);
		//短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开    
		req.setSmsParamString(smsParam);
		//短信模板ID    
		req.setSmsTemplateCode(SMS_TEMPLATE_CODE);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		JSONObject json = JSON.parseObject(rsp.getBody());
		String jsonStr = json.getString("alibaba_aliqin_fc_sms_num_send_response");
		if (jsonStr != null && !jsonStr.isEmpty())
		{
			json = JSON.parseObject(jsonStr);
			String result = json.getString("result");
			if (result != null && !result.isEmpty())
			{
				json = JSON.parseObject(result);
			//	System.out.println("json:" + json);
				String errorCode = json.getString("err_code");
				if ("0".equals(errorCode))
				{
					//发送成功    
					//System.out.println("发送成功");
					return KeyoneResult.build(200, code);
				} else
				{
					//发送失败  
					//System.out.println("发送失败");
					return KeyoneResult.build(400, "验证码发过过于频繁");
					
				}
			}
		}
		//发送失败  
	//	System.out.println("发送失败");
		return KeyoneResult.build(400, "验证码发过过于频繁");
	}

	/** 
	 *  
	 * @param phone   用户手机号码 
	 * @param smsUrl  oss请求url 
	 * @param smsAppKey  oss应用App key 
	 * @param smsSerect  oss应用Serect 
	 * @param smsSign      oss应用名 
	 * @param smsModelType oss模板类型1表示验证码类2表示通知类型 
	 * @param smsModelId     oss模板id 
	 * @param code     验证码或者订单号 
	 * @return 
	 * @throws Exception 
	 */

	public boolean sendMsgCode(String phone, String smsUrl, String smsAppKey, String smsSerect, String smsSign,
			String smsModelType, String smsModelId, JSONObject modelParam) throws Exception
	{
		//获得第三方阿里云短信通知接口  
		TaobaoClient client = new DefaultTaobaoClient(smsUrl, smsAppKey, smsSerect);
		//获得短信通知请求头  
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		//短信通知类型  
		req.setSmsType(SMS_TYPE);
		//短信通知签名  
		req.setSmsFreeSignName(smsSign);
		//短信接收号码:传入号码为11位手机号码不能加0或+86,最多传入200个号码,多个号码以逗号分隔  
		req.setRecNum(phone);
		//短信通知参数json格式  
		SmsParam smsParamVo = new SmsParam();
		if ("1".equals(smsModelType))
		{
			//短信验证码中的变量${number}  
			smsParamVo.setNumber(modelParam.getString("code"));
		} else if ("2".equals(smsModelType))
		{
			//短信通知中订单号变量${ordernumber}  
			smsParamVo.setOrdernumber(modelParam.getString("code"));
		} else if ("21".equals(smsModelType))
		{
			smsParamVo.setDisname(modelParam.getString("disname"));
			smsParamVo.setGoodsinfoname(modelParam.getString("goodsinfoname"));
			smsParamVo.setOfollowprice(modelParam.getString("ofollowprice"));
			smsParamVo.setNfollowprice(modelParam.getString("nfollowprice"));
		} else if ("211".equals(smsModelType))
		{
			smsParamVo.setBsetname(modelParam.getString("bsetname"));
			smsParamVo.setGoodsinfoname(modelParam.getString("goodsinfoname"));
		} else if ("212".equals(smsModelType))
		{
			smsParamVo.setGoodsinfoname(modelParam.getString("goodsinfoname"));
		} else if ("213".equals(smsModelType))
		{

		}
		smsParamVo.setProduct(smsSign);
		String smsParam = JSONObject.toJSONString(smsParamVo);
		//System.out.println("短信通知参数smsParam:"+smsParam);  
		//短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开  
		//案例：req.setSmsParamString("{\"code\":\"1234\",\"product\":\"alidayu\"}");  
		req.setSmsParamString(smsParam);
		//短信模板ID  
		req.setSmsTemplateCode(smsModelId);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		JSONObject json = JSON.parseObject(rsp.getBody());
		String jsonStr = json.getString("alibaba_aliqin_fc_sms_num_send_response");
		if (jsonStr != null && !jsonStr.isEmpty())
		{
			json = JSON.parseObject(jsonStr);
			String result = json.getString("result");
			if (result != null && !result.isEmpty())
			{
				json = JSON.parseObject(result);
				//System.out.println("json:"+json);  
				String errorCode = json.getString("err_code");
				if ("0".equals(errorCode))
				{
					//发送成功    
					return true;
				} else
				{
					//发送失败  
					return false;
				}
			}
		}
		//发送失败  
		return false;
	}

	/** 
	 * 随机生成6位数字作为验证码 
	 * @return 
	 */
	public static String getCode()
	{
		int code = (int) (Math.random() * 9000 + 100000);
		return code + "";
	}

	//测试  
	public KeyoneResult smsSend(String phoneNumber)
	{
		try
		{
			String phone = phoneNumber;
			String smsUrl = "http://gw.api.taobao.com/router/rest";
			String smsAppKey = "23669622";
			String smsSerect = "77c99b227ff054dc44fab8b9d41f975a";
			String smsSign = "千匠";
			//模板类型：1验证码类2通知类  
			String smsModelType = "212";
			String smsModelId = "SMS_57355168";
			JSONObject modelParam = new JSONObject();
			//modelParam.put("disname", "华东");  
			//modelParam.put("goodsinfoname", "大洁儿儿");
			//modelParam.put("ofollowprice", "6200.99");  
			//modelParam.put("nfollowprice", "6199.00");  
			//modelParam.put("bsetname", "千米网站");  
			SmsUtil m = new SmsUtil();
			KeyoneResult result = m.sendMsgCode(phone);
			//发送其他短信信息
			//boolean result = m.sendMsgCode(phone, smsUrl, smsAppKey, smsSerect, smsSign, smsModelType, smsModelId,modelParam);
			return result;
		} catch (Exception e)
		{
			e.printStackTrace();
			//System.out.println("发送失败");
			return KeyoneResult.build(400, "验证码发过过于频繁");
		}

	}

}
