package client;

import util.ClientState;
import util.NetworkUtil;

public class StopThread implements Runnable {
    private NetworkUtil util;
    private ClientState clientState;

    public StopThread(NetworkUtil util, ClientState clientState) {
        this.util = util;
        this.clientState = clientState;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        util.write(clientState);
        if(clientState == ClientState.CLOSE_CONNECTION)
            util.closeConnection();
    }
}
