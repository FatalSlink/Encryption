package encryptionmachine;

public class NotLeading {

    // Method to encrypt a four-digit integer token
    public static int encryptToken(int token) {
        int encrypted = 0;
        int place = 1000; // To handle the place value (thousands, hundreds, tens, ones)
        
        while (token > 0) {
            int digit = token % 10; // Extract the last digit
            digit = (digit + 7) % 10; // Encrypt the digit
            encrypted = digit * place + encrypted; // Place the encrypted digit in the correct place
            token /= 10; // Move to the next digit
            place /= 10; // Move to the next place value
        }
        
        return encrypted;
    }

    // Method to decrypt a four-digit encrypted token back to the original token
    public static int decryptToken(int encryptedToken) {
        int decrypted = 0;
        int place = 1000; // To handle the place value (thousands, hundreds, tens, ones)
        
        while (encryptedToken > 0) {
            int digit = encryptedToken % 10; // Extract the last digit
            digit = (digit + 3) % 10; // Decrypt the digit
            decrypted = digit * place + decrypted; // Place the decrypted digit in the correct place
            encryptedToken /= 10; // Move to the next digit
            place /= 10; // Move to the next place value
        }
        
        return decrypted;
    }

    public static void main(String[] args) {
        int originalToken = 1234; // Example token
        int encryptedToken = encryptToken(originalToken);
        int decryptedToken = decryptToken(encryptedToken);
        
        System.out.println("Original Token: " + originalToken);
        System.out.println("Encrypted Token: " + encryptedToken);
        System.out.println("Decrypted Token: " + decryptedToken);
    }
}

