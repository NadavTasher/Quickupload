package communications;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class Client {

    private static final File FILES = new File("/var/www/html/files");

    private static final int MAX_SIZE = 1024 * 1024;

    private String id = null;

    public Client(Socket socket) {
        this.id = random(16);
        // Start a listening thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Pipe IO
                    InputStream socketInput = socket.getInputStream();
                    OutputStream socketOutput = socket.getOutputStream();
                    OutputStream fileOutput = new FileOutputStream(new File(FILES, id));
                    for (int character = 0; (socketInput.available() > 0) && (character = socketInput.read()) >= 0; ) {
                        fileOutput.write(character);
                    }
                    // Display link
                    socketOutput.write((System.getenv("URL") + id + "\r\n").getBytes());
                    // Flush all
                    fileOutput.flush();
                    socketOutput.flush();
                    // Close all
                    fileOutput.close();
                    socketOutput.close();
                    socketInput.close();
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
