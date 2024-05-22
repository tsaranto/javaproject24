public class WordAnswer extends Answer{
    private String response;

    public WordAnswer(int evaluatedCode, int questionCode, String response) {
        super(evaluatedCode, questionCode);
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean isCorrect(Question question) {
        if (question instanceof WordAnswerQuestion) {
            WordAnswerQuestion wordQuestion = (WordAnswerQuestion) question;
            return wordQuestion.checkAnswer(this);
        }
        return false;
    }
}
