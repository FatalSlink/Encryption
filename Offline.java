package encryptionmachine;

public class Offline {


    public static String encryptToken(String token) {
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0; i < token.length(); i++) {
            int digit = Character.getNumericValue(token.charAt(i)); 
            digit = (digit + 7) % 10; 
            encrypted.append(digit); 
        }
        
        return encrypted.toString();
    }

  
    public static String decryptToken(String encryptedToken) {
        StringBuilder decrypted = new StringBuilder();
        
        for (int i = 0; i < encryptedToken.length(); i++) {
            int digit = Character.getNumericValue(encryptedToken.charAt(i)); 
            digit = (digit + 3) % 10; 
            decrypted.append(digit); 
        }
        
        return decrypted.toString();
    }

    public static void main(String[] args) {
        String originalToken = "0123"; 
        String encryptedToken = encryptToken(originalToken);
        String decryptedToken = decryptToken(encryptedToken);
        
        System.out.println("Original Token: " + originalToken);
        System.out.println("Encrypted Token: " + encryptedToken);
        System.out.println("Decrypted Token: " + decryptedToken);
    }
}

