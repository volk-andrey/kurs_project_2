package sky.pro.kurs_project_2.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sky.pro.kurs_project_2.dto.Question;
import sky.pro.kurs_project_2.exeption.QuestionIsAlreadyExeption;

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
}
