import java.io.*;
import java.net.Socket;
import java.util.Random;

public class Client2 {

    private static final String HOST = "localhost";
    private static final int PORT = 1337;

    public static void main(String args[]) throws IOException{

        Socket dataSocket = new Socket(HOST, PORT);

        InputStream inStream = dataSocket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = dataSocket.getOutputStream();
        PrintWriter out = new PrintWriter(outStream, true);

        String inmsg, outmsg;
        DNSClientProtocol2 app = new DNSClientProtocol2();

        do{
            if(new Random().nextInt(2) == 0){
                outmsg = "N," + app.prepareRequestHostname();
            }
            else{
                outmsg = "A," + app.prepareRequestIP();
            }
            System.out.println("Request: " + outmsg);
            out.println(outmsg);
            inmsg = in.readLine();
            app.processReply(inmsg);
            try{
                Thread.sleep(500);
            }
            catch (InterruptedException e){/*blank*/}
        }while(true);
    }
}
