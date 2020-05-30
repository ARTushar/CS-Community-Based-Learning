package server;

import util.NetworkUtil;

public class Serve implements Runnable {

    private NetworkUtil util;
    public Serve(NetworkUtil util)
    {
        this.util = util;
    }

    @Override
    public void run() {
        while (true) {
        }
    }
}
