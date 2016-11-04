package edu.mum.lesson1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.lesson1.models.User;
import edu.mum.lesson1.repository.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public void changePassword(String username, String newPassword) {
		User user = userRepository.findByUsername(username);
		user.setPassword(bCryptPasswordEncoder.encode(newPassword));
		userRepository.saveAndFlush(user);
	}

	@Transactional
	@Override
	public void deleteUser(String username) {

	}

	@Override
	public User getLoggedInUser() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserDetails userDetails = auth == null ? null : (UserDetails) auth.getPrincipal();
			String userName = userDetails == null ? "guest" : userDetails.getUsername();
			return findByUsername(userName);
		} catch (ClassCastException ex) {
			return null;
		} catch (Exception ex) {
			return null;
		}
	}

	@Transactional
	@Override
	public void updateUser(String username, User user) {
		User userOld = userRepository.findByUsername(username);
		userOld.setPassword(user.getPassword());
		userOld.setRoles(user.getRoles());
		userRepository.saveAndFlush(user);
	}

	@Transactional(readOnly = true)
	@Override
	public boolean userExists(String username) {
		return userRepository.findByUsername(username) != null;
	}

	@Override
	public boolean authenticate(String password) {
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, password, userDetails.getAuthorities());
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			return usernamePasswordAuthenticationToken.isAuthenticated();
		} catch (AuthenticationException ex) {
			return false;
		} catch (Exception ex) {
			return false;
		}

	}

	@Transactional(readOnly = true)
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}
}
