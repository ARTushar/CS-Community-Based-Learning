package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import util.NetworkUtil;
import util.Question;

import java.io.IOException;

public class ListCellController {

    private NetworkUtil util;
    private ShowGui main;
    private HomePageController homePageController;
    Question thisQuestion;

    @FXML
    protected Label voteNumber;

    @FXML
    protected Label answerNumber;


    @FXML
    protected Label time;

    @FXML
    protected Label name;

    @FXML
    protected Label question;

    @FXML
    void questionDetails(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/questionDetails.fxml"));
        Node questionScene = null;
        try {
            questionScene = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        QuestionDetailsController questionController = loader.getController();
        questionController.load(util,homePageController,thisQuestion);
        homePageController.mainLayout.setCenter(questionScene);

    }

    public void load(ShowGui main, NetworkUtil util, HomePageController homePageController)
    {
        this.main = main;
        this.util= util;
        this.homePageController  = homePageController;
    }

}
