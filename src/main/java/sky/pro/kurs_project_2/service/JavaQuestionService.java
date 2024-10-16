package sky.pro.kurs_project_2.service;

import org.springframework.stereotype.Service;
import sky.pro.kurs_project_2.dto.Question;
import sky.pro.kurs_project_2.exeption.QuestionIsAlreadyExeption;
import sky.pro.kurs_project_2.exeption.QuestionNotFoundException;

import java.util.*;

import static java.util.Collections.unmodifiableSet;
@Service
public class JavaQuestionService implements QuestionService{
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question addQuestion(String question, String answer) {
        Question resultQuestion = new Question(question, answer);
        if (questions.contains(resultQuestion)) {
            throw new QuestionIsAlreadyExeption(question,answer);
        }
        questions.add(resultQuestion);
        return resultQuestion;
    }

    @Override
    public Question addQuestion(Question question) {
        if (questions.contains(question)) {
            throw new QuestionIsAlreadyExeption(question.getQuestion(),question.getAnswer());
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question removeQuestion(String question, String answer) {
        Question resultQuestion = new Question(question, answer);
        if (!questions.contains(resultQuestion)) {
            throw new QuestionNotFoundException(question,answer);
        }
        questions.remove(resultQuestion);
        return resultQuestion;
    }

    @Override
    public Question removeQuestion(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException(question.getQuestion(),question.getAnswer());
        };
        questions.remove(question);
        return question;
    }

    @Override
    public Question findQuestion(String question) {
        return null;
    }

    @Override
    public Collection<Question> allQuestions() {
        return unmodifiableSet(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return questions.toArray(new Question[0])[random.nextInt(questions.size())];
    }
}
