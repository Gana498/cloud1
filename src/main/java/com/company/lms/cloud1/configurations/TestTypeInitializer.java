package com.company.lms.cloud1.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.company.lms.cloud1.repository.TestTypeRepository;
import com.company.lms.cloud1.model.TestType;


@Configuration
public class TestTypeInitializer {

    @Bean
    public CommandLineRunner initTestTypeData(TestTypeRepository testTypeRepository) {
        return args -> {
            // Create default test types
            createTestTypeIfNotExists(testTypeRepository, "Mock Test 1");
            createTestTypeIfNotExists(testTypeRepository, "Mock Test 2");
            createTestTypeIfNotExists(testTypeRepository, "Mock Test 3");
        };
    }

    private void createTestTypeIfNotExists(TestTypeRepository testTypeRepository, String title) {
        if (!testTypeRepository.existsByTitle(title)) {
            TestType testType = new TestType();
            testType.setTitle(title);
            testTypeRepository.save(testType);
        }
    }
}
