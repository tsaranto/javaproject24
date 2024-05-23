import java.util.List;
import java.util.stream.Collectors;

public class SentenceCompletionAnswer extends Answer {
    List<String> wordOrder;

    public SentenceCompletionAnswer(int evaluated_code, int question_code, List<String>wordOrder){
        super(evaluated_code,question_code);
        this.wordOrder=wordOrder;
    }

    @Override
    public boolean isCorrect(Question question) {
        if (question instanceof SentenceCompletionQuestion) {
            SentenceCompletionQuestion scQuestion = (SentenceCompletionQuestion) question;
            return scQuestion.getOrdered().equals(wordOrder);
        }
        return false;
    }

    @Override
    public String getResponse() {
        return wordOrder.stream().collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return "evaluatee: " + evaluated_code + ", question: " + question_code
        + ", type: SentenceCompletionQuestion"+
                ", answer:'" + wordOrder;
    }
}
