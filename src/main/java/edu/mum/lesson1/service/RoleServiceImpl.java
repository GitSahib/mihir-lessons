package edu.mum.lesson1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.lesson1.models.Role;
import edu.mum.lesson1.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
    
	@Override
	public Role findRoleByName(String name) {
		// TODO Auto-generated method stub
		return roleRepository.findByName(name);
	}

	@Override
	public void save(Role role) {
		// TODO Auto-generated method stub
		roleRepository.save(role);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}
}
