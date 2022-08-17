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

            while (true) {
                toServerText = new BufferedReader(new InputStreamReader(System.in));
                String text = toServerText.readLine();
                byte[] sendData = text.getBytes();
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
