import java.io.*;
import java.net.*;
import java.util.*;

public class TcpServer {
    private static final int PORT = 6789;

    public static void main(String argv[]) {
        try {
            String clientSentence = "", key = "", value = "";
            ServerSocket serverSocket = new ServerSocket(PORT);
            Map<String, String> map = new HashMap<String, String>();

            while (true) {
                Socket connectionSocket = serverSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                outToClient.writeBytes("Waiting for a command.\n");
                clientSentence = inFromClient.readLine();
                if (clientSentence.equalsIgnoreCase("store")) {
                    outToClient.writeBytes("Please enter key.\n");
                    key = inFromClient.readLine();
                    outToClient.writeBytes("Please enter value.\n");
                    value = inFromClient.readLine();
                    map.put(key, value);
                    outToClient.writeBytes("Successfully stored : " + key + ": " + value + "\n");
                }
                else if (clientSentence.equalsIgnoreCase("retrieve")) {
                    outToClient.writeBytes("Please enter key to retrieve its value.\n");
                    key = inFromClient.readLine();
                    value = map.containsKey(key) ? "Value: " + map.get(key) + "\n" : "Invalid Key!\n";
                    outToClient.writeBytes(value);
                }
                else {
                    outToClient.writeBytes("Invalid Command.\n");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
