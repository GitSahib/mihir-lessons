package edu.mum.lesson1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.lesson1.models.User;
import edu.mum.lesson1.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userRepository;
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User update(String username, User user) {
		// TODO Auto-generated method stub
		User userOld = userRepository.findByUsername(username);
		if(userOld != null)
		{
			userOld.setUsername(user.getUsername());
			userOld.setPassword(user.getPassword());
			return userRepository.saveAndFlush(userOld);
		}
		return null;
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userRepository.delete(user);
	}

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean authenticat(User user) {
		// TODO Auto-generated method stub
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null;
	}

}
