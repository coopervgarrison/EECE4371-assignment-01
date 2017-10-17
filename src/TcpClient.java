import java.io.*;
import java.net.*;

public class TcpClient {
    private static final String HOST_ADDRESS = "127.0.0.1";
    private static final int PORT = 6789;

    public static void main(String[] args) {
        try {
            boolean task = true;
            String responseFromServer, userInput;
            Socket socket = new Socket(HOST_ADDRESS, PORT);

            while (task) {
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                responseFromServer = inFromServer.readLine();
                if (responseFromServer.equalsIgnoreCase("Waiting for a command.")
                        || responseFromServer.equalsIgnoreCase("Please enter key.")
                        || responseFromServer.equalsIgnoreCase("Please enter value.")
                        || responseFromServer.equalsIgnoreCase("Please enter key to retrieve its value.")) {
                    System.out.println(responseFromServer);
                    userInput = inFromUser.readLine() + "\n";
                    outToServer.writeBytes(userInput);
                }
                else {
                    System.out.println(responseFromServer);
                    task = false;
                    socket.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
