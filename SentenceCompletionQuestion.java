public class SentenceCompletionQuestion extends Question{
    list<String> answerlist;
    list<String> ordered;

    public SentenceCompletionQuestion(int code, String description, String answerlist, String ordered){
        super(code, description);
        this.answerlist = answerlist;
        this.ordered = ordered;
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

