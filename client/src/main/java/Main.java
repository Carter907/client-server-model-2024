import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // client connecting to server on 8090 (localhost)
        try (var socket = new Socket("localhost", 8090);
             var dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
             var dataIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
             var cli = new Scanner(System.in)
        ) {
            System.out.println("Connection successful!");
            System.out.println(socket.getInetAddress());

            while (true) {
                String message = cli.nextLine();
                dataOut.writeUTF(message);
                dataOut.flush();
                System.out.println("wrote: " + message);

                String reply = dataIn.readUTF();
                System.out.println("Server reply: " + reply);

            }

        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
