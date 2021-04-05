package com.springboot.crud.springbootcrud.repositories;

import com.springboot.crud.springbootcrud.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
