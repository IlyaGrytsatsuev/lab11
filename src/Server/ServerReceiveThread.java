package Server;

import java.io.*;
import java.net.*;

public class ServerReceiveThread extends Thread {
    private DatagramSocket ServerSocket ;
    private DatagramPacket receivePacket;
    private int port;
    private String text;
    private String inputName = "Uncknown" ;
    private ServerSendThread thread2;
    private boolean stop = false;

    public ServerReceiveThread(DatagramSocket server) throws Exception{
        ServerSocket = server;
    }

    public void run()  {
        try {
            byte[] receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            ServerSocket.receive(receivePacket);
            thread2 = new ServerSendThread(ServerSocket, getIp(), getPort());
            thread2.setClientSocketState(true);
            thread2.start();

            while (true) {

                    receiveData = new byte[1024];
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    ServerSocket.receive(receivePacket);
                    text = new String(receivePacket.getData()).trim();

                    if (text.startsWith("@name")) {
                        inputName = text.substring(6);
                        continue;
                    }

                    if (text.equals("@quit")) {
                        thread2.setClientSocketState(false);
                        System.out.println("User has left");
                        break;

                    }

                    else
                        System.out.println(inputName + ": " + text);

            }

        }
        catch (Exception e){
            ServerSocket.close();
            }
    }

    public InetAddress getIp(){
        return receivePacket.getAddress();
    }

    public int getPort(){
        return receivePacket.getPort();
    }

}
