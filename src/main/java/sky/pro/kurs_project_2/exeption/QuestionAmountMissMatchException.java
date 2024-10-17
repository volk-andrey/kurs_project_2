package sky.pro.kurs_project_2.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuestionAmountMissMatchException extends RuntimeException{
    public QuestionAmountMissMatchException(int amount) {
        super(String.format("Incorrect amount of question", amount));
    }
}
