package com.company.lms.cloud1.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.lms.cloud1.model.TestType;
import com.company.lms.cloud1.repository.TestTypeRepository;

@Service
public class TestTypeService {

    @Autowired
    private TestTypeRepository testTypeRepository;

    public List<TestType> getAllTypes() {
        return testTypeRepository.findAll();
    }
}
