import java.util.Random;

public class DNSClientProtocol {

    public String prepareRequest(){
        Random r = new Random();
        String std = "10.10.10.";
        String std_h = "server.uom.vt";
        String id = Integer.toString(r.nextInt(20));
        return std + id + "," + std_h + id;
    }

    public void processReply(String theInput){

        System.out.println("Reply: " + theInput);
    }
}
