package client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import util.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class QuestionDetailsController {
    private NetworkUtil util;
    private HomePageController homePageController;
    private Question thisQuestion;
    private LinkedList<Answer> answerLinkedList;
    private ObservableList<Answer> observableList;
    private int answerNumber = 0;
    ArrayList<Integer> likes;


    @FXML
    private JFXTextArea questionDescription;

    @FXML
    private JFXTextArea questionTitle;

    @FXML
    private Label voteNo;

    @FXML
    private Label postedBy;

    @FXML
    private Label date;

    @FXML
    private Label answerNo;

    @FXML
    private JFXTextArea replyText;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton postButton;

    @FXML
    private JFXListView<Answer> answersList;

    @FXML
    private JFXButton backButton;

    @FXML
    void cancelAnswer(ActionEvent event) {
        replyText.setText("");
    }

    @FXML
    void goBack(ActionEvent event) {
        sortAnswer(answerLinkedList);
        new SendThread(util, thisQuestion, ClientState.UPDATE_QUESTION);
        homePageController.mainLayout.setCenter(homePageController.tabPane);
    }

    @FXML
    void postAnswer(ActionEvent event) {
        String text = replyText.getText();
        replyText.setText("");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
        String date = now.format(formatter);
        Answer answer = new Answer(text, date, util.getUserName());
        answerLinkedList.addFirst(answer);
        answer.setQuestionID(thisQuestion.getQuestionID());
        new SendThread(util, answer, ClientState.RECEIVE_ANSWER);
    }

    @FXML
    void incrementVote(ActionEvent event) {
        if (likes.get(0) == 0 || likes.get(0) == -1) {
            thisQuestion.incrementLike();
            if (likes.get(0) == 0)
                likes.set(0, 1);
            else
                likes.set(0, 0);
            voteNo.setText(thisQuestion.getLikeNumber());
            sortAnswer(answerLinkedList);
        }
    }

    @FXML
    void decrementVote(ActionEvent event) {
        if (likes.get(0) == 0 || likes.get(0) == 1) {
            thisQuestion.decrementLike();
            if (likes.get(0) == 0)
                likes.set(0, -1);
            else
                likes.set(0, 0);
            voteNo.setText(thisQuestion.getLikeNumber());
            sortAnswer(answerLinkedList);
        }
    }

    private void sortAnswer(LinkedList<Answer> answerLinkedList) {
        Collections.sort(answerLinkedList, new AnswerClassComparator());
        Collections.reverse(answerLinkedList);
    }

    public void load(NetworkUtil util, HomePageController homePageController, Question thisQuestion) {
        this.util = util;
        this.homePageController = homePageController;
        this.thisQuestion = thisQuestion;
        if (homePageController.likesTrack.containsKey(thisQuestion.getQuestionID())) {
            likes = homePageController.likesTrack.get(thisQuestion.getQuestionID());
        } else {

            likes = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                likes.add(0);
            }
            homePageController.likesTrack.put(thisQuestion.getQuestionID(), likes);
        }
        answerLinkedList = thisQuestion.getAnswer();
        observableList = FXCollections.observableList(answerLinkedList);
        answerOnList();
        questionDescription.setEditable(false);
        questionTitle.setEditable(false);
        questionTitle.setText(thisQuestion.getTitle());
        questionDescription.setText(thisQuestion.getMessage());
        voteNo.setText(thisQuestion.getLikeNumber());
        postedBy.setText(postedBy.getText() + thisQuestion.getPostedBy());
        date.setText(thisQuestion.getDate());
        answerNo.setText(thisQuestion.getAnswerNumber() + answerNo.getText());

    }

    void answerOnList() {
        answersList.setItems(observableList);
        answersList.setCellFactory(list -> new ListCell<Answer>() {
            Node root;
            AnswerCellController cellController;

            {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/answerCell.fxml"));
                    root = loader.load();
                    cellController = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Answer item, boolean empty) {
                //don't do anything here
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    //use your logic as your needs on the the new list cell
                    cellController.answerText.setText(item.getAnswer());
                    cellController.date.setText(item.getDate());
                    cellController.postedBy.setText(cellController.postedBy.getText() + item.getPostedBy());
                    cellController.voteNo.setText(String.valueOf(item.getLike()));
                    cellController.load(item,likes,answerNumber++);
                    setGraphic(root);
                }
            }
        });
    }

}
