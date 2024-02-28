package encryptionmachine;

import java.util.Random;
import java.util.Scanner;

public class Classwork {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int total = 0;
        boolean playerTurn = true; // true for player's turn, false for computer's turn

        while (total < 21) {
            if (playerTurn) {
                System.out.print("Your turn (enter a value between 1 and 11): ");
                int playerChoice = scanner.nextInt();
                // Validate player input
                while (playerChoice < 1 || playerChoice > 11) {
                    System.out.println("Invalid input. Please enter a value between 1 and 11.");
                    playerChoice = scanner.nextInt();
                }
                total += playerChoice;
                System.out.println("Player added " + playerChoice + ". Total is now " + total + ".");
            } else {
                // Simple computer strategy within the rules
                int computerChoice;
                if (total < 16) {
                    // Riskier play if total is significantly below 21
                    computerChoice = 6 + random.nextInt(6); // Chooses 6-11 to try and reach close to 21
                } else {
                    // Conservative play if total is close to 21
                    computerChoice = 1 + random.nextInt(5); // Chooses 1-5 to avoid going over 21
                }
                total += computerChoice;
                System.out.println("Computer added " + computerChoice + ". Total is now " + total + ".");
            }
            playerTurn = !playerTurn;

            if (total >= 21) {
                if (playerTurn) {
                    System.out.println("Computer wins!");
                } else {
                    System.out.println("Player wins!");
                }
                break;
            }
        }
        scanner.close();
    }
}
