package com.company.lms.cloud1.configurations;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;  
import com.company.lms.cloud1.repository.QuestionTypeRepository;
import com.company.lms.cloud1.model.QuestionType;

@Configuration
public class QuestionTypeInitializer {
    // This class can be used to initialize data at application startup
    // Create question types if they do not exist
    @Bean
    public CommandLineRunner initQuestionTypeData(QuestionTypeRepository questionTypeRepository) {
        return args -> {
            // Create default question types
            createQuestionTypeIfNotExists(questionTypeRepository, "MCQ - Text");
            createQuestionTypeIfNotExists(questionTypeRepository, "MCQ - Image");
            createQuestionTypeIfNotExists(questionTypeRepository, "True/False");
            createQuestionTypeIfNotExists(questionTypeRepository, "Fill in the Blanks");
        };
    }

    private void createQuestionTypeIfNotExists(QuestionTypeRepository questionTypeRepository, String name) {
        if (!questionTypeRepository.existsByName(name)) {
            QuestionType questionType = new QuestionType();
            questionType.setName(name);
            questionTypeRepository.save(questionType);
        }
    }
}