    /* OMADA XRHSTWN 114
    p3220006 , p3220304 , p3220046
    */

    import java.util.HashMap;
    import java.util.Map;
    import java.util.AbstractMap;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.List;
    import java.util.Scanner;
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;

    public class mainApp {
        private static List<Evaluated> evaluatedList = new ArrayList<>();
        private static List<Question> questions = new ArrayList<>();
        private static List<Answer> answers = new ArrayList<>();

    


        public static void main(String[] args) {
            String ratedPersonsPath = "data/rated_persons.txt";
            String questionsPath = "data/questions.txt";
            String answersPath = "data/answers.txt";


            readQuestionsFromFile(questionsPath);
            readRatedPersonsFromFile(ratedPersonsPath);
            readAnswersFromFile(answersPath);

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("0. Save to file");
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
                        writeRatedPersonsToFile(ratedPersonsPath);
                        writeQuestionsToFile(questionsPath);
                        writeAnswersToFile(answersPath);
                        break;
                    case 0:
                        writeRatedPersonsToFile(ratedPersonsPath);
                        writeQuestionsToFile(questionsPath);
                        writeAnswersToFile(answersPath);
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
            evaluatedList.add(new Evaluated(1, "Sakis", "Rouvas"));
            evaluatedList.add(new Evaluated(2, "Markellos", "Kokkonias"));
            evaluatedList.add(new Evaluated(3, "Stolen", "Mic"));
            evaluatedList.add(new Evaluated(4, "Stavros", "Toumpis"));

            questions.add(new MultipleChoiceQuestion(Arrays.asList("Paris", "London", "Rome", "Berlin"), Arrays.asList("Paris"),1,"What is the capital of Zimbambwe?"));
            questions.add(new MultipleChoiceQuestion(Arrays.asList("Argyros", "Kapodistrias", "Quentin Tarantino", "Ntalaras"),Arrays.asList("Ntalaras"),2,"Who was the arch nemesis of Jimmy Panousis?"));
            questions.add(new MultipleChoiceQuestion(Arrays.asList("Mythos", "Stolichnaya", "Bergina", "Potato"),Arrays.asList("Mythos", "Bergina"),3,"Which are beers?"));

            questions.add(new WordAnswerQuestion(4, "What is the best university in Greece?", "Aueb"));
            questions.add(new WordAnswerQuestion(5, "Who created comment song 5?", "2j"));
            questions.add(new WordAnswerQuestion(6, "What do computer science students drink instead of water?", "coffee"));
            
            questions.add(new SentenceCompletionQuestion(7, "Kathe prama ston ___ tou kai o kolios ton ___.",
                    Arrays.asList("kairo", "avgousto"), Arrays.asList("kairo", "avgousto")));
            questions.add(new SentenceCompletionQuestion(8, "Ma ego eimai ___, den tha ___",
                    Arrays.asList("ellinas", "pethano"), Arrays.asList("ellinas", "pethano")));
            questions.add(new SentenceCompletionQuestion(9, "I have a ___, i have an ____, APPLE-PEN!!!",
                    Arrays.asList("pen", "apple"), Arrays.asList("pen","apple")));

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


            public static void writeRatedPersonsToFile(String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("RATEDPERSON_LIST\n{\n");
                for (Evaluated evaluated : evaluatedList) {
                    writer.write(" RATEDPERSON\n {\n");
                    writer.write(" CODE " + evaluated.getCode() + "\n");
                    writer.write(" SURNAME " + evaluated.getSurname() + "\n");
                    writer.write(" FIRSTNAME " + evaluated.getName() + "\n");
                    writer.write(" }\n");
                }
                writer.write("}\n");
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        }

        public static void readRatedPersonsFromFile(String filePath) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean readingData = false;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("RATEDPERSON_LIST")) {
                        readingData = true;
                    } else if (readingData && line.contains("RATEDPERSON")) {
                        int code = -1;
                        String surname = null;
                        String firstname = null;
                        while ((line = reader.readLine()) != null && !line.contains("}")) {
                            if (line.contains("CODE")) {
                                code = Integer.parseInt(line.trim().split("\\s+")[1]);
                            } else if (line.contains("SURNAME")) {
                                surname = line.trim().split("\\s+")[1];
                            } else if (line.contains("FIRSTNAME")) {
                                firstname = line.trim().split("\\s+")[1];
                            }
                        }
                        if (code != -1 && surname != null && firstname != null) {
                            evaluatedList.add(new Evaluated(code, surname, firstname));
                        } else {
                            System.err.println("Invalid format for RATEDPERSON entry.");
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading from the file.");
                e.printStackTrace();
            }
        }
        
        public static void writeQuestionsToFile(String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("QUESTION_LIST\n{\n");
                for (Question question : questions) {
                    writer.write(" QUESTION\n {\n");
                    writer.write(" CODE " + question.getCode() + "\n");
                    writer.write(" DESCRIPTION " + question.getDescription() + "\n");
                    writer.write(" TYPE " + question.getClass().getSimpleName() + "\n");
                    if (question instanceof MultipleChoiceQuestion) {
                        MultipleChoiceQuestion mcq = (MultipleChoiceQuestion) question;
                        writer.write(" CHOICES " + String.join(",", mcq.getAnswerlist()) + "\n");
                        writer.write(" CORRECT_CHOICES " + String.join(",", mcq.getCorrectanswer()) + "\n");
                    } else if (question instanceof WordAnswerQuestion) {
                        WordAnswerQuestion waq = (WordAnswerQuestion) question;
                        writer.write(" CORRECT_ANSWER " + waq.getCorrectAnswer() + "\n");
                    } else if (question instanceof SentenceCompletionQuestion) {
                        SentenceCompletionQuestion scq = (SentenceCompletionQuestion) question;
                        writer.write(" PROVIDED_WORDS " + String.join(",", scq.getAnswerList()) + "\n");
                        writer.write(" CORRECT_ORDER " + String.join(",", scq.getOrdered()) + "\n");
                    }
                    writer.write(" }\n");
                }
                writer.write("}\n");
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        }
        
        public static void readQuestionsFromFile(String filePath) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("QUESTION_LIST")) {
                        reader.readLine(); 
                        while (!(line = reader.readLine()).equals("}")) {
                            if (line.equals(" QUESTION")) {
                                reader.readLine(); 
                                int code = -1;
                                String description = null;
                                String type = null;
        
                                while ((line = reader.readLine()) != null && !line.equals(" }")) {
                                    if (line.contains("CODE")) {
                                        code = Integer.parseInt(line.trim().split("\\s+")[1]);
                                    } else if (line.contains("DESCRIPTION")) {
                                        description = line.trim().split("\\s+", 2)[1].trim();
                                    } else if (line.contains("TYPE")) {
                                        type = line.trim().split("\\s+")[1];
                                    } else if (line.contains("CHOICES")) {
                                        String choices =  line.trim().split("\\s+", 2)[1].trim();
                                        String correctChoices = reader.readLine().trim().split("\\s+")[1];
                                        List<String> totalChoices = Arrays.asList(choices.split(","));
                                        List<String> correctAnswers = Arrays.asList(correctChoices.split(","));
                                        questions.add(new MultipleChoiceQuestion(totalChoices, correctAnswers, code, description));
                                    } else if (line.contains("CORRECT_ANSWER")) {
                                        String correctAnswer = line.trim().split("\\s+")[1];
                                        questions.add(new WordAnswerQuestion(code, description, correctAnswer));
                                    } else if (line.contains("PROVIDED_WORDS")) {
                                        String providedWords = line.trim().split("\\s+")[1];
                                        String correctOrder = reader.readLine().trim().split("\\s+")[1];
                                        List<String> provided = Arrays.asList(providedWords.split(","));
                                        List<String> order = Arrays.asList(correctOrder.split(","));
                                        questions.add(new SentenceCompletionQuestion(code, description, provided, order));
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading from the file.");
                e.printStackTrace();
            }
        }
        

        public static void writeAnswersToFile(String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("ANSWER_LIST\n{\n");
                for (Answer answer : answers) {
                    writer.write(" ANSWER\n {\n");
                    writer.write(" EVALUATEE_CODE " + answer.getEvaluatedCode() + "\n");
                    writer.write(" QUESTION_CODE " + answer.getQuestionCode() + "\n");
                    writer.write(" TYPE " + answer.getClass().getSimpleName() + "\n");
                    if (answer instanceof MultipleChoiceAnswer) {
                        MultipleChoiceAnswer mca = (MultipleChoiceAnswer) answer;
                        writer.write(" ANSWERS " + String.join(",", mca.getResponse()) + "\n");
                    } else if (answer instanceof WordAnswer) {
                        WordAnswer wa = (WordAnswer) answer;
                        writer.write(" ANSWER " + wa.getResponse() + "\n");
                    } else if (answer instanceof SentenceCompletionAnswer) {
                        SentenceCompletionAnswer sca = (SentenceCompletionAnswer) answer;
                        writer.write(" ORDERED_ANSWERS " + String.join(",", sca.getResponse()) + "\n");
                    }
                    writer.write(" }\n");
                }
                writer.write("}\n");
            } catch (IOException e) {
                System.err.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
        }

        public static void readAnswersFromFile(String filePath) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("ANSWER_LIST")) {
                        reader.readLine(); 
                        while (!(line = reader.readLine()).equals("}")) {
                            if (line.equals(" ANSWER")) {
                                reader.readLine(); 
                                int evaluateeCode = -1;
                                int questionCode = -1;
                                String type = null;
                                String answerValue = null;
        
                                while ((line = reader.readLine()) != null && !line.equals(" }")) {
                                    if (line.contains("EVALUATEE_CODE")) {
                                        evaluateeCode = Integer.parseInt(line.trim().split("\\s+")[1]);
                                    } else if (line.contains("QUESTION_CODE")) {
                                        questionCode = Integer.parseInt(line.trim().split("\\s+")[1]);
                                    } else if (line.contains("TYPE")) {
                                        type = line.trim().split("\\s+")[1];
                                    } else if (line.contains("ANSWERS") || line.contains("ANSWER") || line.contains("ORDERED_ANSWERS")) {
                                        answerValue = line.trim().split("\\s+", 2)[1];
                                    }
                                }
        
                                if (evaluateeCode != -1 && questionCode != -1 && type != null && answerValue != null) {
                                    switch (type) {
                                        case "MultipleChoiceAnswer":
                                            List<String> chosenAnswers = Arrays.asList(answerValue.split(","));
                                            answers.add(new MultipleChoiceAnswer(evaluateeCode, questionCode, chosenAnswers));
                                            break;
                                        case "WordAnswer":
                                            answers.add(new WordAnswer(evaluateeCode, questionCode, answerValue));
                                            break;
                                        case "SentenceCompletionAnswer":
                                            List<String> orderedAnswers = Arrays.asList(answerValue.split(","));
                                            answers.add(new SentenceCompletionAnswer(evaluateeCode, questionCode, orderedAnswers));
                                            break;
                                        default:
                                            System.err.println("Invalid answer type found in file: " + type);
                                    }
                                } else {
                                    System.err.println("Invalid format for ANSWER entry.");
                                }
        
                                reader.readLine(); 
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading from the file.");
                e.printStackTrace();
            }
        }
        
        
    }    