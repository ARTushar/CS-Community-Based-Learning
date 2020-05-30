package client;

import util.Answer;
import util.ClientState;
import util.NetworkUtil;
import util.Question;

public class SendThread implements Runnable {
    private NetworkUtil util;
    private Question question;
    private ClientState clientState;
    private Answer answer;
    private String commentOrReply;

    public SendThread(NetworkUtil util, Question question, ClientState clientState) {
        this.util = util;
        this.question = question;
        this.clientState = clientState;
        Thread thread = new Thread(this);
        thread.start();
    }
    public SendThread(NetworkUtil util, Answer answer, ClientState clientState) {
        this.util = util;
        this.answer = answer;
        this.clientState = clientState;
        new Thread(this).start();
    }

    public SendThread(NetworkUtil util,ClientState clientState) {
        this.util = util;
        this.clientState = clientState;
        new Thread(this).start();
    }
    public SendThread(NetworkUtil util,String s) {
        this.util = util;
        this.commentOrReply = s;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (clientState == ClientState.RECEIVE_QUESTION || clientState == ClientState.UPDATE_QUESTION) {
            util.write(clientState);
            util.resetObjecttOutputStream();
            util.write(question);
            clientState = null;
        }else if(clientState == ClientState.RECEIVE_ANSWER) {
            util.write(ClientState.RECEIVE_ANSWER);
            util.write(answer);
            clientState = null;
        }else if(clientState!=null){

            util.write(clientState);
            clientState = null;
        }else {
            util.write(commentOrReply);
        }

    }
}
