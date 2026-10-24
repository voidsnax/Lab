import java.io.*;
import java.net.*;
import java.util.*;

public class MultiClientChatServer {
    static Set<PrintWriter> clients = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(5000)) {
            System.out.println("Chat server started.");

            while (true) {
                Socket socket = server.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    static void broadcast(String message) {
        synchronized (clients) {
            for (PrintWriter client : clients)
                client.println(message);
        }
    }

    static class ClientHandler extends Thread {
        Socket socket;
        PrintWriter out;
        String name;

        ClientHandler(Socket socket) {
            this.socket = socket;
            name = "Client-" + socket.getPort();
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clients) {
                    clients.add(out);
                }

                broadcast(name + " joined the chat.");

                String message;

                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("exit"))
                        break;

                    broadcast(name + ": " + message);
                }
            } catch (IOException e) {
                System.out.println(name + " disconnected.");
            } finally {
                if (out != null) {
                    synchronized (clients) {
                        clients.remove(out);
                    }
                }

                try {
                    socket.close();
                } catch (IOException e) {
                    // Socket already closed.
                }

                broadcast(name + " left the chat.");
            }
        }
    }
}