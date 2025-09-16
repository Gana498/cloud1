package com.company.lms.cloud1.service;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.company.lms.cloud1.model.Option;
import com.company.lms.cloud1.model.Question;
import com.company.lms.cloud1.model.QuestionType;
import com.company.lms.cloud1.model.TestType;
import com.company.lms.cloud1.repository.OptionRepository;
import com.company.lms.cloud1.repository.QuestionRepository;
import com.company.lms.cloud1.repository.QuestionTypeRepository;
import com.company.lms.cloud1.repository.TestTypeRepository;



@Service
@Transactional
public class ExcelTestService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Autowired
    private TestTypeRepository testTypeRepository;

    public void processExcelFile(MultipartFile file, Long questionTypeId, Long testTypeId) throws IOException {
        if (file.isEmpty()) return;

        QuestionType questionType = questionTypeRepository.findById(questionTypeId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid question type ID"));

        TestType testType = testTypeRepository.findById(testTypeId)
            .orElseThrow(() -> new IllegalArgumentException("Test not found"));

        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                // Validate all required cells
                //if (isRowIncomplete(row)) continue;

                String questionText = row.getCell(0).getStringCellValue().trim();
                String[] optionTexts = new String[5];
                for (int i = 0; i < 5; i++) {
                    if(row.getCell(i+1) != null)
                    optionTexts[i] = row.getCell(i + 1).getStringCellValue().trim();
                }
                int correctOption = (int) row.getCell(6).getNumericCellValue();

                Question question = new Question();
                question.setText(questionText);
                question.setType(questionType);
                question.setTestType(testType);

                question = questionRepository.save(question);

                for (int i = 0; i < optionTexts.length; i++) {
                    if(optionTexts[i] == null || optionTexts[i].isEmpty()) continue;
                    Option option = new Option();
                    option.setText(optionTexts[i]);
                    option.setCorrect((i + 1) == correctOption);
                    option.setQuestion(question);
                    optionRepository.save(option);
                }
            }
        }
    }

    private boolean isRowIncomplete(Row row) {
        for (int i = 0; i <= 5; i++) {
            Cell cell = row.getCell(i);
            if (cell == null || (i < 5 && cell.getCellType() != CellType.STRING) || (i == 5 && cell.getCellType() != CellType.NUMERIC)) {
                return true;
            }
        }
        return false;
    }
}
