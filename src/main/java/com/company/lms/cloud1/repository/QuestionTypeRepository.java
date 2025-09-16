package com.company.lms.cloud1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.lms.cloud1.model.QuestionType;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
    boolean existsByName(String name);
    QuestionType findByName(String name);
}
