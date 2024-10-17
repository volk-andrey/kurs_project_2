package sky.pro.kurs_project_2.service;

import org.springframework.stereotype.Service;
import sky.pro.kurs_project_2.model.Question;

import java.util.Collection;

@Service
public interface ExaminerService {
    public Collection<Question> getQuestions(int amount);
}
