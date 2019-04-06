import java.util.HashMap;

public class DNSHashMap {

    private static volatile HashMap<Integer, String> dns_table;
    private static volatile HashMap<String, Integer> dns_table_r;
    public static volatile DNSHashMap singleton;

    private DNSHashMap(){
        dns_table = new HashMap<>();
        dns_table_r = new HashMap<>();
    }

    public static synchronized DNSHashMap getHashMap(){
        if(singleton == null) singleton = new DNSHashMap();
        return singleton;
    }

    public synchronized boolean add(int key, String value){

        String old = dns_table.get(key);

        try{
            int ip = dns_table_r.get(value);
            return false;
        }catch (NullPointerException ne){ /*empty*/ }


        if(old != null) return false;
        old = value;
        dns_table.put(key, old);
        dns_table_r.put(old, key);
        return true;
    }

    public synchronized boolean remove(int key, String value){

        String old = dns_table.get(key);

        if(old == null) return false;
        else{
            dns_table.remove(key);
            dns_table_r.remove(value);
            return true;
        }
    }

    public synchronized String getHostname(int ip){
        return dns_table.get(ip);
    }

    public synchronized int getIP(String hostname){
        try{
            int ip = dns_table_r.get(hostname);
            return ip;
        }catch (NullPointerException ne){
            return 0;
        }
    }

    public synchronized boolean updateRecord(int ip, String hostname){
        return dns_table.get(ip) != null;
    }
}
