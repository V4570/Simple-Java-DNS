import java.io.*;
import java.net.Socket;

public class DNSThread extends Thread{

    private Socket dataScocket;
    private InputStream inStream;
    private BufferedReader in;
    private OutputStream outStream;
    private PrintWriter out;

    public DNSThread(Socket socket){

        dataScocket = socket;
        try{

            inStream = dataScocket.getInputStream();
            in = new BufferedReader(new InputStreamReader(inStream));
            outStream = dataScocket.getOutputStream();
            out = new PrintWriter(outStream, true);
        }
        catch (IOException e){
            System.out.println(ColoredCli.RED_BOLD + "I/O Error" + e + ColoredCli.RESET);
        }
    }

    public void run(){

        String outmsg;
        DNSProtocol app = new DNSProtocol();
        try{
            do{
                outmsg = app.processRequest(in.readLine());
                out.println(outmsg);
            }while (true);
        }
        catch (IOException e){
            System.out.println(ColoredCli.RED_BOLD + "I/O Error" + e + ColoredCli.RESET);
        }
    }
}
