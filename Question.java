public abstract class Question {
    int code;
    String description;

    public Question(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract boolean checkAnswer(String answer);

    @Override
    public String toString() {
        return "Question{" +
               "code=" + code +
               ", description='" + description + '\'' +
               '}';
    }
}
