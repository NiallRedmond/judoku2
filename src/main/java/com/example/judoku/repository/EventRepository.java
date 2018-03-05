package com.example.judoku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.judoku.model.User;

@Repository
public interface EventRepository extends JpaRepository<User, Long> {


}
