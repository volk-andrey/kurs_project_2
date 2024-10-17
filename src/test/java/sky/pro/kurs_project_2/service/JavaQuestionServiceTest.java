package sky.pro.kurs_project_2.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sky.pro.kurs_project_2.model.Question;
import sky.pro.kurs_project_2.exeption.QuestionIsAlreadyExeption;
import sky.pro.kurs_project_2.exeption.QuestionNotFoundException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService = new JavaQuestionService();

    @DisplayName("Положительный тест на добавление вопроса")
    @Test
    public void addQuestionPositiveTest() {
        //given
        int sizeQuestion = javaQuestionService.allQuestions().size();
        int expected = ++sizeQuestion;
        Question question = new Question("Вопрос", "Ответ");
        //when
        javaQuestionService.addQuestion(question);
        //then
        int actual = javaQuestionService.allQuestions().size();
        assertEquals(expected, actual);
        boolean isAdded = javaQuestionService.allQuestions()
                .stream()
                .anyMatch(question::equals);
        assertTrue(isAdded);
    }
    @DisplayName("Отрицательный тест на добавление вопроса")
    @Test
    public void addQuestionNegativeTest() {
        //given
        Question question = new Question("Вопрос", "Ответ");
        javaQuestionService.addQuestion(question);
        int sizeQuestion = javaQuestionService.allQuestions().size();
        //whet//then
        assertThrows(QuestionIsAlreadyExeption.class,()->javaQuestionService.addQuestion(question));
        int actual = javaQuestionService.allQuestions().size();
        assertEquals(sizeQuestion, actual);
    }
    @DisplayName("Положительный тест на удаление вопроса")
    @Test
    public void deleteQuestionPositiveTest() {
        //given
        Question question = new Question("Вопрос", "Ответ");
        javaQuestionService.addQuestion(question);
        int sizeQuestion = javaQuestionService.allQuestions().size();
        int expected = --sizeQuestion;
        //when
        javaQuestionService.removeQuestion(question);
        //then
        int actual = javaQuestionService.allQuestions().size();
        assertEquals(expected, actual);
        boolean isDeleted = javaQuestionService.allQuestions()
                .stream()
                .noneMatch(question::equals);
        assertTrue(isDeleted);
    }
    @DisplayName("Отрицательный тест на удаление вопроса")
    @Test
    public void deleteQuestionNegativeTest() {
        //given
        int expected = javaQuestionService.allQuestions().size();
        Question question = new Question("Вопрос", "Ответ");
        //when//then
        assertThrows(QuestionNotFoundException.class,()->javaQuestionService.removeQuestion(question));
        int actual = javaQuestionService.allQuestions().size();
        assertEquals(expected, actual);
    }
    @DisplayName("Положительный тест на поиск вопроса")
    @Test
    public void findQuestionPositiveTest() {
        //given
        String question = "Вопрос";
        String answer = "Ответ";
        Question expected = new Question(question, answer);
        javaQuestionService.addQuestion(expected);
        //when
        Question actual = javaQuestionService.findQuestion(question);
        //then
        assertEquals(expected, actual);
    }

    @DisplayName("Отрицательный тест на поиск вопроса")
    @Test
    public void findQuestionNegativeTest() {
        //given
        String question = "Вопрос";
        String answer = "Ответ";
        Question expected = new Question(question, answer);
        //when//then
        assertThrows(QuestionNotFoundException.class,()->javaQuestionService.findQuestion(question));
    }
    @DisplayName("Положительный тест на получение всех вопросов")
    @Test
    public void getAllQuestionsPositiveTest() {
        //given
        int expected = javaQuestionService.allQuestions().size();
        Collection<Question> expectedQuestions = javaQuestionService.allQuestions();
        //when
        int actual = javaQuestionService.allQuestions().size();
        Collection<Question> actualQuestions = javaQuestionService.allQuestions();
        //then
        assertEquals(expected, actual);
        assertEquals(expectedQuestions, actualQuestions);
    }
}
