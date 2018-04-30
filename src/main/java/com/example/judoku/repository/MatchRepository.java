package com.example.judoku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.judoku.model.Match;




@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
