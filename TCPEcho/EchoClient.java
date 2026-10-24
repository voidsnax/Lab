import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(
                        socket.getOutputStream(), true);
                Scanner sc = new Scanner(System.in)) {

            String message;

            while (true) {
                System.out.print("Enter message (exit to quit): ");
                message = sc.nextLine();

                out.println(message);

                if (message.equalsIgnoreCase("exit"))
                    break;

                String reply = in.readLine();
                System.out.println("Echo from server: " + reply);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}