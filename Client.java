import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 1337;

    public static void main(String args[]) throws IOException{

        Socket dataSocket = new Socket(HOST, PORT);

        InputStream inStream = dataSocket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = dataSocket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream, true);

        String inmsg, outmsg = null;
        DNSClientProtocol app = new DNSClientProtocol();

        do{
            switch (new Random().nextInt(4)){
                case 0:
                    outmsg = "D," + app.prepareRequest();
                    break;
                case 1:
                    outmsg = "I," + app.prepareRequest();
                    break;
                case 2:
                    outmsg = "I," + app.prepareRequest();
                    break;
                case 3:
                    outmsg = "U," + app.prepareRequest();
                    break;
            }
            System.out.println("Request: " + outmsg);
            out.println(outmsg);
            inmsg = in.readLine();
            app.processReply(inmsg);
            try{
                Thread.sleep(1500);
            }
            catch (InterruptedException e){/*blank*/}
        }while(true);
    }
}
