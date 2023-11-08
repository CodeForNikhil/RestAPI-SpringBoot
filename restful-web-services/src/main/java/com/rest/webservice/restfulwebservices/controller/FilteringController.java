package com.rest.webservice.restfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.webservice.restfulwebservices.bean.SomeBean;

@RestController
public class FilteringController {

	@GetMapping(path = "/filtering")
	public SomeBean filtering() {
		return new SomeBean("value 1", "value 2", "value 3");
	}

	@GetMapping(path = "/filtering-list")
	public List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("value 1", "value 2", "value 3"),
				new SomeBean("value 4", "value 5", "value 6"));
	}

	// dynamic filtering cannot be done on beans, it needs to be done on controller.
	// the below code would not work unless the bean class does not have @JsonFilter
	// annotation.

	@GetMapping(path = "/filtering-dynamic")
	public MappingJacksonValue dynamicFiltering() {
		SomeBean someBean = new SomeBean("value 1", "value 2", "value 3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}

	@GetMapping(path = "/filtering-list-dynamic")
	public MappingJacksonValue dynamicFilteringList() {
		List<SomeBean> list = Arrays.asList(new SomeBean("value 1", "value 2", "value 3"),
				new SomeBean("value 4", "value 5", "value 6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");;
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
		mappingJacksonValue.setFilters(filters );
		
		return mappingJacksonValue;
	}
}
