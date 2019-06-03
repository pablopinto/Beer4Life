package com.beer.springboot.app.view.json;

import java.util.Map;

import org.springframework.data.auditing.MappingAuditableBeanWrapperFactory;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class Json extends MappingJackson2JsonView {

	@Override
	protected Object filterModel(Map<String, Object> model) {
		return super.filterModel(model);
	}

	
}
