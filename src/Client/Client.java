package Client;

import java.net.DatagramSocket;

public class Client {
    private DatagramSocket ClientSocket;
    private int port;
    private String ip;
    private boolean finish = false;

    public Client(String ip_, int port_) throws Exception{
        ip = ip_;
        port = port_;
        ClientSocket = new DatagramSocket();
        ClientSendThread thread1 = new ClientSendThread (ClientSocket, ip, port);
        ClientReceiveThread thread2 = new ClientReceiveThread(ClientSocket);
        thread1.start();
        thread2.start();

    }
}


