public abstract class Answer {
    int evaluated_code;
    int question_code;
    
    public Answer(int evaluated_code, int question_code){
        this.evaluated_code = evaluated_code;
        this.question_code = question_code;
    }
    public abstract boolean isCorrect(Question question);

}
