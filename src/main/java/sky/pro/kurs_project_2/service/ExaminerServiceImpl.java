package sky.pro.kurs_project_2.service;

import org.springframework.stereotype.Service;
import sky.pro.kurs_project_2.model.Question;
import sky.pro.kurs_project_2.exeption.QuestionAmountMissMatchException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> allQuestions = questionService.allQuestions();
        if (amount < 0 || amount > allQuestions.size()) {
            throw new QuestionAmountMissMatchException(amount);
        }
        if (amount == allQuestions.size()) {
            return allQuestions;
        }
        Set<Question> resultSet = new HashSet<>();
        while (resultSet.size() < amount){
            resultSet.add(questionService.getRandomQuestion());
        }
        return resultSet;
    }
}
