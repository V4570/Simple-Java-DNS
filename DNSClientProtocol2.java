import java.util.Random;

public class DNSClientProtocol2 {

    public String prepareRequestIP(){
        Random r = new Random();
        String std = "10.10.10.";
        return std + Integer.toString(r.nextInt(20));
    }

    public String prepareRequestHostname(){
        Random r = new Random();
        String std_h = "server.uom.vt";
        return std_h + Integer.toString(r.nextInt(20));
    }

    public void processReply(String input){
        System.out.println("Reply: " + input);
    }
}
