import java.util.Scanner;


// FRUIT BLUEPRINT
// Represents a fruit and all its characteristics used in the game
class Fruit {

    // Basic identity
    String name;

    // Attributes used for questioning
    String skinColor, taste, size, insideColor, seedCount;
    String peel, shape, growsOnTree, juicy;

    // Tracks whether this fruit is still a possible answer
    boolean isStillInGame;

    // Constructor: initializes all properties of a fruit
    Fruit(String name, String skinColor, String taste, String size,
          String insideColor, String seedCount, String peel,
          String shape, String growsOnTree, String juicy) {

        this.name = name;
        this.skinColor = skinColor;
        this.taste = taste;
        this.size = size;
        this.insideColor = insideColor;
        this.seedCount = seedCount;
        this.peel = peel;
        this.shape = shape;
        this.growsOnTree = growsOnTree;
        this.juicy = juicy;

        // Every fruit starts as a valid candidate
        this.isStillInGame = true;
    }

    // Returns the value of a specific attribute based on its name
    public String getAttributeValue(String attr) {
        switch (attr) {
            case "skinColor": return skinColor;
            case "taste": return taste;
            case "size": return size;
            case "insideColor": return insideColor;
            case "seedCount": return seedCount;
            case "peel": return peel;
            case "shape": return shape;
            case "growsOnTree": return growsOnTree;
            case "juicy": return juicy;
            default: return "";
        }
    }

    // Converts attribute + value into a human-friendly question
    public static String frameQuestion(String attr, String val) {
        switch (attr) {
            case "skinColor": return "Is the outer color " + val + "?";
            case "taste": return "Is it " + val + " in taste?";
            case "size": return "Is it " + val + " in size?";
            case "insideColor": return "Is the inside color " + val + "?";
            case "seedCount": return "Does it have " + val + " seeds?";
            case "peel": return val.equals("yes") ? "Do you peel it before eating?" : "Do you NOT peel it before eating?";
            case "shape": return "Is it " + val + " in shape?";
            case "growsOnTree": return val.equals("yes") ? "Does it grow on a tree?" : "Does it NOT grow on a tree?";
            case "juicy": return val.equals("yes") ? "Is it juicy?" : "Is it NOT juicy?";
            default: return "Is it " + val + "?";
        }
    }
}


// MAIN GAME PROGRAM

