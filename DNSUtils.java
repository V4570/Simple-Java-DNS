import java.util.ArrayList;

public class DNSUtils {

    public synchronized static String ipToString(int ipv4){
        StringBuilder sb = new StringBuilder();
        int temp = (ipv4 >> 24) & 255;

        sb.append(temp);
        temp = (ipv4 >> 16) & 255;
        sb.append(".").append(temp);
        temp = (ipv4 >> 8) & 255;
        sb.append(".").append(temp);
        temp = ipv4 & 255;
        sb.append(".").append(temp);

        return sb.toString();
    }

    public synchronized static int ipToInt(String ipv4){

        ArrayList<Integer> part = new ArrayList<>();

        for(String s : ipv4.split("\\.")){
            part.add(Integer.parseInt(s));
        }

        return (part.get(0) << 24) + (part.get(1) << 16) + (part.get(2) << 8) + part.get(3);
    }
}
