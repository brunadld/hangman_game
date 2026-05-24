import java.util.Random;
import java.util.Scanner;

public class rps {
    public static int selectOption(Scanner s) {
        System.out.println("Select an option:\n[1] Rock\t[2] Paper\t[3] Scissors");
        int x = s.nextInt();
        System.out.println();

        switch(x) {
            case 1 -> System.out.println("PLAYER picks ROCK");
            case 2 -> System.out.println("PLAYER picks PAPER");
            case 3 -> System.out.println("PLAYER picks SCISSORS");
        }

        return x;
    }

    public static int computerChoice() {
        Random rand = new Random();
        int y = rand.nextInt(1, 3);

        switch(y) {
            case 1 -> System.out.println("COMPUTER picks ROCK");
            case 2 -> System.out.println("COMPUTER picks PAPER");
            case 3 -> System.out.println("COMPUTER picks SCISSORS");
        }

        return y;
    }
    
    public static boolean tie(int x, int y) {
        return x == y;  // return true if x == y
    }

    public static void winner(int x, int y) {
        if(x == 1 && y == 3 || x == 2 && y == 1 || x == 3 && y == 2) {
            System.out.println("PLAYER wins!");
        }
        else {
            System.out.println("COMPUTER wins!");
        }
    }

    public static void main(String[] args) {
        try(Scanner s = new Scanner(System.in)) {

            int player = selectOption(s);
            int computer = computerChoice();

            while(tie(player, computer)) {
                System.out.println("It's a tie!\n");
                player = selectOption(s);
                computer = computerChoice();
            }

            winner(player, computer);
        }
    }
}