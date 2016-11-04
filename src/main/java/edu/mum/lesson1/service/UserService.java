package edu.mum.lesson1.service;

import java.util.List;

import edu.mum.lesson1.models.User;

public interface UserService {
    User save(User user);
    
    User findByUsername(String username);

	void deleteUser(String username);

	
	void changePassword(String username, String newPassword);

	void updateUser(String username, User user);

	boolean userExists(String username);

	boolean authenticate(String password);

	User getLoggedInUser();

	List<User> findAll();

	User findById(Long id);
}
