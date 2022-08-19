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
    private InetAddress ip;
    private int port;
    private boolean ClientSocketState = true;
    private boolean stop = false;

    public ServerSendThread(DatagramSocket socket, InetAddress ip_, int port_) throws Exception{
        ServerSocket = socket;
        ip = ip_;
        port = port_;
    }

    public void run(){
        try{
            while(ClientSocketState){
                toClientText = new BufferedReader(new InputStreamReader(System.in));
                String text = toClientText.readLine();
                byte[] sendData = new byte[1024];
                sendData = text.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
                ServerSocket.send(sendPacket);

                if(text.equals("@quit")){
                    stop = true;
                    ServerSocket.close();
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void setClientSocketState(boolean state){
        ClientSocketState = state;
    }

}
