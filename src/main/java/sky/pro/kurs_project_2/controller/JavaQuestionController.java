package sky.pro.kurs_project_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.kurs_project_2.dto.Question;
import sky.pro.kurs_project_2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService questionService;


    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping
    public Collection<Question> allQuestions(){
        return questionService.allQuestions();
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer){
        return questionService.addQuestion(question,answer);
    }
    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer){
        return questionService.removeQuestion(question,answer);
    }
    @GetMapping("/find")
    public Question findQuestion(String question){
        return questionService.findQuestion(question);
    }
}
