package communications;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Timer;
import java.util.TimerTask;

public class Server {

    private ServerSocket serverSocket = null;
    private int port = 0;

    public Server(int port) {
        this.port = port;
    }

    public void begin() throws IOException {
        // Try initializing the socket
        serverSocket = new ServerSocket(port);
        // Schedule client listener
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    new Client(serverSocket.accept());
                } catch (IOException ignored) {
                }
            }
        }, 0, 500);
    }

}
