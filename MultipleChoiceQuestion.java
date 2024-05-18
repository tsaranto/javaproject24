public class MultipleChoiceQuestion extends Question{
    String answerlist;
    String correctanswer;
}
public MultipleChoiceQuestion(String answerlist,String correctanswer , int code, String description) {

    super(code , description);
    this.answerlist = answerlist;
    this.correctanswer = correctanswer;

}

public int checkAnswer(){


}

