package com.company.lms.cloud1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.lms.cloud1.model.QuestionType;
import com.company.lms.cloud1.repository.QuestionTypeRepository;

@Service
public class QuestionTypeService {
    @Autowired
    private QuestionTypeRepository repository;

    public List<QuestionType> getAllTypes() {
        return repository.findAll();
    }
}
