package server;

import util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class SendLists {
    private static  int i = 0;
    static void sendQuestionList(LinkedList<Question> questionArrayList, ArrayList<String> connectedUsersKey, HashMap<String, NetworkUtil> clientList)
    {
        Iterator iterator = connectedUsersKey.iterator();
        QuestionList questionList = new QuestionList(questionArrayList);

        while(iterator.hasNext()){
            String key = (String) iterator.next();
            //System.out.println("key: "+key);
            NetworkUtil util = clientList.get(key);
            //System.out.println(key + "  Util : "+ util);
            util.write(ClientState.RECEIVE_DISCUSSION_LIST);
            util.resetObjecttOutputStream();
            util.write(questionList);
        }
        System.out.println("Successfully sent the questionList to all the clients ");
    }

    static void sendCommentList(ArrayList<Comment> commentArrayList, ArrayList<String> connectedUsersKey, HashMap<String, NetworkUtil> clientList)
    {
        Iterator iterator = connectedUsersKey.iterator();
        CommentList commentList = new CommentList(commentArrayList);

        while(iterator.hasNext()){
            String key = (String) iterator.next();
            NetworkUtil util = clientList.get(key);
            util.resetObjecttOutputStream();
            util.write(ClientState.RECEIVE_COMMENT_LIST);
            util.write(commentList);

        }
    }
    static void sendCommentsList(ArrayList<Comment> commentArrayList, ArrayList<NetworkUtil> connectedUsers)
    {
        Iterator iterator = connectedUsers.iterator();
        CommentList commentList = new CommentList(commentArrayList);

        while(iterator.hasNext()){
            NetworkUtil util = (NetworkUtil) iterator.next();
            util.resetObjecttOutputStream();
            util.write(ClientState.RECEIVE_COMMENT_LIST);
            util.write(commentList);
        }
    }
}
