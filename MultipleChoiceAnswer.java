import java.util.List;
import java.util.stream.Collectors;

public class MultipleChoiceAnswer extends Answer{
    private List<String> chosenanswers;

    public MultipleChoiceAnswer(int evaluated_code, int question_code, List<String> chosenanswers){
        super(evaluated_code,question_code);
        this.chosenanswers = chosenanswers;
    }

    public boolean isCorrect(Question question) {
        if (question instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;
            return mcQuestion.checkAnswer(this);
        }
        return false;
    }

    @Override
    public String getResponse() {
        return chosenanswers.stream().collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return "evaluatee: " + evaluated_code + ", question: " + question_code
        + ", type: MultipleChoiceAnswer"+
                ", answer:'" + chosenanswers;
    }
}

