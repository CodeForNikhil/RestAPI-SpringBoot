package com.rest.webservice.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservice.restfulwebservices.bean.User;
import com.rest.webservice.restfulwebservices.exceptionhandling.UserNotFoundException;
import com.rest.webservice.restfulwebservices.service.UserDaoService;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	// Method to show all users
	@GetMapping(path = "/users")
	public List<User> retrieveAllUser() {
		return userDaoService.findAll();
	}

	// Method to show users with specific ID
	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User findOne = userDaoService.getById(id);

		// if not found, do not throw error, show blank page.
		// morph the error page into an JSON page where showing an customised error
		// message
		if (findOne==null) {
			throw new UserNotFoundException("id: " + id);
		}

		// EntityModel allows us to add more details to the bean class JSON output
		// without changing the basic structure of the JSON (Rest API).
		EntityModel<User> entityModel = EntityModel.of(findOne);

		// WebMvcBuilder helps to create a link.
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());

		// Now we add that link to the Rest API data output, under the "all-users" name.
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		// Assigning the obj to the object variable so that it can be used for URI
		// below.
		User addUser = userDaoService.addUser(user);

		// creating a URL, for easy access in Talend API tester (Chrome extention)to
		// check for the
		// newly added user.
		URI location = ServletUriComponentsBuilder
				// from current request gets the current /users url.
				.fromCurrentRequest()
				// path adds the /{id}
				.path("/{id}")
				// this now replaces the {id} with newly added user id.
				.buildAndExpand(addUser.getId())
				// converts to URL.
				.toUri();
		// returns the http:localhost:8080/users/{id} where id is replaced by newly
		// created user ID.
		// the above code actually creates the URL and inserts the ID in the place for
		// easy access
		// & verification.
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userDaoService.deleteUserById(id);
	}
}
