
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainApp {
    private static List<Evaluated> evaluatedList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice, choice2, questioncode;
        String description;
        int elena;

        do {
            System.out.println("1. Insert evaluatee");
            System.out.println("2. Insert question");
            System.out.println("3. Insert answer");
            System.out.println("4. Show questions");
            System.out.println("5. Show evaluatee Answers");
            System.out.println("6. Show number of correct answers");
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
                    addQuestion();

                    break;
                case 3:
                    //addAnswer
                    break;
                case 4:
                    //displayQuestions
                    break;
                case 5:
                    //displayEvaluatorAnswers
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
        System.out.println("Set description:");
        String description = scanner.nextLine();
        switch(choice){
            case 1:
            

        }
