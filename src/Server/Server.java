package Server;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    private int port;
    private DatagramSocket ServerSocket ;
    private InetAddress ip;

    public Server (int port_) throws Exception {
        port = port_;
        ServerSocket = new DatagramSocket(port);
        ServerReceiveThread thread1 = new ServerReceiveThread(ServerSocket);
        thread1.start();
    }
}
