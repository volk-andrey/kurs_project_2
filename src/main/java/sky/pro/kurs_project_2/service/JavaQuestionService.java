package sky.pro.kurs_project_2.service;

import org.springframework.stereotype.Service;
import sky.pro.kurs_project_2.model.Question;
import sky.pro.kurs_project_2.exeption.QuestionIsAlreadyExeption;
import sky.pro.kurs_project_2.exeption.QuestionNotFoundException;

import java.util.*;

import static java.util.Collections.unmodifiableSet;
@Service
public class JavaQuestionService implements QuestionService{
    private final Set<Question> questions = new HashSet<>(Set.of(
            new Question("Что такое переменная в Java?", "Переменная в Java представляет собой именованную область памяти, которая хранит значение определенного типа."),
            new Question("Как объявляется массив в Java?", "Массивы в Java объявляются с помощью квадратных скобок после типа данных."),
            new Question("Как работает оператор 'this' в Java?", "Оператор 'this' используется для ссылки на текущий объект в контексте методов класса."),
            new Question("Что такое класс в Java?", "Класс в Java – это определение структуры данных и поведения, которое может быть использовано для создания объектов."),
            new Question("Что такое объект в Java?", "Объект в Java – это экземпляр класса, содержащий данные и методы."),
            new Question("Как реализуется полиморфизм в Java?", "Полиморфизм в Java реализуется через наследование классов и использование виртуальных методов."),
            new Question("Что такое интерфейс в Java?", "Интерфейс в Java – это контракт, определяющий методы, которые должны быть реализованы в классе."),
            new Question("Как работает ключевое слово 'final' в Java?", "Ключевое слово 'final' в Java используется для маркировки переменной или метода как неизменяемое.")
    ));
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
        for (Question findQuestion : questions) {
            if (findQuestion.getQuestion().equals(question)) {
                return findQuestion;
            }
        }
        throw new QuestionNotFoundException(question,null);
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
