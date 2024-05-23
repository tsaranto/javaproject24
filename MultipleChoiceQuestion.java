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
    //APO DO KAI PERA EINAI CHAT TZIPITOULIS, TO GIVENANSERS EINAI THEORITIKA APO TIN KLASI ANSER
    @Override
    public boolean checkAnswer(Answer answer) {
        // Check if the size of givenAnswers and correctAnswer match

        if (answer instanceof MultipleChoiceAnswer) {
            MultipleChoiceAnswer mcAnswer = (MultipleChoiceAnswer) answer;
            List<String> givenAnswers = Arrays.asList(mcAnswer.getResponse().split(","));
        

            if (givenAnswers.size() != correctanswer.size()) {
                return false;
            }

            // Check if all given answers are correct
            for (String givenAnswer : givenAnswers) {
                if (!correctanswer.contains(givenAnswer)) {
                    return false;
                }
            }

            return true;
        }

        return true;

    }

    public void display() {
        System.out.println("Question: " + super.description);
        for (int i = 0; i < answerlist.size(); i++) {
            System.out.println((i + 1) + ": " + answerlist.get(i));
        }
    }
}
