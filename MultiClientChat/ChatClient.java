import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             Scanner sc = new Scanner(System.in)) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            System.out.println("Connected to chat. Type exit to leave.");

            Thread receiver = new Thread(new MessageReceiver(in));
            receiver.setDaemon(true);
            receiver.start();

            while (true) {
                String message = sc.nextLine();
                out.println(message);

                if (message.equalsIgnoreCase("exit"))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}

class MessageReceiver implements Runnable {
    BufferedReader in;

    MessageReceiver(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            String message;

            while ((message = in.readLine()) != null)
                System.out.println("\n" + message);

        } catch (IOException e) {
            // Connection closed.
        }
    }
}