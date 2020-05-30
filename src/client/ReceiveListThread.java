package client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import util.*;

import java.util.LinkedList;

public class ReceiveListThread implements Runnable {
    private Thread thread;
    private HomePageController homePageController;
    private CPPTutorialController cppTutorialController;
    private JavaTutorialController javaTutorialController;
    private Python3TutorialController python3TutorialController;
    private TutorialPageController tutorialPageController;
    private String tutorial;
    private NetworkUtil util;
    private boolean threadAliveFlag = true;
    private ClientState clientState;


    public ReceiveListThread(HomePageController homePageController, NetworkUtil util) {
        this.homePageController = homePageController;
        this.util = util;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (threadAliveFlag) {
            Object o = util.read();
            if( o instanceof ClientState)
            {
                clientState = (ClientState) o;
            }
            if(clientState == ClientState.RECEIVE_DISCUSSION_LIST) {
                Object object = util.read();
                if (object instanceof QuestionList) {
                    QuestionList questionList = (QuestionList) object;
                    System.out.println("Successfully received the questionList :D");
                    homePageController.questionLinkedList = questionList.questionArrayList;
                    homePageController.questions = FXCollections.observableArrayList(homePageController.questionLinkedList);

                    Platform.runLater(() ->
                    {

                        homePageController.questionListView.setItems(homePageController.questions);
                    });
                }

            }
            else if(clientState == ClientState.QUESTION_LIST_INITIALISE){
                Object object = util.read();
                if(object instanceof LinkedList)
                {
                    homePageController.questionLinkedList = (LinkedList<Question>) object;
                    homePageController.questions = FXCollections.observableArrayList(homePageController.questionLinkedList);
                    Platform.runLater(() ->
                    {

                        homePageController.questionListView.setItems(homePageController.questions);
                    });
                }
            }
            else if(clientState == ClientState.RECEIVE_TUTORIAL){

                System.out.println("receiving tutorial");
                tutorial = RecieveFIle.recieveFile(util);
                //System.out.println(tutorial);
                Platform.runLater(()->{
                    tutorialPageController.webView.getEngine().loadContent(tutorial);
                });
            }
            else if(clientState == ClientState.RECEIVE_COMMENT_LIST){
                Object object = util.read();

                if(object instanceof CommentList){
                    CommentList commentList = (CommentList) object;
                    System.out.println("comment list"+commentList);
                    if(commentList != null) {
                        tutorialPageController.commentArrayList =  commentList.commentsList;
                        if(tutorialPageController.commentArrayList != null) {
                            tutorialPageController.comments = FXCollections.observableList(tutorialPageController.commentArrayList);
                            Platform.runLater(()->
                            {
                                tutorialPageController.commentList.setItems(FXCollections.observableArrayList(tutorialPageController.comments));
                            });
                        }

                    }
                } else if(o instanceof String){
                    System.out.println("Stopping comment update thread...");
                    threadAliveFlag = false;
                }
            }

            else if (clientState == ClientState.CLOSE_CONNECTION) {
                System.out.println("Stopping  update thread...");
                util.write(clientState);
                util.closeConnection();
                threadAliveFlag = false;
            }
        }
    }

    public void setCppTutorialController(CPPTutorialController cppTutorialController) {
        this.cppTutorialController = cppTutorialController;
    }

    public void setJavaTutorialController(JavaTutorialController javaTutorialController) {
        this.javaTutorialController = javaTutorialController;
    }

    public void setPython3TutorialController(Python3TutorialController python3TutorialController) {
        this.python3TutorialController = python3TutorialController;
    }

    public void setTutorialPageController(TutorialPageController tutorialPageController) {
        this.tutorialPageController = tutorialPageController;
    }
}
