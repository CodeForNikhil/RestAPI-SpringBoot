package com.rest.webservice.restfulwebservices.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.rest.webservice.restfulwebservices.bean.User;

@Component
public class UserDaoService {

	private static List<User> userlist = new ArrayList<>();

	static int cnt = 0;
	static {
		userlist.add(new User(++cnt, "Nikhil", LocalDate.now().minusYears(29)));
		userlist.add(new User(++cnt, "Atharva", LocalDate.now().minusYears(22)));
		userlist.add(new User(++cnt, "Aditya", LocalDate.now().minusYears(21)));

	}

	public List<User> findAll() {
		return userlist;
	}

	public User getById(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id;
//		System.out.println("The Predicate returns " + predicate);
		return userlist.stream().filter(predicate).findFirst().orElse(null);
	}

	public User addUser(User user) {
		user.setId(++cnt);
		userlist.add(user);
		return user;
	}

	public void deleteUserById(int id) {
		Predicate<? super User> predicate = user -> user.getId() == id;
		userlist.removeIf(predicate);
	}
}
