package com.company.lms.cloud1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.company.lms.cloud1.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}