import java.util.List;

public class SentenceCompletionQuestion extends Question{
    List<String> answerlist;
    List<String> ordered;

    public SentenceCompletionQuestion(int code, String description, List<String> answerlist, List<String> ordered) {
        super(code, description);
        this.answerlist = answerlist;
        this.ordered = ordered;
    }

    @Override
    public boolean checkAnswer(Answer answer) {
        if (answer instanceof SentenceCompletionAnswer) {
            SentenceCompletionAnswer sca = (SentenceCompletionAnswer) answer;
            return ordered.equals(sca.wordOrder);
        }
        return false;
    }

    public List<String> getOrdered() {
        return ordered;
    }

    public List<String> getAnswerList() {
        return answerlist;
    }

    @Override
    public String toString() {
        return "SentenceCompletionQuestion{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", answerList=" + answerlist +
                ", ordered=" + ordered +
                '}';
    }
}

