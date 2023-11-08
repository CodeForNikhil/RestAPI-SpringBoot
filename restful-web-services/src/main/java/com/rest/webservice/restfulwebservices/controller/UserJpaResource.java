package com.rest.webservice.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.rest.webservice.restfulwebservices.bean.Post;
import com.rest.webservice.restfulwebservices.bean.User;
import com.rest.webservice.restfulwebservices.dao.PostRepository;
import com.rest.webservice.restfulwebservices.dao.UserRepository;
import com.rest.webservice.restfulwebservices.exceptionhandling.UserNotFoundException;

@RestController
public class UserJpaResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	// Method to show all users
	@GetMapping(path = "/jpa/users")
	public List<User> retrieveAllUser() {
		return userRepository.findAll();
	}

	// Method to show users with specific ID
	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		// if not found, do not throw error, show blank page.
		// morph the error page into an JSON page where showing an customised error
		// message
		if (!user.isPresent()) {
			throw new UserNotFoundException("id: " + id);
		}

		// EntityModel allows us to add more details to the bean class JSON output
		// without changing the basic structure of the JSON (Rest API).
		EntityModel<User> entityModel = EntityModel.of(user.get());

		// WebMvcBuilder helps to create a link.
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());

		// Now we add that link to the Rest API data output, under the "all-users" name.
		entityModel.add(link.withRel("all-users"));

		return entityModel;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		// Assigning the obj to the object variable so that it can be used for URI
		// below.
		User addUser = userRepository.save(user);

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

	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	
	
	//For Post Bean API's
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> retrievePostForUser(@PathVariable int id) {
		 Optional<User> user = userRepository.findById(id);
		 
		 if(!user.isPresent()) {
			 throw new UserNotFoundException("id: "+id);
		 }
		 
		 return user.get().getPost();
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("id: "+id);
		}
		post.setUser(user.get());
		
		Post postUri = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}").buildAndExpand(postUri.getId()).toUri();
		return ResponseEntity.created(location).build();
				
	}
}
