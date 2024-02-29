package javaencryption;

import java.util.Scanner;

public class EncryptionCypher {
		//Michael S. Pearson - JAVA 1 0830 Tue&Thur. 
		//https://github.com/FatalSlink/Encryption.git - Server and Client version that "should" work...
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Bring out the scannah!
        int storedEncryptedToken = 0; // To store the encrypted token as an integer
        
        while (true) { 
            System.out.println("Enter command ('encrypt' or 'decrypt'):");
            String command = scanner.next();

            if ("encrypt".equals(command)) {
                System.out.println("Enter a four-digit token to encrypt:");
                while (!scanner.hasNextInt()) { //Loop to get the inputs and make sure they are numbers because people are stupid.
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scanner.next(); // Get rid of the non number input
                    System.out.println("Enter a four-digit token to encrypt:");
                }
                int token = scanner.nextInt(); // Now we're "sure" it's an integer
                
                if (token >= 1000 && token <= 9999) { // Check if code/token is 4 digits
                    // Encrypt the token with the maths and modulus's
                    int encrypted = 0, place = 1;
                    for (int i = 0; i < 4; i++) {
                        int digit = token % 10;
                        digit = (digit + 7) % 10;
                        encrypted += digit * place;
                        place *= 10;
                        token /= 10;
                    }
                    storedEncryptedToken = encrypted;
                    System.out.println("Token encrypted to: " + storedEncryptedToken);
                } else { //More moron checks
                    System.out.println("Invalid token. Please enter exactly four numeric digits.");
                }
            } else if ("decrypt".equals(command)) {
                System.out.println("Enter the encrypted token to decrypt:");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scanner.next(); // ^.^
                    System.out.println("Enter the encrypted token to decrypt:");
                }
                int encryptedToken = scanner.nextInt(); // Now we're sure it's an integer again
                
                if (storedEncryptedToken == encryptedToken) {
                    // Decrypt the token
                    int decrypted = 0, place = 1;
                    for (int i = 0; i < 4; i++) {
                        int digit = encryptedToken % 10;
                        digit = (digit + 3) % 10; // Reverse the encryption logic
                        decrypted += digit * place;
                        place *= 10;
                        encryptedToken /= 10;
                    }
                    System.out.println("Decryption successful. Decrypted token: " + decrypted);
                } else {
                    System.out.println("Invalid token.");
                }
            } else {
                System.out.println("Unknown command.");
            }
        }
    }
}
