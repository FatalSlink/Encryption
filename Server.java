package encryptionmachine;

import java.io.*;
import java.net.*;

public class Server {
    private static String storedEncryptedToken = null;
    private static String originalToken = null;
    private static boolean isDecryptionSuccessful = false; // Flag to track successful decryption

    public static void main(String[] args) throws IOException {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    String inputLine = in.readLine();
                    System.out.println("Received: " + inputLine);

                    if (inputLine.startsWith("ENCRYPT")) {
                        originalToken = inputLine.substring(8);
                        storedEncryptedToken = encryptToken(originalToken);
                        isDecryptionSuccessful = false; // Reset flag since a new token is being encrypted
                        out.println("TOKEN ENCRYPTED AND STORED");
                    } else if (inputLine.startsWith("DECRYPT")) {
                        String attempt = inputLine.substring(8);
                        String decryptedToken = decryptToken(storedEncryptedToken);
                        if (attempt.equals(decryptedToken)) {
                            out.println("DECRYPTION SUCCESSFUL");
                            isDecryptionSuccessful = true; // Set flag on successful decryption
                        } else {
                            out.println("DECRYPTION FAILED");
                            isDecryptionSuccessful = false;
                        }
                    } else if (inputLine.equals("SHOW")) {
                        if (isDecryptionSuccessful && originalToken != null) {
                            out.println("ORIGINAL TOKEN: " + originalToken);
                        } else {
                            out.println("NO TOKEN TO SHOW OR DECRYPTION UNSUCCESSFUL");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port " + port + " or listening for a connection");
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static String encryptToken(String token) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : token.toCharArray()) {
            int digit = Character.getNumericValue(c);
            digit = (digit + 7) % 10;
            encrypted.append(digit);
        }
        return encrypted.toString();
    }

    private static String decryptToken(String encryptedToken) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encryptedToken.toCharArray()) {
            int digit = Character.getNumericValue(c);
            digit = (digit + 3) % 10; // Reverse encryption logic
            decrypted.append(digit);
        }
        return decrypted.toString();
    }
}
