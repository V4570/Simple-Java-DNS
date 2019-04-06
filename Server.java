import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 1337;

    public static void main(String args[]) throws IOException{

        ServerSocket connectionSocket = new ServerSocket(PORT);

        System.out.println(ColoredCli.GREEN_BOLD + "Server is listening to port: " +
                ColoredCli.CYAN_BOLD + PORT + ColoredCli.RESET);

        while (true){

            Socket dataSocket = connectionSocket.accept();
            DNSThread dnsThread = new DNSThread(dataSocket);
            dnsThread.start();
        }
    }
}
