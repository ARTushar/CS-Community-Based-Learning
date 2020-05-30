package util;

import java.io.Serializable;
import java.util.ArrayList;

public class CommentList implements Serializable {
    public ArrayList <Comment> commentsList;

    public CommentList(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    public ArrayList<Comment> getCommentsList() {
        return commentsList;
    }
}
