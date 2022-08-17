package Server;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerSendThread extends Thread{
    private BufferedReader toClientText;
    private DatagramSocket ServerSocket;
    private DatagramPacket sendPacket;
    private byte[] sendData = new byte[1024];
    private InetAddress ip;
    private int port;

    public ServerSendThread(DatagramSocket socket, InetAddress ip_, int port_) throws Exception{
        ServerSocket = socket;
        ip = ip_;
        port = port_;
    }

    public void run(){
        try{
            while(true){
                toClientText = new BufferedReader(new InputStreamReader(System.in));
                String text = toClientText.readLine();
                sendData = text.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
                ServerSocket.send(sendPacket);

                if(text.equals("@quit")){
                    ServerSocket.close();
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


}
