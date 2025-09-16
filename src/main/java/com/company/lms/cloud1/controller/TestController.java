package com.company.lms.cloud1.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.company.lms.cloud1.service.ExcelTestService;
import com.company.lms.cloud1.service.QuestionTypeService;
import com.company.lms.cloud1.service.TestTypeService;
@Controller
public class TestController {

    @Autowired
    private QuestionTypeService questionTypeService;

    @Autowired
    private TestTypeService testTypeService;

     @GetMapping("/managetests")
    public String getManageTestsPage() {
        return "managetests"; // Returns the view name "managetests"
    }
    
    @GetMapping("/create-test")
    public String getCreateTestPage() {
        return "createtest"; // Returns the view name "createtest"
    }

    @GetMapping("/create-test/excel")
    public String showExcelUploadPage(Model model) {
        model.addAttribute("questionTypes", questionTypeService.getAllTypes());
        model.addAttribute("testTypes", testTypeService.getAllTypes());
        return "create-test-excel";
    }
    
    @Autowired
    private ExcelTestService excelTestService;

    @PostMapping("/create-test/excel")
    public String handleExcelUpload(@RequestParam("file") MultipartFile file,
                                    @RequestParam("questionType") Long questionTypeId,
                                    @RequestParam("testType") Long testTypeId) throws IOException {
        excelTestService.processExcelFile(file, questionTypeId, testTypeId);
        return "redirect:/managetests";
    }

}

