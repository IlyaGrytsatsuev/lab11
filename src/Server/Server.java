package Server;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    private int port;
    private DatagramSocket ServerSocket ;
    private InetAddress ip;
    private boolean ClientLeft = false;
    private ServerReceiveThread thread1;

    public Server (int port_) throws Exception {
        port = port_;
        ServerSocket = new DatagramSocket(port);
        thread1 = new ServerReceiveThread(ServerSocket);
        thread1.start();
            while (true) {
                if (!thread1.isAlive()) {
                    if (!ServerSocket.isClosed()) {
                        thread1 = new ServerReceiveThread(ServerSocket);
                        thread1.start();
                    } else
                        System.exit(0);
                }
            }
    }
}
