package com.example.judoku.repository;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.judoku.model.Competition;
@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
	
	
}
