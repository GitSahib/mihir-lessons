package edu.mum.lesson1.service;

import edu.mum.lesson1.models.User;

public interface UserService {
	User save(User user);
	User update(String username,User user);
	void delete(User user);
	User findByUserName(String username);
	boolean authenticat(User user);
}
