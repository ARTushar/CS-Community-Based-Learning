package util;

import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Comment implements Serializable{
    private String id;
    private UserInfo commenterInfo;
    private String comment;
    private ArrayList <Pair<String, String>> replies;

    public Comment(UserInfo commenterInfo, String comment) {
        this.commenterInfo = commenterInfo;
        this.comment = comment;
        replies = new ArrayList<>();
    }

    public Comment(UserInfo commenterInfo, String comment, ArrayList<Pair<String, String>> replies) {
        this.commenterInfo = commenterInfo;
        this.comment = comment;
        this.replies = replies;
    }

    public String getId() {
        return id;
    }

    public UserInfo getCommenterInfo() {
        return commenterInfo;
    }

    public String getCommenterName() { return commenterInfo.getUpName();}

    public String getComment() {
        return comment;
    }

    public ArrayList<Pair<String, String>> getReplies() {
        return replies;
    }

    public void addReply(String reply, String replierName){
        replies.add(new Pair(replierName, reply));
    }

    public void getRepliesInString(){
        String str;
        Iterator<Pair<String, String>> itr = replies.iterator();
        while (itr.hasNext()){
            Pair<String, String> p = itr.next();

        }
    }

    public void setId(String id) {
        this.id = id;
    }
}
