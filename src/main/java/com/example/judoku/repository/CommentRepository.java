package com.example.judoku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.judoku.model.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
