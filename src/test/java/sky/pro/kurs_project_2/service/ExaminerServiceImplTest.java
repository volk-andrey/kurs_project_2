package sky.pro.kurs_project_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sky.pro.kurs_project_2.model.Question;
import sky.pro.kurs_project_2.exeption.QuestionAmountMissMatchException;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    private static final List<Question> questions = new ArrayList<>(List.of(
            new Question("Что такое переменная в Java?", "Переменная в Java представляет собой именованную область памяти, которая хранит значение определенного типа."),
            new Question("Как объявляется массив в Java?", "Массивы в Java объявляются с помощью квадратных скобок после типа данных."),
            new Question("Как работает оператор 'this' в Java?", "Оператор 'this' используется для ссылки на текущий объект в контексте методов класса."),
            new Question("Что такое класс в Java?", "Класс в Java – это определение структуры данных и поведения, которое может быть использовано для создания объектов."),
            new Question("Что такое объект в Java?", "Объект в Java – это экземпляр класса, содержащий данные и методы."),
            new Question("Как реализуется полиморфизм в Java?", "Полиморфизм в Java реализуется через наследование классов и использование виртуальных методов."),
            new Question("Что такое интерфейс в Java?", "Интерфейс в Java – это контракт, определяющий методы, которые должны быть реализованы в классе."),
            new Question("Как работает ключевое слово 'final' в Java?", "Ключевое слово 'final' в Java используется для маркировки переменной или метода как неизменяемое.")
    ));
    private static final Collection<Question> questionsSet = new HashSet<>(questions);
    private static final Random random = new Random();
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        when(questionService.allQuestions()).thenReturn(questions);
    }

    @DisplayName("Положительный тест на получение рандомного списка вопросов")
    @Test
    public void getRandomQuestions() {
        //when
        int amount = 3;
        when(questionService.getRandomQuestion()).thenReturn(
                questions.get(0),
                questions.get(1),
                questions.get(2)
        );
        //then
        Collection<Question> actual = examinerService.getQuestions(amount);
        assertEquals(amount, actual.size());
        assertTrue(actual.contains(questions.get(0)));
        assertTrue(actual.contains(questions.get(1)));
        assertTrue(actual.contains(questions.get(2)));
        verify(questionService,times(1)).allQuestions();
        verify(questionService, times(amount)).getRandomQuestion();
    }
    @DisplayName("Положительный тест на получение рандомного списка из всех вопросов")
    @Test
    public void getRandomQuestionsAmountAllSize() {
        //when
        int amount = questions.size();
        //then
        Collection<Question> actual = examinerService.getQuestions(amount);
        assertEquals(amount, actual.size());
        assertEquals(questions,actual);
    }


    @DisplayName("Отрицательный тест на получение рандомного списка вопросов")
    @ParameterizedTest
    @MethodSource("getAmountNegative")
    public void getRandomQuestionsNegativeOverSize(int amount) {
        //when//then//when
        assertThrows(QuestionAmountMissMatchException.class, () -> examinerService.getQuestions(amount));
        verify(questionService,times(1)).allQuestions();
    }

    public static Stream<Arguments> getAmountNegative() {
        return Stream.of(
                Arguments.of(-1 - random.nextInt(100)),
                Arguments.of(questions.size() + random.nextInt(100)
        ));
    }
}
