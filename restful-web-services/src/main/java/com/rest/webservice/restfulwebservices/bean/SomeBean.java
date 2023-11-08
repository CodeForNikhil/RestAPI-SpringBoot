package com.rest.webservice.restfulwebservices.bean;

import com.fasterxml.jackson.annotation.JsonFilter;

//JsonIgnoreProperties is the static filtering which will work similar to the
//JsonIgnore. The difference is this is applied on class and JsonIgnore is applied on 
//the attribute, also we have to specify which attribute needs to be filtered.

//@JsonIgnoreProperties({"field1","field2"})

//the below annotation is for Dynamic filtering which is done in FilteringController class.
@JsonFilter("SomeBeanFilter")
public class SomeBean {

	private String field1;

	// if we wish to hide, or ignore some field and not allow it to show on the
	// response body/
	// web page as the data retrieved, we can use the @JsonIgnore for static
	// filtering.
	// Static filtering is the permanent value filtering for all Rest API web
	// request.
	// means that the specific field/attribute would never be shown on the Web API
	// request. but
	// can be set.

//	@JsonIgnore
	private String field2;

	private String field3;

	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	@Override
	public String toString() {
		return "SomeBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}

}
