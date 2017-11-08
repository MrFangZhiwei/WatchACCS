package com.keyone.common.pojo;

/** 
 * @class:SmsParam 
 * @descript:java 使用阿里大于短信通知验证码模板变量  
 * 短信模板变量，传参规则{"key":"value"}，key的名称须和申请模板中的变量名一致，多个变量之间以逗号隔开。 
 * 示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”， 
 * 传参时需传入{"code":"1234","product":"alidayu"}  
 * 备注： 
 * 模板变量首先要选择是验证码类型还是通知类型，然后添加模板如： 
 * 例如添加验证码模板：点击配置管理--验证码--配置短信模板，变量为模板内容中的自定义变量 
 * 例如添加短信通知模板：点击配置管理--短信通知--配置短信模板，变量为模板中的自定义变量 
 * @date:2017年3月7日 下午2:43:41 
 * @author fzw 
 */


public class SmsParam
{
	//短信模板变量：模板内容中的变量${product}  
	private String product;
	//短信模板变量：模板内容中的变量${number}，手机验证码  
	private String number;
	//短信模板变量：模板内容中的变量${ordernumber}，订单号  
	private String ordernumber;
	//短信模板变量：模板内容中的变量${disname}，关注商品地区  
	private String disname;
	//短信模板变量：模板内容中的变量${goodsinfoname}，货品名称  
	private String goodsinfoname;
	//短信模板变量：模板内容中的变量${ofollowprice}，关注商品收藏的价格  
	private String ofollowprice;
	//短信模板变量：模板内容中的变量${nfollowprice}，降低以后的新价格  
	private String nfollowprice;
	//短信模板变量：模板内容中的变量${bsetname}，站点名称  
	private String bsetname;

	public SmsParam()
	{
		super();
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getProduct()
	{
		return product;
	}

	public void setProduct(String product)
	{
		this.product = product;
	}

	public String getOrdernumber()
	{
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber)
	{
		this.ordernumber = ordernumber;
	}

	public String getDisname()
	{
		return disname;
	}

	public void setDisname(String disname)
	{
		this.disname = disname;
	}

	public String getGoodsinfoname()
	{
		return goodsinfoname;
	}

	public void setGoodsinfoname(String goodsinfoname)
	{
		this.goodsinfoname = goodsinfoname;
	}

	public String getOfollowprice()
	{
		return ofollowprice;
	}

	public void setOfollowprice(String ofollowprice)
	{
		this.ofollowprice = ofollowprice;
	}

	public String getNfollowprice()
	{
		return nfollowprice;
	}

	public void setNfollowprice(String nfollowprice)
	{
		this.nfollowprice = nfollowprice;
	}

	public String getBsetname()
	{
		return bsetname;
	}

	public void setBsetname(String bsetname)
	{
		this.bsetname = bsetname;
	}
}
