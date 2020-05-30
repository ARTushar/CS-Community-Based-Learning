package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

public class Question implements Serializable {
    private String questionID;
    private String userID;
    private String title;
    private String message;
    private LinkedList<Answer> answer = new LinkedList<>();
    private int likeNumber = 0;
    private String date;
    private String postedBy;

    public Question(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void incrementLike() {
        likeNumber++;
    }

    public void decrementLike() {
        likeNumber--;
    }

    public String getLikeNumber() {
        return String.valueOf(likeNumber);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public LinkedList<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(LinkedList<Answer> answer) {
        this.answer = answer;
    }

    public String getAnswerNumber() {
        return String.valueOf(answer.size());
    }

    @Override
    public String toString() {
        return "Title : " + title + "\nMessage : " + message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, message);
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }
}
