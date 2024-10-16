package sky.pro.kurs_project_2.exeption;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
public class QuestionIsAlreadyExeption extends RuntimeException{
    public final String question;
    public final String answer;
    public QuestionIsAlreadyExeption (String question, String answer) {
        super("Question %s is already in use. Answer %s");
        this.question = question;
        this.answer = answer;
    }
}
