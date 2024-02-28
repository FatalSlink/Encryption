package encryptionmachine;

import java.io.*;
import java.net.*;

public class Server {
    private static String storedEncryptedToken = null; 
    private static String originalToken = null; 

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
                        out.println("TOKEN ENCRYPTED AND STORED");
                    } else if (inputLine.startsWith("DECRYPT")) {
                        String attempt = inputLine.substring(8);
                        String decryptedToken = decryptToken(storedEncryptedToken);
                        if (attempt.equals(decryptedToken)) {
                            out.println("DECRYPTION SUCCESSFUL");
                        } else {
                            out.println("DECRYPTION FAILED");
                        }
                    } else if (inputLine.equals("SHOW")) {
                        if (originalToken != null) {
                            out.println("ORIGINAL TOKEN: " + originalToken);
                        } else {
                            out.println("NO TOKEN TO SHOW");
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
            digit = (digit + 3) % 10; 
            decrypted.append(digit);
        }
        return decrypted.toString();
    }
}
