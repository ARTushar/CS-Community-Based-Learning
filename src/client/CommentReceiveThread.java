package client;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import util.CommentList;

public class CommentReceiveThread implements Runnable {
    String tutorial;
    boolean threadAliveFlag = true;
    Thread thread;
    TutorialPageController tutorialPageController;

    @Override
    public void run() {
        tutorial = RecieveFIle.recieveFile(tutorialPageController.util);
        //System.out.println(tutorial);
        Platform.runLater(()->{
            tutorialPageController.webView.getEngine().loadContent(tutorial);
        });

        while (threadAliveFlag){
            Object o = tutorialPageController.util.read();

            if(o instanceof CommentList){
                CommentList commentList = (CommentList) o;
                if(commentList != null) {
                    tutorialPageController.commentArrayList =  commentList.commentsList;
                    //System.out.println("size of comment arraylist in thread: " + tutorialPageController.commentArrayList.size());
                    tutorialPageController.comments = FXCollections.observableList(tutorialPageController.commentArrayList);

                    Platform.runLater(()->
                    {
                        tutorialPageController.commentList.setItems(FXCollections.observableArrayList(tutorialPageController.comments));
                    });
                }
            } else if(o instanceof String){
                System.out.println("Stopping comment update thread...");
                threadAliveFlag = false;
            }
        }
    }

    public CommentReceiveThread(TutorialPageController tutorialPageController) {
        this.tutorialPageController = tutorialPageController;
        thread= new Thread(this);
        thread.start();
    }

}
