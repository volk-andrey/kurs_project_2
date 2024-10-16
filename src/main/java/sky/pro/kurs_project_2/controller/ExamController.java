package sky.pro.kurs_project_2.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.kurs_project_2.dto.Question;
import sky.pro.kurs_project_2.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/get")
public class ExamController {
    public final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/{amount}")
    public Collection<Question>  getQuestions(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
