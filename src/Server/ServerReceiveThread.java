package Server;

import java.io.*;
import java.net.*;

public class ServerReceiveThread extends Thread {
    private DatagramSocket ServerSocket ;
    private DatagramPacket receivePacket;
    private int port;
    private String text;
    private String inputName = "Uncknown" ;
    private int isReceived = 0;
    private ServerSendThread thread2;

    public ServerReceiveThread(DatagramSocket server) throws Exception{
        ServerSocket = server;
    }

    public void run()  {
        try {
            while (true) {

                    byte[] receiveData = new byte[1024];
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    ServerSocket.receive(receivePacket);
                    isReceived = 1;
                    text = new String(receivePacket.getData()).trim();

                    if (isReceived == 1) {
                        thread2 = new ServerSendThread(ServerSocket, getIp(), getPort());
                        thread2.start();
                        isReceived ++;
                    }


                    if (text.startsWith("@name")) {
                        inputName = text.substring(6);
                        continue;
                    }

                    if (text.equals("@quit"))
                        System.out.println("User has left");

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

    public int getReceivedState(){
        return isReceived;
    }

    public Thread getThread(){
        return thread2;
    }
}
