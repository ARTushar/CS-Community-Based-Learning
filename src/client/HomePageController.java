
package client;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.ClientState;
import util.NetworkUtil;
import util.Question;
import util.UserInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class HomePageController {

    private ShowGui main;
    private NetworkUtil util;
    static ReceiveListThread receiveListThread;
    public NetworkUtil getUtil() {
        return util;
    }
    private UserInfo userInfo;
    LinkedList<Question> questionLinkedList;
    ObservableList<Question> questions;
    HashMap<String,ArrayList<Integer>> likesTrack;
    private HomePageController homePageController = this;


    public void load(ShowGui main, NetworkUtil util, UserInfo userInfo) {
        this.main = main;
        this.util = util;
        this.userInfo = userInfo;
        likesTrack = userInfo.getLikesTrack();
        userName.setText(util.getUserName());
        email.setText(userInfo.getUpEmail());
        questionLinkedList = new LinkedList<>();
        questionOnList();
        receiveListThread = new ReceiveListThread(this,util);
        new InitialiseDiscussionListThread(util, questionLinkedList, homePageController);
        questionListView.refresh();
    }

    @FXML
    public BorderPane mainLayout;

    @FXML
    protected TabPane tabPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public Tab learn;

    @FXML
    private Button cpp;

    @FXML
    private Button java;

    @FXML
    private Label userName;

    @FXML
    private Label email;

    @FXML
    protected ListView questionListView;

    @FXML
    private Button python;

    @FXML
    private Button csharp;

    @FXML
    protected Tab discussions;

    @FXML
    protected Tab notifications;

    @FXML
    protected Tab profile;

    @FXML
    private Button newQuestion;


    @FXML
    private ImageView myImage;

    @FXML
    void imageDragOver(DragEvent event) {
        Dragboard board = event.getDragboard();
        if (board.hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void imageDropped(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        List<File> file = dragboard.getFiles();
        try {
            FileInputStream fis = new FileInputStream(file.get(0));
            Image image = new Image(fis);
            myImage.setImage(image);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void openCPP(ActionEvent event) {
        try {
            main.showCppTutorial(this, util);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openCsharp(ActionEvent event) {
        try {
            main.showCSharpTutorial(this, util);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openJava(ActionEvent event) {
        try {
            main.showJavaTutorial(this, util);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openPython(ActionEvent event) {
        try {
            main.showPythonTutorial(this, util);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void signOut(ActionEvent event) {
        try {
            new StopThread(util, ClientState.STOP_THREAD);
            main.showStartUp();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void loadCPP() {
    }

    @FXML
    void openNewQuestion(ActionEvent event) {
        try {
            main.showNewDiscussion(this, util);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void changeListView(ListView<Question> questionListView, ObservableList<Question> questions) {
        questionListView.setItems(questions);
        questionListView.setCellFactory((list) ->
        {
            return new ListCell<Question>() {

                Label vote = new Label("Votes :");
                Label question = new Label();
                Label answer = new Label("Answers");
                Label voteNumber = new Label();
                Label answerNumber = new Label();
                Label name = new Label();
                Label time = new Label();
                VBox v1 = new VBox(voteNumber, vote, answerNumber, answer);
                HBox hBox = new HBox(v1, question);
                VBox vBox = new VBox(hBox, name, time);

                @Override
                protected void updateItem(Question item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        //setText("\t"+item.getTitle()+"\n\n\t"+item.getMessage());
                        question.setText(item.getTitle());
                        //label2.setText(item.getMessage());
                        vBox.setSpacing(10);
                        hBox.setSpacing(50);
                        vBox.setPadding(new Insets(10, 10, 10, 10));
                        setPadding(new Insets(10, 10, 50, 10));
                        setGraphic(vBox);
                    }
                }


            };
        });
        questionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
            System.out.println("ListView Selection Changed (selected: \" + newValue.toString() + \")");
        });
    }

    void questionOnList() {
        questionListView.setItems(questions);
        questionListView.setCellFactory(list -> new ListCell<Question>() {
            Node root;
            ListCellController cellController;

            {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/listCell.fxml"));

                    root = loader.load();
                    cellController = loader.getController();
                    cellController.load(main, util, homePageController);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Question item, boolean empty) {
                //don't do anything here
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    //use your logic as your needs on the the new list cell
                    cellController.question.setText(item.getTitle());
                    cellController.time.setText(item.getDate());
                    cellController.name.setText(item.getPostedBy());
                    cellController.voteNumber.setText(item.getLikeNumber());
                    cellController.answerNumber.setText(item.getAnswerNumber());
                    cellController.thisQuestion = item;
                    setGraphic(root);
                }
            }
        });
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
