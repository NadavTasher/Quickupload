import communications.Server;

import java.io.IOException;

public class Main {

    // Server port
    private static final int PORT = 1;

    /**
     * Main function.
     *
     * @param arguments Command line arguments
     */
    public static void main(String[] arguments) {
        Server server = new Server(PORT);
        try {
            server.begin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
