public class WordAnswer extends Answer{
    private String response;

    public WordAnswer(int evaluated_code, int question_code, String response) {
        super(evaluated_code, question_code);
        this.response = response;
    }
    @Override
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

    @Override
    public String toString() {
        return "evaluatee: " + evaluated_code + ", question: " + question_code
        + ", type: WordAnswerQuestion"+
                ", answer:" + response;
    }
}
