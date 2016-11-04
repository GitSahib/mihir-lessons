package edu.mum.lesson1.service;

import java.util.List;

import edu.mum.lesson1.models.Role;

public interface RoleService {
    void save(Role role);
    Role findRoleByName(String name);
	List<Role> findAll();
}
