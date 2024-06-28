public class WordAnswerQuestion extends Question{
    String correctanswer;

    public WordAnswerQuestion(int id, String description, String correctanswer) {
        super(id, description);
        this.correctanswer = correctanswer;
    }

    @Override
    public boolean checkAnswer(Answer answer) {
        String answer1 = answer.getResponse();
        return correctanswer.equalsIgnoreCase(answer1.trim());
    }

    public String getCorrectAnswer(){
        return correctanswer;
    }

    @Override
    public String toString() {
        return "(" + code + ") type: WordAnswerQuestion"+
                ", description:'" + description + '\'' +
                ", correctAnswer:'" + correctanswer + '\'';
    }
}
