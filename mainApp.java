import java.util.HashMap;
import java.util.Map;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class mainApp {
    private static List<Evaluated> evaluatedList = new ArrayList<>();
    private static List<Question> questions = new ArrayList<>();
    private static List<Answer> answers = new ArrayList<>();
    public static void main(String[] args) {
        initializeData();
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
                    displayEvaluatorAnswers(scanner);
                    break;
                case 6:
                    displayCorrectAnswersCount();
                    break;
                case 7:
                    calculateQuestionAccuracy();
                    break;
                case 8:
                    calculateEvaluatorAccuracy();
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

        Evaluated evaluatee = null;
        for (Evaluated e : evaluatedList) {
            if (e.getCode() == evaluateecode) {
                evaluatee = e;
                break;
            }
        }
    
        Question question = null;
        for (Question q : questions) {
            if (q.getCode() == questioncode) {
                question = q;
                break;
            }
        }

        if(evaluatee != null && question != null) {
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
    }

    private static void displayQuestions(){
        for (Question question: questions){
            System.out.println(question);
        }
    }

    private static void displayEvaluatorAnswers(Scanner scanner){
        System.out.println("List of evaluatees:");
        for (Evaluated evaluated : evaluatedList) {
            System.out.println(evaluated.getCode() + ". " + evaluated.getName() + " " + evaluated.getSurname());
        }
        
        System.out.print("Select an evaluatee: ");
        int selectedEvaluatedCode = scanner.nextInt();
        scanner.nextLine(); 
        
        Evaluated selectedEvaluated = null;
        for (Evaluated evaluated : evaluatedList) {
            if (evaluated.getCode() == selectedEvaluatedCode) {
                selectedEvaluated = evaluated;
                break;
            }
        }
        
        if (selectedEvaluated != null) {
            System.out.println("Answers of " + selectedEvaluated.getName() + " " + selectedEvaluated.getSurname() + ":");
            for (Answer answer : answers) {
                if (answer.getEvaluatedCode() == selectedEvaluatedCode) {
                    System.out.println(answer);
                }
            }
        } else {
            System.out.println("Invalid evaluateecode!");
        }
    }

    private static void displayCorrectAnswersCount(){
        Map<Integer, Integer> correctAnswersCount = new HashMap<>();
        //ftiaxno map pou apothikeuei os key to evaluated code kai value to plithos soston apantiseon
        for (Answer answer : answers){
            Question question = null;

            for (Question q : questions) {
                if (q.getCode() == answer.getQuestionCode()) {
                    question = q;
                    break;
                }
            }

            if (question != null && answer.isCorrect(question)) {
                int evaluatedCode = answer.getEvaluatedCode();
                correctAnswersCount.put(evaluatedCode, correctAnswersCount.getOrDefault(evaluatedCode, 0) + 1);
            }
        }

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(correctAnswersCount.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>() {
        @Override
        public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
            // Sort in descending order
            return entry2.getValue().compareTo(entry1.getValue());
        }
        });

        for (Map.Entry<Integer, Integer> entry : entries) {
            int evaluatedCode = entry.getKey();
            int correctCount = entry.getValue();
            Evaluated evaluated = null;
            for (Evaluated e : evaluatedList) {
                if (e.getCode() == evaluatedCode) {
                    evaluated = e;
                    break;
                }
            }
            if (evaluated != null) {
                System.out.println("Evaluatee " + evaluated.getName() + " " + evaluated.getSurname() + " has " + correctCount + " correct answers.");
            }
        }


    }


    private static void initializeData() {
        evaluatedList.add(new Evaluated(1, "John", "Doe"));
        evaluatedList.add(new Evaluated(2, "Jane", "Smith"));
        evaluatedList.add(new Evaluated(3, "Alice", "Johnson"));
        evaluatedList.add(new Evaluated(4, "Bob", "Brown"));

        questions.add(new MultipleChoiceQuestion(Arrays.asList("Paris", "London", "Rome", "Berlin"), Arrays.asList("Paris"),1,"What is the capital of France?"));
        questions.add(new MultipleChoiceQuestion(Arrays.asList("Python", "Java", "HTML", "CSS"),Arrays.asList("Python", "Java"),2,"Which are programming languages?"));
        questions.add(new MultipleChoiceQuestion(Arrays.asList("Apple", "Carrot", "Banana", "Potato"),Arrays.asList("Apple", "Banana"),3,"Which are fruits?"));
        questions.add(new MultipleChoiceQuestion(Arrays.asList("A", "B", "C"), Arrays.asList("A", "C"), 114, "What is 2 + 2?"));

        questions.add(new WordAnswerQuestion(4, "What is the largest planet?", "Jupiter"));
        questions.add(new WordAnswerQuestion(5, "What is the smallest prime number?", "2"));
        questions.add(new WordAnswerQuestion(6, "What is the chemical symbol for water?", "H2O"));
        questions.add(new WordAnswerQuestion(10, "What is the capital of France?", "Paris"));
        
        questions.add(new SentenceCompletionQuestion(7, "The quick brown ___ jumps over the lazy ___.",
                Arrays.asList("fox", "dog"), Arrays.asList("fox", "dog")));
        questions.add(new SentenceCompletionQuestion(8, "To be or not to be, that is the ___",
                Arrays.asList("question", "answer"), Arrays.asList("question")));
        questions.add(new SentenceCompletionQuestion(9, "I have a ___, it is a yellow fruit",
                Arrays.asList("banana", "apple"), Arrays.asList("banana")));
        //apla gia test
        answers.add(new MultipleChoiceAnswer(1, 1, Arrays.asList("Paris")));
        answers.add(new MultipleChoiceAnswer(2, 2, Arrays.asList("Python", "Java")));
        answers.add(new MultipleChoiceAnswer(3, 3, Arrays.asList("Apple", "Banana")));

        answers.add(new WordAnswer(4, 4, "Jupiter"));
        answers.add(new WordAnswer(1, 5, "2"));
        answers.add(new WordAnswer(2, 6, "H2O"));

        answers.add(new MultipleChoiceAnswer(1, 1, Arrays.asList("A", "C"))); 
        answers.add(new MultipleChoiceAnswer(1, 1, Arrays.asList("A"))); 
        answers.add(new MultipleChoiceAnswer(2, 1, Arrays.asList("A", "B"))); 
        answers.add(new WordAnswer(1, 2, "Paris")); 
        answers.add(new WordAnswer(2, 2, "Lyon")); 

        answers.add(new SentenceCompletionAnswer(3, 7, Arrays.asList("fox", "dog")));
        answers.add(new SentenceCompletionAnswer(4, 8, Arrays.asList("question")));
        answers.add(new SentenceCompletionAnswer(4, 9, Arrays.asList("banana")));

    }

    private static void calculateQuestionAccuracy() {
        Map<Integer, Integer> correctAnswersCount = new HashMap<>();
        Map<Integer, Integer> totalAnswersCount = new HashMap<>();

        for (Answer answer : answers) {
            int questionCode = answer.getQuestionCode();

            Question question = null;
            for (Question q : questions) {
                if (q.getCode() == questionCode) {
                    question = q;
                    break;
                }
            }

            if (question != null) {
                totalAnswersCount.put(questionCode, totalAnswersCount.getOrDefault(questionCode, 0) + 1);
                if (answer.isCorrect(question)) {
                    correctAnswersCount.put(questionCode, correctAnswersCount.getOrDefault(questionCode, 0) + 1);
                }
            }
        }

        List<Map.Entry<Integer, Double>> accuracies = new ArrayList<>();
        for (Integer questionCode : totalAnswersCount.keySet()) {
            int correctCount = correctAnswersCount.getOrDefault(questionCode, 0);
            int totalCount = totalAnswersCount.get(questionCode);
            double accuracy = (double) correctCount / totalCount * 100;
            accuracies.add(new AbstractMap.SimpleEntry<>(questionCode, accuracy));
        }

        accuracies.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

        for (Map.Entry<Integer, Double> entry : accuracies) {
            System.out.println("Question Code: " + entry.getKey() + ", Accuracy: " + entry.getValue() + "%");
        }
    }
    

    private static void calculateEvaluatorAccuracy() {
        Map<Integer, Integer> correctAnswersCount = new HashMap<>();
        Map<Integer, Integer> totalAnswersCount = new HashMap<>();

        for (Answer answer : answers) {
            int evaluateeCode = answer.getEvaluatedCode();
            int questionCode = answer.getQuestionCode();

            Question question = null;
            for (Question q : questions) {
                if (q.getCode() == questionCode) {
                    question = q;
                    break;
                }
            }

            if (question != null) {
                totalAnswersCount.put(evaluateeCode, totalAnswersCount.getOrDefault(evaluateeCode, 0) + 1);
                if (answer.isCorrect(question)) {
                    correctAnswersCount.put(evaluateeCode, correctAnswersCount.getOrDefault(evaluateeCode, 0) + 1);
                }
            }
        }

        List<Map.Entry<Integer, Double>> accuracies = new ArrayList<>();
        for (Integer evaluateeCode : totalAnswersCount.keySet()) {
            int correctCount = correctAnswersCount.getOrDefault(evaluateeCode, 0);
            int totalCount = totalAnswersCount.get(evaluateeCode);
            double accuracy = (double) correctCount / totalCount * 100;
            accuracies.add(new AbstractMap.SimpleEntry<>(evaluateeCode, accuracy));
        }

        accuracies.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

        for (Map.Entry<Integer, Double> entry : accuracies) {
            Evaluated evaluated = null;
            for (Evaluated e : evaluatedList) {
                if (e.getCode() == entry.getKey()) {
                    evaluated = e;
                    break;
                }
            }
            if (evaluated != null) {
                System.out.println("Evaluatee " + evaluated.getName() + " " + evaluated.getSurname() + " has " + entry.getValue() + "% correct answers.");
            }
        }
    }

}    