package client;

import util.NetworkUtil;

import java.net.Socket;

public class Client{
    private NetworkUtil networkUtil;
    private Socket socket;
    private String ipAddress = "localhost";
    private int port = 7777;
    private boolean threadAliveFlag = true;

    public Client()
    {
        networkUtil = new NetworkUtil(ipAddress,port);
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }


}
