import java.util.List;
import java.util.ArrayList;

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
    public boolean checkAnswer(MultipleChoiceAnswer answer) {
        List<String> givenAnswers = answer.getChosenAnswers();
        // Check if the size of givenAnswers and correctAnswer match
        if (givenAnswers.size() != correctanswer.size()) {
            return false;
        }

        // Check if all given answers are correct
        for (String answer : givenAnswers) {
            if (!correctanswer.contains(answer)) {
                return false;
            }
        }

        return true;
    }

    public void display() {
        System.out.println("Question: " + super.description);
        for (int i = 0; i < answerlist.size(); i++) {
            System.out.println((i + 1) + ": " + answerlist.get(i));
        }
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String toString() {
        return "MultipleChoiceQuestion{code=" + getCode() + ", description='" + getDescription() + "', answerList=" + answerList + ", correctAnswer=" + correctAnswer + '}';
    }
}
