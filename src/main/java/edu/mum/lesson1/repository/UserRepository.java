package edu.mum.lesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.lesson1.models.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	User findByUsername(String username);
	User findByUsernameAndPassword(String username,String password);
}
