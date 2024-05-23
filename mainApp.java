
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class mainApp {
    private static List<Evaluated> evaluatedList = new ArrayList<>();
    private static List<Question> questions = new ArrayList<>();
    private static List<Answer> answers = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Insert evaluatee");
            System.out.println("2. Insert question");
            System.out.println("3. Insert answer");
            System.out.println("4. Show questions");
            System.out.println("5. Show evaluatee Answers");
            System.out.println("6. Show number of correct answers per evaluatee");
            System.out.println("7. Question accuracy percentage");
            System.out.println("8. Evaluatee accuracy percentage");
            System.out.println("9. Exit");
            System.out.print("Select: ");
            choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    addEvaluated(scanner);
                    break;
                case 2:
                    addQuestion(scanner);

                    break;
                case 3:
                    addAnswer(scanner);
                    break;
                case 4:
                    displayQuestions();
                    break;
                case 5:
                    displayEvaluatorAnswers();
                    break;
                case 6:
                    //displayCorrectAnswersCount
                    break;
                case 7:
                    //calculateQuestionAccuracy
                    break;
                case 8:
                    //calculateEvaluatorAccuracy
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 9);
    }
    
    private static void addEvaluated(Scanner scanner){
        System.out.println("Set code:");
        int evaluatedcode = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Set name:");
        String name = scanner.nextLine();
        System.out.println("Set surname:");
        String surname = scanner.nextLine();
        Evaluated evaluated = new Evaluated(evaluatedcode, name, surname);
        System.out.println(evaluated.toString());
        evaluatedList.add(evaluated);

    }



    private static void addQuestion(Scanner scanner){
        System.out.println("Select type of question:");
        System.out.println("1. Multiple choice question");
        System.out.println("2. Single word answer question");
        System.out.println("3. Sentence completion question");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Set code:");
        int questioncode = scanner.nextInt();
        scanner.nextLine();
        if(choice==3){
            System.out.println("Mark missing words with '?'");
        }
        System.out.println("Set description:");
        String description = scanner.nextLine();
        switch(choice){
            case 1:
                System.out.println("Enter total choices (comma-separated):");
                List<String> totalChoices = Arrays.asList(scanner.nextLine().split(","));
                System.out.println("Enter the correct choices (comma-separated):");
                List<String> correctChoices = Arrays.asList(scanner.nextLine().split(","));
                questions.add(new MultipleChoiceQuestion(totalChoices, correctChoices, questioncode, description));
                break; 
            case 2:
                System.out.println("Enter correct word:");
                String correctWord = scanner.nextLine();
                questions.add(new WordAnswerQuestion(questioncode, description, correctWord));
                break;
            case 3:
                System.out.println("Enter provided words (comma-separated):");
                List<String> providedWords = Arrays.asList(scanner.nextLine().split(","));
                System.out.println("Enter the correct order of words (comma-separated):");
                List<String> correctOrder = Arrays.asList(scanner.nextLine().split(","));
                questions.add(new SentenceCompletionQuestion(questioncode, description, providedWords, correctOrder));
                break;
            default: 
                System.out.println("Invalid choice!");
        }
    }


    private static void addAnswer(Scanner scanner){
        System.out.println("Select type of answer:");
        System.out.println("1. Multiple choice answer");
        System.out.println("2. Single word answer");
        System.out.println("3. Sentence completion answer");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Insert evaluatee code:");
        int evaluateecode = scanner.nextInt();
        System.out.println("Insert question code:");
        int questioncode = scanner.nextInt();
        scanner.nextLine();
        switch(choice){
            case 1:
                System.out.println("Insert chosen answers seperated by commas(,):");
                List<String> chosenanswers = Arrays.asList(scanner.nextLine().split(","));
                answers.add(new MultipleChoiceAnswer(evaluateecode, questioncode, chosenanswers));
                break;
            case 2:
                System.out.println("Insert chosen word:");
                String word = scanner.nextLine();
                answers.add(new WordAnswer(evaluateecode, questioncode, word));
                break;
            case 3:
                System.out.println("Insert ordered words(comma-separated):");
                List<String> orderedanswers = Arrays.asList(scanner.nextLine().split(","));
                answers.add(new SentenceCompletionAnswer(evaluateecode, questioncode, orderedanswers));
                break;
            default:
                System.out.println("Invalid choice!");

        }
    }

    private static void displayQuestions(){
        for (Question question: questions){
            System.out.println(question);
        }
    }

    private static void displayEvaluatorAnswers(){
        for (Answer answer: answers){
            System.out.println(answer);
        }
    }


}    