package encryptionmachine;

public class NotLeading {


    public static int encryptToken(int token) {
        int encrypted = 0;
        int place = 1000; 
        
        while (token > 0) {
            int digit = token % 10; 
            digit = (digit + 7) % 10; 
            encrypted = digit * place + encrypted; 
            token /= 10; 
            place /= 10;
        }
        
        return encrypted;
    }

   
    public static int decryptToken(int encryptedToken) {
        int decrypted = 0;
        int place = 1000; 
        
        while (encryptedToken > 0) {
            int digit = encryptedToken % 10;
            digit = (digit + 3) % 10; 
            decrypted = digit * place + decrypted; 
            encryptedToken /= 10; 
            place /= 10; 
        }
        
        return decrypted;
    }

    public static void main(String[] args) {
        int originalToken = 1234; 
        int encryptedToken = encryptToken(originalToken);
        int decryptedToken = decryptToken(encryptedToken);
        
        System.out.println("Original Token: " + originalToken);
        System.out.println("Encrypted Token: " + encryptedToken);
        System.out.println("Decrypted Token: " + decryptedToken);
    }
}

