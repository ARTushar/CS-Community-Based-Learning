package server;


import util.*;

import java.util.*;

public class ClientHandler implements Runnable {

    private NetworkUtil util;
    private HashMap<String, NetworkUtil> clientList;
    private ArrayList<UserInfo> userInfoList ;
    private LinkedList<Question> questionsList;
    private ArrayList<String> connectedUserskey;
    private UserInfo userInfo;
    private Thread thread;
    private boolean fileSaved = false;
    private int flag = 1;
    private String received;
    private Object o;
    private static int totalQuestion = 0;
    protected static HashMap<ClientState, ArrayList<Comment>> commentsList;
    private boolean threadAliveFlag = true;

    private ClientState clientState;

    public ClientHandler(NetworkUtil util, ArrayList<UserInfo> userInfoList, HashMap<String, NetworkUtil> clientList, LinkedList<Question> questionsList, ArrayList<String> connectedUserskey, HashMap<ClientState, ArrayList<Comment>> cmtList) {
        this.util = util;
        this.userInfoList = userInfoList;
        this.clientList = clientList;
        this.questionsList = questionsList;
        this.connectedUserskey  = connectedUserskey;
        commentsList = cmtList;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (threadAliveFlag)
        {
            Object o = util.read();
            if(o instanceof  ClientState) {
                clientState = (ClientState) o;
                ArrayList<NetworkUtil> list = Server.clientStatesList.get(clientState);
                list.add(util);
                Server.clientStatesList.put(clientState, list);
            }
            if(clientState == ClientState.SIGN_UP)
            {
                Object object = util.read();
                if (object instanceof UserInfo) {
                    userInfo = (UserInfo) object;
                    Server.totalClient++;
                    String id = PrimaryKeyGenerator.keyGenarate(userInfo.getUpName(), Server.totalClient);
                    userInfo.setId(id);
                    userInfoList.add(userInfo);
                    util.write("Successfully signed up");
                    if (fileSaved == false) {
                       // WriteFile.writeClientInfo("src\\files\\ClientInformation.txt", userInfoList);
                        ReadWriteObjects.writeUserInfoList(userInfoList,Server.USER_INFO_FILE);
                        fileSaved = true;
                    }
                    System.out.println("Successfully added . client name"+userInfo.getUpName());
                }
            }
            else if(clientState == ClientState.SIGN_IN)
            {
                Object object = util.read();
                if (object instanceof UserInfo) {
                    userInfo = (UserInfo) object;

                    if (findUser(util, userInfo))
                    {
                        util.write("found");
                        util.write(userInfo);


                        System.out.println("Signed In :"+userInfo.getInName());
                        connectedUserskey.add(userInfo.getId());
                        clientList.put(userInfo.getId(), util);
                    }
                    else
                        util.write("not found");
                }
            }
            else if(clientState == ClientState.QUESTION_LIST_INITIALISE)
            {
                util.write(ClientState.QUESTION_LIST_INITIALISE);
                util.write(questionsList);
            }
            else if(clientState == ClientState.RECEIVE_QUESTION)
            {
                Object object = util.read();
                if(object instanceof Question) {
                    totalQuestion++;
                    Question question = (Question) object;
                    System.out.println("Successfully received question from client : "+userInfo.getInName());
                    System.out.println(question);
                    question.setPostedBy(userInfo.getUpName());
                    String id = PrimaryKeyGenerator.keyGenarate(question.getPostedBy(), totalQuestion);
                    question.setQuestionID(id);
                    question.setUserID(userInfo.getId());
                    questionsList.addFirst(question);
                    SendLists.sendQuestionList(questionsList, connectedUserskey, clientList);

                }
                else
                    System.out.println("Cannot receive the question from the client :"+userInfo.getInName());
            }
            else if(clientState == ClientState.RECEIVE_ANSWER)
            {
                Object object = util.read();
                if(object instanceof Answer) {
                    Answer answer = (Answer) object;

                    for (Question q : questionsList) {
                        if (q.getQuestionID().equals(answer.getQuestionID())) {
                            q.getAnswer().addFirst(answer);
                            break;
                        }
                    }
                    SendLists.sendQuestionList(questionsList, connectedUserskey, clientList);
                }
                else
                    System.out.println("Something went wrong receiving Answer from client : "+userInfo.getInName());
            }
            else if(clientState == ClientState.UPDATE_QUESTION)
            {
                Object object = util.read();
                if(object instanceof Question){
                    Question question = (Question) object;
                    Iterator iterator = questionsList.iterator();
                    LinkedList<Answer> answers = question.getAnswer();

                    while(iterator.hasNext()) {
                        Question q = (Question) iterator.next();
                        if(q.getQuestionID().equals(question.getQuestionID())) {
                            q.setLikeNumber(Integer.parseInt(question.getLikeNumber()));
                            LinkedList<Answer> a = q.getAnswer();
                            for(int i = 0 ; i < a.size() ;i++)
                            {
                                a.get(i).setLike(answers.get(i).getLike());
                                if(a.get(i).getLike()<-5)
                                {
                                    a.remove(i);
                                }
                            }
                            System.out.println("Updated the question");
                            sortAnswer(q);
                            SendLists.sendQuestionList(questionsList,connectedUserskey,clientList);
//                            System.out.println("like number of the question : "+q.getAnswer().getFirst().getLike());
                            break;
                        }
                    }
                }
                else {
                    System.out.println("Something went wrong in updating the question from client : "+userInfo.getInName());
                }
            }
            else if(clientState == ClientState.STOP_THREAD)
            {
                util.write(ClientState.CLOSE_CONNECTION);
            }

            else if (clientState == ClientState.CPP_BASIC_CONCEPT) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending basic concept tutorial");
                SendFile.sendFile(util, "src\\files\\CPPFiles\\Basic Concept.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.CPP_LOOPS) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending loop tutorial");
                SendFile.sendFile(util, "src\\files\\CPPFiles\\Loop Types.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.CPP_DATA_TYPES) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending datatype tutorial");
                SendFile.sendFile(util, "src\\files\\CPPFiles\\Data Types.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.CPP_FUNCTIONS) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending function tutorial");
                SendFile.sendFile(util, "src\\files\\CPPFiles\\Functions.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.CPP_CLASSES) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending class tutorial");
                SendFile.sendFile(util, "src\\files\\CPPFiles\\Classes and Objects.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.CPP_INHERITANCE) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending inheritance tutorial");
                SendFile.sendFile(util, "src\\files\\CPPFiles\\Inheritance.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.CPP_TEMPLATE) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending template tutorial");
                SendFile.sendFile(util, "src\\files\\CPPFiles\\Templates.html");
                handleTutorial("src\\files\\comments.txt");
            }else if (clientState == ClientState.JAVA_INTRODUCTION) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending java Introduction tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\introduction.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.JAVA_ARRAYS) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending loop tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\Arrays & Loops.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.JAVA_COLLECTIONS) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending datatype tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\Collection.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.JAVA_PACKAGE) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending function tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\Packages.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.JAVA_FILES) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending class tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\JavaIO.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.JAVA_INHERITANCE) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending inheritance tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\Inheritance.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.JAVA_STRINGS) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending template tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\Strings.html");
                handleTutorial("src\\files\\comments.txt");
            }else if (clientState == ClientState.JAVA_ENUMERATION) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending function tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\Enumeration.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.JAVA_THREADING) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending class tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\Thread.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.JAVA_NETWORKING) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending inheritance tutorial");
                SendFile.sendFile(util, "src\\files\\JavaFiles\\Networking.html");
                handleTutorial("src\\files\\comments.txt");
            }else if (clientState == ClientState.PYTHON_BASIC_CONCEPT) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending basic concept tutorial");
                SendFile.sendFile(util, "src\\files\\PythonFiles\\Basic Concept.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.PYTHON_CLASSES) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending loop tutorial");
                SendFile.sendFile(util, "src\\files\\PythonFiles\\Classes.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.PYTHON_CONTROL_STRUCTURE) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending datatype tutorial");
                SendFile.sendFile(util, "src\\files\\PythonFiles\\Control Structure.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.PYTHON_FUNCTION) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending function tutorial");
                SendFile.sendFile(util, "src\\files\\PythonFiles\\Function.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.PYTHON_FILE) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending class tutorial");
                SendFile.sendFile(util, "src\\files\\PythonFiles\\File.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.PYTHON_EXCEPTION) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending inheritance tutorial");
                SendFile.sendFile(util, "src\\files\\PythonFiles\\Exception.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.PYTHON_FUNCTIONAL_PROGRAMMING) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending template tutorial");
                SendFile.sendFile(util, "src\\files\\PythonFiles\\Functional Programming.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.PYTHON_STRINGS) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending class tutorial");
                SendFile.sendFile(util, "src\\files\\PythonFiles\\Strings.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.PYTHON_REGULAR_EXPRESSION) {
                util.write(ClientState.RECEIVE_TUTORIAL);
                System.out.println("sending inheritance tutorial");
                SendFile.sendFile(util, "src\\files\\PythonFiles\\RegularExpression.html");
                handleTutorial("src\\files\\comments.txt");
            } else if (clientState == ClientState.CLOSE_CONNECTION) {
                closeConnection(clientState);
            }
            ArrayList<NetworkUtil> list = Server.clientStatesList.get(clientState);
            list.remove(util);
            Server.clientStatesList.put(clientState, list);
        }
        System.out.println(userInfo.getId()+" : closing thread");

    }


    private boolean findUser(NetworkUtil util, UserInfo userInfo ) {
        Iterator iterator = userInfoList.iterator();
        while (iterator.hasNext()) {
            UserInfo user = (UserInfo) iterator.next();
            if (user.getUpName().equals(userInfo.getInName()) && user.getUpPassword().equals(userInfo.getInPassword())) {
                //System.out.println(user.getUpName() + user.getUpPassword());
                userInfo.reload(user);
                userInfo.setId(user.getId());
                clientList.put(user.getId(), util);
                Server.currentClient++;
                return true;
            }
        }
        return false;
    }
    private boolean closeConnection(ClientState State)
    {

        if(State == ClientState.CLOSE_CONNECTION) {
            //util.write(ClientState.STOP_DISCUSSION);
            util.closeConnection();
            Iterator iterator = connectedUserskey.iterator();

            while (iterator.hasNext())
            {
                String string = (String) iterator.next();
                if(string.equals(userInfo.getId())) {
                    connectedUserskey.remove(string);
                    break;
                }
            }
            QuestionList list = new QuestionList(questionsList);
            ReadWriteObjects.writeQuestionList(list,Server.QUESTION_LIST_FILE);
            ReadWriteObjects.writeCommentsList(commentsList, "src\\files\\comments.txt");
            threadAliveFlag  = false;
            return true;
        }
        return false;
    }
    private void sortAnswer(Question question)
    {
        LinkedList<Answer> answers = question.getAnswer();
        Collections.sort(answers,new AnswerClassComparator());
        Collections.reverse(answers);
    }

    private void handleTutorial(String path) {

        ArrayList<Comment> commentArrayList = commentsList.get(clientState);
        CommentList commentList = new CommentList(commentArrayList);
        util.resetObjecttOutputStream();
        util.write(ClientState.RECEIVE_COMMENT_LIST);
        util.write(commentList);
        //SendLists.sendCommentList(commentArrayList, connectedUserskey, clientList);

        while(true) {
            Object o = util.read();
            if (o instanceof String) {
                String str = (String) o;
                String[] arr = str.split(":");

                if(arr.length>1) {
                    if (arr[0].equals("comment")) {
                        Server.commentNumberArray[11]++;
                        System.out.println("comment received successfully");
                        Comment comment = new Comment(userInfo, arr[1]);
                        String id = PrimaryKeyGenerator.keyGenarate(userInfo.getUpName(),Server.commentNumberArray[11]);
                        comment.setId(id);
                        commentArrayList.add(comment);
                        commentsList.put(clientState, commentArrayList);

                        ReadWriteObjects.writeCommentsList(commentsList, "src\\files\\comments.txt");
                        ReadWriteObjects.writeCommentArray("src\\files\\Comment Array.txt", Server.commentNumberArray);
                        //SendLists.sendCommentList(commentArrayList, connectedUserskey, clientList);
                        SendLists.sendCommentsList(commentArrayList, Server.clientStatesList.get(clientState));
                        //SendLists.sendCommentList(commentArrayList, connectedUserskey, clientList);
                    } else {
                        int c=0;
                        System.out.println("reply :" + arr[1]);

                        for (Comment temp: commentArrayList) {
                            if (temp.getId().equals(arr[0])) {
                                temp.addReply(arr[1], userInfo.getUpName());
                                commentArrayList.set(c, temp);
                                commentsList.put(clientState, commentArrayList);
                                break;
                            }
                            c++;
                        }

                        ReadWriteObjects.writeCommentsList(commentsList, "src\\files\\comments.txt");
                        //SendLists.sendCommentList(commentArrayList, connectedUserskey, clientList);
                        SendLists.sendCommentsList(commentArrayList, Server.clientStatesList.get(clientState));
                        //SendLists.sendCommentList(commentArrayList, connectedUserskey, clientList);
                    }
                }
            }else if(o instanceof ClientState){
                ClientState c = (ClientState) o;
                //System.out.println("in handle tutorial first"+ c);
                if(c == ClientState.BACK_TO_HOMEPAGE){
                    //System.out.println("in handle tutorial "+c);
                    break;
                }else if(c == ClientState.STOP_THREAD){
                    util.write(ClientState.CLOSE_CONNECTION);
                    break;
                }

            }
        }
    }

}
