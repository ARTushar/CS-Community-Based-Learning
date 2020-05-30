package util;

import java.io.Serializable;

public class Answer implements Serializable {
    private String questionID;
    private String answer;
    private Integer like = 0;
    private String date;
    private String postedBy;

    public Answer(String answer, String date, String postedBy) {
        this.answer = answer;
        this.date = date;
        this.postedBy = postedBy;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public void incrementLike() {
        like++;
    }

    public void decrementLike() {
        like--;
    }

    public Integer getLike() {
        return like;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public void setLike(int like) {
        this.like = like;
    }

    /*@Override
    public int compareTo(Object o) {
        Answer answer = (Answer) o;
        return this.like.compareTo(answer.like);
    }*/
}
