package com.company.lms.cloud1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.company.lms.cloud1.model.TestType;
public interface TestTypeRepository extends JpaRepository<TestType, Long> {
    boolean existsByTitle(String title);
}