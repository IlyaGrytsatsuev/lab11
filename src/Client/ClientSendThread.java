package Client;
import java.io.*;
import java.net.*;
import java.util.*;

public class ClientSendThread extends Thread{
    private BufferedReader toServerText;
    DatagramSocket ClientSocket;
    private String inputName = "Uncknown";
    private InetAddress ip ;
    private DatagramPacket sendPacket;
    private int port;
    private boolean finish = false;

    public ClientSendThread( DatagramSocket send, String ip_, int port_) throws Exception{
        ClientSocket = send;
        ip = InetAddress.getByName(ip_);
        port = port_;
    }

    public void run(){
        try {
            String text = "@NewClient";
            byte [] sendData = new byte [1024];
            sendData = text.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
            ClientSocket.send(sendPacket);
            while (true) {
                toServerText = new BufferedReader(new InputStreamReader(System.in));
                text = toServerText.readLine();
                sendData = text.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
                ClientSocket.send(sendPacket);

                if(text.equals("@quit")) {
                    ClientSocket.close();
                    break;
                }

            }
        }

        catch(Exception e){
            e.printStackTrace();
        }


    }
}
