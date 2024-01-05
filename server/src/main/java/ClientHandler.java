import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {

    private final Socket client;
    private final Server server;

    public ClientHandler(Socket client, Server server) {

        this.server = server;
        this.client = client;
    }


    @Override
    public void run() {

        try (
             var dataOut = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
             var dataIn = new DataInputStream(new BufferedInputStream(client.getInputStream()))
        ) {

            while (true) {

                String inputMessage = dataIn.readUTF();

                dataOut.writeUTF(inputMessage);
                dataOut.flush();
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }


}
