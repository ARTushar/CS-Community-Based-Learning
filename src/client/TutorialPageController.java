package client;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.util.Pair;
import util.ClientState;
import util.Comment;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TutorialPageController {
    ShowGui main;
    NetworkUtil util;
    protected HomePageController homePageController;
    protected ObservableList<Comment> comments;
    protected ArrayList<Comment> commentArrayList;
    private ClientState clientState;

    @FXML
    WebView webView;


    @FXML
    protected ListView<Comment> commentList;

    @FXML
    private JFXButton backButton;

    @FXML
    private TextField writeComment;

    @FXML
    private JFXButton commentButton;



    @FXML
    void backButtonHandle(ActionEvent event) {
        try {
            new SendThread(util, ClientState.BACK_TO_HOMEPAGE);
            if(ShowGui.clientState == ClientState.CPP) {
                main.showCppTutorial(homePageController, util);
            }else if(ShowGui.clientState == ClientState.JAVA) {
                main.showJavaTutorial(homePageController, util);
            }else if(ShowGui.clientState == ClientState.PYTHON){
                main.showPythonTutorial(homePageController, util);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void commentButtonHandle(ActionEvent event) {
        String str = writeComment.getText();
        String comment = "comment:"+str;
        new SendThread(util, comment);
        writeComment.setText("");
    }

    public void load(ShowGui showGui, NetworkUtil util, HomePageController homePageController) {
        this.main = showGui;
        this.util = util;
        this.homePageController = homePageController;
        commentArrayList = new ArrayList<>();
        HomePageController.receiveListThread.setTutorialPageController(this);

        showComments();
    }

    void showComments()
    {
        commentList.refresh();
        commentList.setItems(comments);
        commentList.setCellFactory(list-> new ListCell<Comment>() {
            Node root ;
            CommentCellController commentCellController ;

            {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/commentCell.fxml"));

                    root = loader.load();

                    commentCellController = loader.getController();
                    commentCellController.load(util, TutorialPageController.this);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            protected void updateItem(Comment item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    commentCellController.setId(item.getId());
                    commentCellController.name.setText(item.getCommenterName());
                    commentCellController.commentText.setText("Comment: "+item.getComment()+"\nReplies:\n\t");
                    Iterator<Pair<String, String>> itr = item.getReplies().iterator();
                    while (itr.hasNext()){
                        Pair<String, String> p = itr.next();
                        commentCellController.commentText.setText(commentCellController.commentText.getText()+p.getKey()+":"+p.getValue()+"\n\t");
                    }

                    commentCellController.commentText.setEditable(false);

                    setGraphic(root);
                }
            }
        });
    }
}
