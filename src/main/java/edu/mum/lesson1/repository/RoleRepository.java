package edu.mum.lesson1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.lesson1.models.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
}
