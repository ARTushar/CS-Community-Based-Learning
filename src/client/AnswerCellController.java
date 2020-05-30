package client;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.Answer;

import java.util.ArrayList;

public class AnswerCellController {

    Answer answer;
    private Integer number;
    private ArrayList<Integer> likes;


    @FXML
    protected JFXTextArea answerText;

    @FXML
    protected Label voteNo;

    @FXML
    protected Label postedBy;

    @FXML
    protected Label date;

    @FXML
    void incrementVote(ActionEvent event) {
        if (likes.get(number) == 0 || likes.get(number) == -1) {
            answer.incrementLike();
            if (likes.get(number) == 0)
                likes.set(number,1);
            else
                likes.set(number,0);
            voteNo.setText(String.valueOf(answer.getLike()));
        }

    }

    @FXML
    void decrementVote(ActionEvent event) {

        if (likes.get(number) == 0 || likes.get(number) == 1) {
            answer.decrementLike();
            if (likes.get(number) == 0)
                likes.set(number,-1);
            else
                likes.set(number,0);
            voteNo.setText(String.valueOf(answer.getLike()));
        }

    }

    public void load(Answer answer, ArrayList<Integer> likes,Integer number) {
        this.answer = answer;
        this.likes = likes;
        this.number = number;
        answerText.setEditable(false);
    }

}
