package client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import util.ClientState;
import util.NetworkUtil;
import util.Question;

import java.util.LinkedList;

public class InitialiseDiscussionListThread implements Runnable {

    private LinkedList<Question> questionLinkedList;
    private NetworkUtil util;
    private HomePageController homePageController;

    public InitialiseDiscussionListThread(NetworkUtil util, LinkedList<Question> questionLinkedList, HomePageController homePageController)
    {

        this.questionLinkedList = questionLinkedList;
        this.util = util;
        this.homePageController = homePageController;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("In the initialisation thread ");
        util.write(ClientState.QUESTION_LIST_INITIALISE);
    }
}
