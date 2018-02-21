package com.example.judoku.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.judoku.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}