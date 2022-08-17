package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientReceiveThread extends Thread {
    private DatagramSocket ClientSocket;
    private DatagramPacket receivePacket;
    private String text;
    private String inputName = "Uncknown";
    private InetAddress ip;
    private int port;

    public ClientReceiveThread(DatagramSocket receive) throws Exception {
        ClientSocket = receive;

    }

    public void run() {
        try {

            while (true) {
                byte[] receiveData = new byte[1024];
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                ClientSocket.receive(receivePacket);
                text = new String(receivePacket.getData()).trim();

                if (text.startsWith("@name")) {
                    inputName = text.substring(6);
                    continue;
                }
                if (text.equals("@quit"))
                    System.out.println("User has left");
                else
                    System.out.println(inputName + ": " + text);
            }
        } catch (Exception e) {
            ClientSocket.close();
        }
    }

}
