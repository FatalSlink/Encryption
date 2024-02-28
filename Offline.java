package encryptionmachine;

public class Offline {

    // Method to encrypt a token represented as a String
    public static String encryptToken(String token) {
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0; i < token.length(); i++) {
            int digit = Character.getNumericValue(token.charAt(i)); // Convert char to integer
            digit = (digit + 7) % 10; // Encrypt the digit
            encrypted.append(digit); // Append the encrypted digit
        }
        
        return encrypted.toString();
    }

    // Method to decrypt an encrypted token back to the original token
    public static String decryptToken(String encryptedToken) {
        StringBuilder decrypted = new StringBuilder();
        
        for (int i = 0; i < encryptedToken.length(); i++) {
            int digit = Character.getNumericValue(encryptedToken.charAt(i)); // Convert char to integer
            digit = (digit + 3) % 10; // Decrypt the digit (reverse of encryption logic)
            decrypted.append(digit); // Append the decrypted digit
        }
        
        return decrypted.toString();
    }

    public static void main(String[] args) {
        String originalToken = "0123"; // Example token with leading zero
        String encryptedToken = encryptToken(originalToken);
        String decryptedToken = decryptToken(encryptedToken);
        
        System.out.println("Original Token: " + originalToken);
        System.out.println("Encrypted Token: " + encryptedToken);
        System.out.println("Decrypted Token: " + decryptedToken);
    }
}

