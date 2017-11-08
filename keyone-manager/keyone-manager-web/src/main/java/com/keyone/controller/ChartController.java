package com.keyone.controller;

import com.keyone.common.util.DateUtil;
import com.keyone.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * 图表跳转Controller
 * <p>Title: PageController</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2016-11-21下午8:11:42
 * @version  1.0
 */
@Controller
public class ChartController
{
	@Autowired
	private ParameterService parameterService;

	@RequestMapping("/show/analysis")
	public String showPage(@RequestParam(value = "time", defaultValue = "") String time, Model temperature,
			Model tp_createtime, Model humidity, Model hm_createtime, Model carbon, Model cb_createtime, Model reatetime)
	{
		String nowtime = DateUtil.date2String(new Date(), "yyyy-MM-dd");
		if (time.equals(""))
		{
			time = nowtime;
		}
		String parameter = parameterService.getParamItemById(time);

		if (!(parameter.equals("")))
		{
			String[] parameterList = parameter.split("_");
			temperature.addAttribute("temperature", parameterList[0]);
			tp_createtime.addAttribute("tp_createtime", parameterList[1]);
			humidity.addAttribute("humidity", parameterList[2]);
			hm_createtime.addAttribute("hm_createtime", parameterList[3]);
			carbon.addAttribute("carbon", parameterList[4]);
			cb_createtime.addAttribute("cb_createtime", parameterList[5]);
			reatetime.addAttribute("createtime", parameterList[6]);
		}
		return "analysis";
	}
}
