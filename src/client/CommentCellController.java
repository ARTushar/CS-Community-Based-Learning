package client;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import util.NetworkUtil;

public class CommentCellController {
    private String id;
    NetworkUtil util;
    TutorialPageController tutorialPageController;

    @FXML
    protected JFXTextArea commentText;

    @FXML
    protected Text name;

    @FXML
    private JFXButton replyButton;

    @FXML
    private TextField replyText;

    @FXML
    void handleReplyButton(ActionEvent event) {
        String reply = id+":"+replyText.getText();
        new SendThread(util, reply);
        tutorialPageController.showComments();
    }

    public void load(NetworkUtil util, TutorialPageController tutorialPageController) {
        this.util = util;
        this.tutorialPageController = tutorialPageController;
    }

    public void setId(String id) {
        this.id = id;
    }
}
