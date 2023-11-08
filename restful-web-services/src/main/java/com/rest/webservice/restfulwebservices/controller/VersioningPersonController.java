package com.rest.webservice.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.restfulwebservices.bean.Name;
import com.rest.webservice.restfulwebservices.bean.PersonV1;
import com.rest.webservice.restfulwebservices.bean.PersonV2;

@RestController
public class VersioningPersonController {
	
	//URI versioning
	@GetMapping(path="/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Nikhil Nandanwar");
	}
	
	@GetMapping(path="/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Nikhil", "Nandanwar"));
	}
	
	//Request Parameter Versioning
	@GetMapping(path="/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Nikhil Nandanwar");
	}
	
	@GetMapping(path="/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("Nikhil", "Nandanwar"));
	}
	
	//Custom Headers versioning
	@GetMapping(path="/person/header",headers= "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Nikhil Nandanwar");
	}
	
	@GetMapping(path="/person/header",headers= "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Nikhil", "Nandanwar"));
	}
	
	//Media type versioning aka "Content negotiation" or "Accept header"
	@GetMapping(path="/person/accept",produces= "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Nikhil Nandanwar");
	}
	
	@GetMapping(path="/person/accept",produces= "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Nikhil", "Nandanwar"));
	}
}
