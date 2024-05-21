public class Evaluated {
    int code;
    String name;
    String surname;

    public Evaluated(int code, String name, String surname){
        this.code = code;
        this.name = name;
        this.surname = surname;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Evaluated{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
