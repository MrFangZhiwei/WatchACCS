package com.keyone.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keyone.mapper.KoParameterMapper;
import com.keyone.pojo.KoParameter;
import com.keyone.pojo.KoParameterExample;
import com.keyone.pojo.KoParameterExample.Criteria;
import com.keyone.service.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService
{
	@Autowired
	private KoParameterMapper koParameterMapper;

	@Override
	public String getParamItemById(String time)
	{
		KoParameterExample example = new KoParameterExample();
		Criteria criteria = example.createCriteria();
		criteria.andCreatetimeEqualTo(time);
		List<KoParameter> list = koParameterMapper.selectByExample(example);
		StringBuffer sb = new StringBuffer();
		for (KoParameter parameter : list)
		{
			String temperature = parameter.getTemperature();
			String tpCreatetime = parameter.getTpCreatetime();
			String humidity = parameter.getHumidity();
			String hmCreatetime = parameter.getHmCreatetime();
			String carbon = parameter.getCarbon();
			String cbCreatetime = parameter.getCbCreatetime();
			String createtime = parameter.getCreatetime();
			sb.append(temperature);
			sb.append("_");
			sb.append(tpCreatetime);
			sb.append("_");
			sb.append(humidity);
			sb.append("_");
			sb.append(hmCreatetime);
			sb.append("_");
			sb.append(carbon);
			sb.append("_");
			sb.append(cbCreatetime);
			sb.append("_");
			sb.append(createtime);
		}
		return sb.toString();
	}

}
