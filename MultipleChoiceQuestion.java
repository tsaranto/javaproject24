import java.util.List;
import java.util.Arrays;

public class MultipleChoiceQuestion extends Question{
    List<String> answerlist;
    List<String> correctanswer;

    public MultipleChoiceQuestion(List<String> answerlist,List<String> correctanswer , int code, String description) {

        super(code , description);
        this.answerlist = answerlist;
        this.correctanswer = correctanswer;

    }
    @Override
    public boolean checkAnswer(Answer answer) {

        if (answer instanceof MultipleChoiceAnswer) {
            MultipleChoiceAnswer mcAnswer = (MultipleChoiceAnswer) answer;
            List<String> givenAnswers = Arrays.asList(mcAnswer.getResponse().split(","));
        

            if (givenAnswers.size() != correctanswer.size()) {
                return false;
            }

            for (String givenAnswer : givenAnswers) {
                if (!correctanswer.contains(givenAnswer)) {
                    return false;
                }
            }

            return true;
        }

        return true;

    }

    public List<String> getAnswerlist() {
        return answerlist;
    }

    public List<String> getCorrectanswer() {
        return correctanswer;
    }

    @Override
    public String toString() {
        return "(" + code + ") type: MultipleChoiceQuestion" +
                ", description: '" + description + '\'' +
                ", choices: " + answerlist +
                ", correctChoices: " + correctanswer;
    }

}
