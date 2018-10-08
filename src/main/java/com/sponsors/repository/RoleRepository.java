package com.sponsors.repository;

import com.sponsors.model.Role;
import com.sponsors.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String candidate);
}