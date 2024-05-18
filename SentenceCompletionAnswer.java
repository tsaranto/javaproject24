import java.util.List;

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
}
