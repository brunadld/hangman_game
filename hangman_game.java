import java.util.*;

public class hangman_game {
    public static void drawHangman(int x) {
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 5; j++) {
                switch(i) {
                    case 0 -> System.out.print("__");
                    case 1 -> {
                        switch(j) {
                            case 0 -> System.out.print("|");
                            case 1 -> System.out.print("\t");
                            case 2 -> System.out.print(" ");
                            case 3 -> System.out.print("|");
                            case 4 -> System.out.print(" ");
                        }
                    }
                    case 2 -> {
                        switch(j) {
                            case 0 -> System.out.print("|");
                            case 1 -> System.out.print("\t");
                            case 2 -> System.out.print(" ");
                            case 3 -> {
                                if(x == 0) {
                                    System.out.print(" ");
                                }
                                else {
                                    System.out.print("O");
                                }
                            }
                            case 4 -> System.out.print(" ");
                        }
                    }
                    case 3 -> {
                        switch(j) {
                            case 0 -> System.out.print("|");
                            case 1 -> System.out.print("\t");
                            case 2 -> {
                                if(x > 2) {
                                    System.out.print("/");
                                }
                                else {
                                    System.out.print(" ");
                                }
                            }
                            case 3 -> {
                                if(x > 1) {
                                    System.out.print("|");
                                }
                                else {
                                    System.out.print(" ");
                                }
                            }
                            case 4 -> {
                                if(x > 3) {
                                    System.out.print("\\");
                                }
                                else {
                                    System.out.print(" ");
                                }
                            }
                        }
                    }
                    case 4 -> {
                        switch(j) {
                            case 0 -> System.out.print("|");
                            case 1 -> System.out.print("\t");
                            case 2 -> {
                                if(x > 4) {
                                    System.out.print("/");
                                }
                                else {
                                    System.out.print(" ");
                                }
                            }
                            case 3 -> System.out.print(" ");
                            case 4 -> {
                                if(x > 5) {
                                    System.out.print("\\");
                                }
                                else {
                                    System.out.print(" ");
                                }
                            }
                        }
                    }
                    case 5 -> {
                        if(j == 0) {
                            System.out.print("|");
                        }
                        else {
                            System.out.print("___");
                        }
                    }
                    default -> {
                    }    
                    }
                }

                System.out.print("\n");
            }

            System.out.println();
    }
    
    public static String chooseWord(int option, Scanner scan) {
        if(option == 2) {
            scan.nextLine();

            System.out.println("Insert a word:");
            return scan.nextLine().toLowerCase();
        }
        else if(option < 1 || option > 2) {
            System.out.println("Invalid command! A word will be selected randomly.");
        }

        List<String> words = new ArrayList<>();
        
        words.add("mirror");
        words.add("trampoline");
        words.add("lollipop");
        words.add("star");
        words.add("committed");

        int x = words.size() - 1;
        
        Random picker = new Random();
        int choice = picker.nextInt(0, x);
        
        return words.get(choice);
    }
    
    public static String fillDisplay(String word) {
        String x = "";

        for(int i = 0; i < word.length(); i++) {
            x += "_";
        }

        return x;
    }

    public static void printDisplay(String word, int n, String letters) {
        System.out.println("\nERRORS: "+n+"\tWRONG GUESSES: "+letters+"\n");
        drawHangman(n);
        
        for(int i = 0; i < word.length(); i++) {
            System.out.print(word.charAt(i)+" ");
        }
        System.out.println("\n");
    }
    
    public static String updateDisplay(String input, String answer, String display) {
        StringBuilder sb = new StringBuilder(display);

        for(int i = 0; i < answer.length(); i++) {
            if(answer.charAt(i) == input.charAt(0)) {
                sb.setCharAt(i, answer.charAt(i));
            }
        }

        return sb.toString();
    }

    public static int checkAnswer(int n, String input, String answer) {
        for(int i = 0; i < answer.length(); i++) {
            if(answer.charAt(i) == input.charAt(0)) {
                return n;
            }
        }
        
        return n + 1;
    }

    public static String updateMistakes(String input, String letters, String answer) {
        for(int i = 0; i < answer.length(); i++) {
            if(answer.charAt(i) == input.charAt(0)) {
                return letters;
            }
        }

        letters += input + " ";
        return letters;
    }
    
    public static boolean gameOver(String answer, String display) {
        for(int i = 0; i < answer.length(); i++) {
            if(answer.charAt(i) != display.charAt(i)) {
                return false;
            }
        }

        System.out.print("\nYOU WON! Answer: ");

        for(int i = 0; i < display.length(); i++) {
            System.out.print(display.toUpperCase().charAt(i));
        }

        return true;
    }

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Choose an option:\n[1]\tRandomly chosen word");
            System.out.println("[2]\tWord chosen by user input");
            
            int option = scan.nextInt();
            String answer = chooseWord(option, scan);
            
            int errors = 0;
            String wrongLetters = "";
            
            String display = fillDisplay(answer);
            
            while(errors < 7 && !gameOver(answer, display)) {
                printDisplay(display, errors, wrongLetters);
                
                System.out.print("Choose a letter: ");
                String userInput = scan.next().toLowerCase();
                display = updateDisplay(userInput, answer, display);
                
                errors = checkAnswer(errors, userInput, answer);
                wrongLetters = updateMistakes(userInput, wrongLetters, answer);
            }         
            
            if(errors == 7) {
                printDisplay(display, errors, wrongLetters);
                System.out.println("\nYOU LOST! Answer: "+answer.toUpperCase());
            }
        }
    }
}