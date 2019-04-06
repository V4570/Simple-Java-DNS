
public class DNSProtocol {

    private DNSHashMap hashMapInstance;

    public DNSProtocol(){
        hashMapInstance = DNSHashMap.getHashMap();
    }

    public synchronized String processRequest(String inputLine) {

        String input[] = inputLine.split(",");
        String action = input[0];
        int ip;
        String hostname;
        String outmsg;

        switch (action) {
            case "N":
                //search by hostname
                hostname = input[1];
                int record = searchByHostname(hostname);
                if(record == 0) outmsg = ColoredCli.RED_BOLD + "ERR" + ColoredCli.RESET;
                else outmsg = ColoredCli.GREEN_BOLD + DNSUtils.ipToString(record) + ColoredCli.RESET;
                break;
            case "A":
                //search by IP
                ip = DNSUtils.ipToInt(input[1]);
                String record_h = searchByIp(ip);
                if(record_h == null) outmsg = ColoredCli.RED_BOLD + "ERR" + ColoredCli.RESET;
                else outmsg = ColoredCli.GREEN_BOLD + record_h + ColoredCli.RESET;
                break;
            case "I":
                //insert ip hostname pair
                ip = DNSUtils.ipToInt(input[1]);
                hostname = input[2];
                if(insertRecord(ip, hostname)) outmsg = ColoredCli.GREEN_BOLD + "OK" + ColoredCli.RESET;
                else outmsg = ColoredCli.RED_BOLD + "ERR" + ColoredCli.RESET;
                break;
            case "D":
                //delete ip hostname pair
                ip = DNSUtils.ipToInt(input[1]);
                hostname = input[2];
                if(removeRecord(ip, hostname)) outmsg = ColoredCli.GREEN_BOLD + "OK" + ColoredCli.RESET;
                else outmsg = ColoredCli.RED_BOLD + "ERR" + ColoredCli.RESET;
                break;
            case "U":
                //update ip hostname pair
                ip = DNSUtils.ipToInt(input[1]);
                hostname = input[2];
                if(updateRecord(ip, hostname)) outmsg = ColoredCli.GREEN_BOLD + "OK" + ColoredCli.RESET;
                else outmsg = ColoredCli.RED_BOLD + "ERR" + ColoredCli.RESET;
                break;
            default:
                outmsg = ColoredCli.RED_BACKGROUND + ColoredCli.WHITE_BOLD + "Bad Request!" + ColoredCli.RESET;
                break;
        }

        return outmsg;
    }

    private synchronized boolean insertRecord(int ip, String hostname){

        return this.hashMapInstance.add(ip, hostname);
    }

    private synchronized boolean removeRecord(int ip, String hostname){

        return this.hashMapInstance.remove(ip, hostname);
    }

    private synchronized boolean updateRecord(int ip, String hostname){

        return this.hashMapInstance.updateRecord(ip, hostname);
    }

    private synchronized String searchByIp(int ip){

        return this.hashMapInstance.getHostname(ip);
    }

    private synchronized int searchByHostname(String hostname){

        return this.hashMapInstance.getIP(hostname);
    }
}
