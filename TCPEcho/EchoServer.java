import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5000)) {
            System.out.println("Server waiting for client...");

            try (Socket socket = server.accept();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(
                            socket.getOutputStream(), true)) {

                System.out.println("Client connected.");

                String message;

                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("exit"))
                        break;

                    out.println(message);
                }

                System.out.println("Client disconnected.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}