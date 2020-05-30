package client;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import util.ClientState;
import util.NetworkUtil;
import util.Question;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewQuestionController extends Region{

    private Question question;
    private NetworkUtil util;
    private ShowGui main;
    private HomePageController controller;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXTextArea title;

    @FXML
    private JFXTextArea message;

    @FXML
    void cancelQuestion(ActionEvent event) {
        title.setText("");
        message.setText("");

    }

    @FXML
    void postQuestion(ActionEvent event) {
        question = new Question(title.getText(),message.getText());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
        String date = now.format(formatter);
        question.setDate(date);
        new SendThread(util,question, ClientState.RECEIVE_QUESTION);
        title.setText(null);
        message.setText(null);
        controller.mainLayout.setCenter(controller.tabPane);
    }

    public void load(ShowGui main, NetworkUtil util, HomePageController controller)
    {
        this.main = main;
        this.util = util;
        this.controller = controller;
    }
    @FXML
    void goBack(ActionEvent event) {
        controller.mainLayout.setCenter(controller.tabPane);
    }

}
