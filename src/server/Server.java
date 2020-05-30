package server;

import util.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private final int PORT = 7777;
    private HashMap<String, NetworkUtil> clientList;
    private ArrayList<UserInfo> userInfoList;
    private LinkedList<Question> questionsList;
    private ArrayList<String> connectedUsersKey;
    protected static int totalClient = 0;
    protected static int currentClient = 0;
    static int [] commentNumberArray;
    protected static HashMap<ClientState, ArrayList<Comment>> commentsList;
    protected static HashMap<ClientState, ArrayList<NetworkUtil>> clientStatesList;
    public static final String QUESTION_LIST_FILE = "src\\files\\questionArrayList";
    public static final String USER_INFO_FILE ="src\\files\\usersList";


    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientList = new HashMap<>();
        connectedUsersKey = new ArrayList<>();
        ClientState[] states = ClientState.values();

        commentNumberArray = ReadWriteObjects.readCommentArray("src\\files\\comment.txt" );
        if(commentNumberArray == null){
            commentNumberArray = new int[50];
        }
        commentsList = ReadWriteObjects.readComments("src\\files\\comments.txt");

        if(commentsList == null){
            commentsList  = new HashMap();
            for(ClientState c: states){
                commentsList.put(c, new ArrayList<>());
            }
        }

        clientStatesList = new HashMap();
        for (ClientState c : states) {
            clientStatesList.put(c, new ArrayList<>());
        }


        //userInfoList = ReadFile.readUserInfo("src\\files\\ClientInformation.txt", userInfoList);
        userInfoList = ReadWriteObjects.readUserInfoList(USER_INFO_FILE);
        if(userInfoList == null){
            userInfoList = new ArrayList<>();
        }


        QuestionList list = ReadWriteObjects.readQuestionList(QUESTION_LIST_FILE);
        if(list!=null)
        {
			
            questionsList = list.questionArrayList;
        }
        else
        {
			System.out.println("Hello");
            questionsList = new LinkedList<>();
        }
    }

    public void start() {
        while (true) {
            try {
                socket = serverSocket.accept();
                NetworkUtil util = new NetworkUtil(socket);
                new ClientHandler(util, userInfoList, clientList, questionsList, connectedUsersKey, commentsList);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
