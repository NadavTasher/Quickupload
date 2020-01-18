package communications;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Client {

    private static final File FILES = new File("/var/www/html/files");

    private static final int MAX_SIZE = 1024 * 1024;

    private String id = null;

    // Socket IO
    BufferedReader reader = null;
    BufferedWriter writer = null;

    // File IO
    FileWriter fileWriter = null;

    public Client(Socket socket) {
        this.id = random(16);
        // Start a listening thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fileWriter = new FileWriter(new File(FILES, id));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    // Listen on reader
                    int character = 0;
                    while (reader.ready() && character < MAX_SIZE) {
                        fileWriter.write(reader.read());
                        character++;
                    }
                    fileWriter.flush();
                    fileWriter.close();
                    writer.write(System.getenv("URL") + id);
                    writer.newLine();
                    writer.flush();
                    writer.close();
                    reader.close();
                    socket.close();
                } catch (Exception ignored) {
                }
            }
        }).start();
    }

    private static String random(int length) {
        final String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
        if (length > 0) {
            return charset.charAt(new Random().nextInt(charset.length())) + random(length - 1);
        }
        return "";
    }
}
