package encryptionmachine;

import java.io.*;
import java.net.*;

public class Client {
    private String hostName;
    private int port;

    public Client(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public void communicateWithServer(String message) {
        try (Socket socket = new Socket(hostName, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(message); 
            System.out.println("Server says: " + in.readLine());

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java TokenClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        Client client = new Client(hostName, portNumber);

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        System.out.println("Enter commands: 'ENCRYPT <token>', 'DECRYPT <token>', or 'SHOW' to interact with the server:");
        while ((userInput = stdIn.readLine()) != null && !userInput.equalsIgnoreCase("exit")) {
            client.communicateWithServer(userInput);
            System.out.println("Enter next command or 'exit' to quit:");
        }
    }
}
