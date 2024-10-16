package sky.pro.kurs_project_2.service;

import org.springframework.stereotype.Service;
import sky.pro.kurs_project_2.dto.Question;

import java.util.Collection;

@Service
public interface QuestionService {

    Question addQuestion(String question, String answer);
    Question addQuestion(Question question);
    Question removeQuestion(String question, String answer);
    Question removeQuestion(Question question);
    Question findQuestion(String question);
    Collection<Question> allQuestions();
    Question getRandomQuestion();
}
