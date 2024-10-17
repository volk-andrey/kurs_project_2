package sky.pro.kurs_project_2.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends RuntimeException {
    private final String question;
    private final String answer;
    public  QuestionNotFoundException(String question, String answer){
        super(String.format("Question '%s' with answer '%s' not found",question,answer));
        this.question =question;
        this.answer =answer;
    }

    public String getQuestion() {
        return question;
    }
    public String getAnswer() {
        return answer;
    }

}


