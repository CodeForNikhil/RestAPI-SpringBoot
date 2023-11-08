package com.rest.webservice.restfulwebservices.bean;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

//in H2 database, the user is reserved keyword.
@Entity(name = "user_details")
public class User {

	@Id
	@GeneratedValue
	private int id;

	// JsonProperty helps to rename the tag to a specified attribute.
//	@JsonProperty("user_name")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;

//	@JsonProperty("birth_date")
	@Past(message = "Birth Date should be in past")
	private LocalDate birthdate;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> post;

	public User() {
	}

	public User(int id, String name, LocalDate birthdate) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}

}
