import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        try (var socket = new ServerSocket(8090)) {
            var server = new Server(socket.getInetAddress());
            while (true) {

                System.out.println("waiting for client to connect...");
                Socket client = socket.accept();

                CompletableFuture.runAsync(new ClientHandler(client, server));
            }
        } catch (IOException e) {
            System.err.println("OH NO SERVER ERRRORR!!! : " + e.getMessage());
        }
    }
}