public class fruitinator {
    public static void main(String[] args) {
        
        // Build the database of 25 fruits
        Fruit[] fruits = new Fruit[25];
        int fruitsLeft = 25; 


        fruits[0] = new Fruit("Mango","yellow","sweet","medium","yellow","one","yes","oval","yes","yes");
        fruits[1] = new Fruit("Banana","yellow","sweet","medium","white","none","yes","long","no","no");
        fruits[2] = new Fruit("Apple","red","sweet","medium","white","few","no","round","yes","no");
        fruits[3] = new Fruit("Orange","orange","sweet/sour","medium","orange","few","yes","round","yes","yes");
        fruits[4] = new Fruit("Grapes","green/purple","sweet","small","green","none","no","round","no","yes");
        fruits[5] = new Fruit("Watermelon","green","sweet","large","red","many","yes","round","no","yes");
        fruits[6] = new Fruit("Papaya","orange","sweet","large","orange","many","yes","oval","yes","yes");
        fruits[7] = new Fruit("Guava","green","sweet/sour","small","white","many","no","round","yes","no");
        fruits[8] = new Fruit("Pomegranate","red","sweet/sour","medium","red","many","yes","round","yes","yes");
        fruits[9] = new Fruit("Pineapple","brown","sweet/sour","large","yellow","none","yes","oval","no","yes");
        fruits[10] = new Fruit("Peach","orange","sweet","medium","yellow","one","no","round","yes","yes");
        fruits[11] = new Fruit("Blueberry","blue","sweet","small","blue","many","no","round","no","yes");
        fruits[12] = new Fruit("CustardApple","green","sweet","small","white","many","yes","round","yes","no");
        fruits[13] = new Fruit("Lychee","red","sweet","small","white","one","yes","round","yes","yes");
        fruits[14] = new Fruit("Muskmelon","yellow","sweet","large","orange","many","yes","round","no","yes");
        fruits[15] = new Fruit("Coconut","brown","sweet","large","white","one","yes","round","yes","yes");
        fruits[16] = new Fruit("Lemon","yellow","sour","small","yellow","few","yes","oval","yes","yes");
        fruits[17] = new Fruit("Strawberry","red","sweet/sour","small","red","many","no","heart","no","yes");
        fruits[18] = new Fruit("Jamun","purple","sour","small","purple","one","no","oval","yes","yes");
        fruits[19] = new Fruit("Ber","green","sweet/sour","small","white","one","no","round","yes","no");
        fruits[20] = new Fruit("Kiwi","brown","sour","small","green","many","yes","oval","no","yes");
        fruits[21] = new Fruit("Avocado","green","bland","medium","green","one","yes","oval","yes","no");
        fruits[22] = new Fruit("Dragonfruit","pink","sweet","medium","white","many","yes","oval","no","yes");
        fruits[23] = new Fruit("Jackfruit","green","sweet","large","yellow","many","yes","irregular","yes","no");
        fruits[24] = new Fruit("Bel","brown","sweet","medium","brown","many","yes","round","yes","no");

        // Display intro and rules
        System.out.println("=== FRUITINATOR ===\n");
        System.out.println("Welcome to Fruitinator.\n");
        System.out.println("Instructions:");
        System.out.println("1. Look at the list of fruits below.");
        System.out.println("2. Choose ONE fruit in your mind. Do not type it.");
        System.out.println("3. I will ask you a series of yes/no questions.");
        System.out.println("4. Answer honestly using only: yes or no.");
        System.out.println("5. Based on your answers, I will try to guess your fruit.\n");

        System.out.println("Fruits:\n");

        // Print the list of fruits (5 per line)
        int printCount = 0;
        for (int i = 0; i < fruits.length; i++) {
            System.out.print(fruits[i].name);
            if (i != fruits.length - 1) {
                System.out.print(", ");
            }
            printCount++;
            if (printCount == 5) {
                System.out.println();
                printCount = 0;
            }
        }

        System.out.println("\n\nPress ENTER to start...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        System.out.println("\nGame starting...\n");
        String[] attributes = {"skinColor","taste","size","insideColor","seedCount","peel","shape","growsOnTree","juicy"};

        int questionsAsked = 0;
        
        // MAIN GAME LOOP
        // Runs until only 1 fruit is left OR we reach the 4-question limit
        while (fruitsLeft > 1 && questionsAsked < 4) {
            
            // Parallel arrays to hold questions that actually help narrow down the list
            String[] validAttributes = new String[250];
            String[] validValues = new String[250];
            int[] splitDifferences = new int[250];
            int validQuestions = 0;

            // STEP 1: ANALYSIS
            // Test every possible question to see how well it splits the remaining fruits
            for (int a = 0; a < attributes.length; a++) {
                String currentAttribute = attributes[a];

                String[] alreadyCheckedValues = new String[25];
                int checkedCount = 0;

                for (int i = 0; i < fruits.length; i++) {
                    if (fruits[i].isStillInGame == true) {
                        String testValue = fruits[i].getAttributeValue(currentAttribute);
                        
                        // Check if we already tested this specific trait to avoid duplicate work
                        boolean alreadyChecked = false;
                        for (int t = 0; t < checkedCount; t++) {
                            if (alreadyCheckedValues[t].equals(testValue)) { 
                                alreadyChecked = true; 
                                break; 
                            }
                        }
                        
                        if (alreadyChecked == false) {
                            alreadyCheckedValues[checkedCount] = testValue;
                            checkedCount++;
                            
                            int fruitsWithTrait = 0;
                            int fruitsWithoutTrait = 0;

                            // Count how many remaining fruits have this trait and how many don't
                            for (int j = 0; j < fruits.length; j++) {
                                if (fruits[j].isStillInGame == true) {
                                    if (fruits[j].getAttributeValue(currentAttribute).equals(testValue)) {
                                        fruitsWithTrait++;
                                    } else {
                                        fruitsWithoutTrait++;
                                    }
                                }
                            }

                            // If the trait splits the group, save it. 
                            // The math score is the difference (closest to 0 is a perfect 50/50 split)
                            if (fruitsWithTrait > 0 && fruitsWithoutTrait > 0) {
                                validAttributes[validQuestions] = currentAttribute;
                                validValues[validQuestions] = testValue;
                                splitDifferences[validQuestions] = Math.abs(fruitsWithTrait - fruitsWithoutTrait);
                                validQuestions++;
                            }
                        }
                    }
                }
            }

            // If no questions can split the remaining fruits anymore, stop asking
            if (validQuestions == 0) {
                break;
            }

            // STEP 2: SORTING
            // Standard Bubble Sort to rank our saved questions from Best (lowest score) to Worst
            for (int i = 0; i < validQuestions - 1; i++) {
                for (int j = 0; j < validQuestions - i - 1; j++) {
                    if (splitDifferences[j] > splitDifferences[j + 1]) {
                        
                        // Swap the scores
                        int tempDifference = splitDifferences[j];
                        splitDifferences[j] = splitDifferences[j + 1];
                        splitDifferences[j + 1] = tempDifference;
                        
                        // Swap the attributes to keep everything aligned
                        String tempAttribute = validAttributes[j];
                        validAttributes[j] = validAttributes[j + 1];
                        validAttributes[j + 1] = tempAttribute;
                        
                        // Swap the values to keep everything aligned
                        String tempValue = validValues[j];
                        validValues[j] = validValues[j + 1];
                        validValues[j + 1] = tempValue;
                    }
                }
            }

            // STEP 3: QUESTION SELECTION
            // Pick a random question from the top 5 to keep the game unpredictable
            int topChoicesCount = 5;
            if (validQuestions < 5) {
                topChoicesCount = validQuestions;
            }
            
            int randomIndex = (int)(Math.random() * topChoicesCount);
            String chosenAttribute = validAttributes[randomIndex];
            String chosenValue = validValues[randomIndex];

            // Print the question and get user input
            System.out.println("\nQuestion " + (questionsAsked + 1) + ": " + Fruit.frameQuestion(chosenAttribute, chosenValue));
            String answer = sc.nextLine();
            answer = answer.toLowerCase();
            
            // Keep asking until they type 'yes' or 'no'
            while (true) {
                if (answer.equals("yes") || answer.equals("no")) {
                    break; 
                } else {
                    System.out.println("Invalid input. Please type exactly 'yes' or 'no'.");
                    answer = sc.nextLine();
                    answer = answer.toLowerCase();
                }
            }

            // STEP 4: ELIMINATION
            // Cross off fruits that contradict what the user just answered
            for (int i = 0; i < fruits.length; i++) {
                if (fruits[i].isStillInGame == true) {
                    boolean fruitHasTrait = false;
                    
                    if (fruits[i].getAttributeValue(chosenAttribute).equals(chosenValue)) {
                        fruitHasTrait = true;
                    }
                    
                    // If user said yes, but fruit doesn't have it -> eliminate
                    if (answer.equals("yes") && fruitHasTrait == false) {
                        fruits[i].isStillInGame = false; 
                        fruitsLeft--;
                    } 
                    // If user said no, but fruit DOES have it -> eliminate
                    else if (answer.equals("no") && fruitHasTrait == true) {
                        fruits[i].isStillInGame = false; 
                        fruitsLeft--;
                    }
                }
            }
            
            questionsAsked++;
        }

        // END GAME
        // Guess the final fruit or admit defeat

        if (fruitsLeft == 0) {
            System.out.println("\nYou stumped me! There are no fruits left that match your answers.");
        } else {
            
            // Find a random fruit that is still marked as active
            int currentIndex = (int)(Math.random() * fruits.length);
            Fruit finalGuess = null;

            for (int i = 0; i < fruits.length; i++) {

                if (fruits[currentIndex].isStillInGame == true) {
                    finalGuess = fruits[currentIndex];
                    break; //found it, Exit the loop.
                }

                currentIndex++;

                if (currentIndex == fruits.length) {
                    currentIndex = 0;
                }
            }

            System.out.println("\nIs your fruit a " + finalGuess.name + "? (yes/no)");
            
            String finalAnswer = sc.nextLine();
            finalAnswer = finalAnswer.toLowerCase();
            
            if (finalAnswer.equals("yes")) {
                System.out.println("Aha! I win! The Fruitinator prevails!");
            } else {
                System.out.println("You win! My database wasn't strong enough.");
            }
        }
        
        sc.close();
    }
}