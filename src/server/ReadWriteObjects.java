package server;

import util.ClientState;
import util.Comment;
import util.QuestionList;
import util.UserInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadWriteObjects {

    public static void writeUserInfoList(ArrayList<UserInfo> userInfoArrayList, String path) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean fileExisted = true;
        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.out.println("No Users in the file");
            fileExisted = false;
        }
        if (fileExisted == true) {
            try {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(userInfoArrayList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static ArrayList<UserInfo> readUserInfoList(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        boolean fileExisted = true;
        ArrayList<UserInfo> userInfoArrayList = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            fileExisted = false;
        }
        if (fileExisted == true) {
            try {
                ois = new ObjectInputStream(fis);
                Object object = ois.readObject();
                if (object instanceof ArrayList)
                    userInfoArrayList = (ArrayList<UserInfo>) object;
                else
                    System.out.println("Not instance of UsersList");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return userInfoArrayList;

    }

    public static void writeQuestionList(QuestionList questionList, String path) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean fileExisted = true;
        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            fileExisted = false;
        }
        if (fileExisted == true) {
            try {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(questionList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static QuestionList readQuestionList(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        boolean fileExisted = true;
        QuestionList questionList = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            fileExisted = false;
        }
        if (fileExisted == true) {
            try {
                ois = new ObjectInputStream(fis);
                Object object = ois.readObject();
                if (object instanceof QuestionList)
                    questionList = (QuestionList) object;
                else
                    System.out.println("No instance of QuestionList");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return questionList;
    }

    public static HashMap<ClientState, ArrayList<Comment>> readComments(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        boolean fileExisted = true;
        HashMap commentsList = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            fileExisted = false;
        }
        if (fileExisted == true) {
            try {
                ois = new ObjectInputStream(fis);
                commentsList = (HashMap<ClientState, ArrayList<Comment>>) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return commentsList;
    }

    public static void writeCommentsList(HashMap<ClientState, ArrayList<Comment>> commentsList, String path) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean fileExisted = true;
        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            fileExisted = false;
        }
        if (fileExisted == true) {
            try {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(commentsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int[] readCommentArray(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        boolean flag = true;
        int[] commentList = new int[50];

        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            for (int i = 0; i < 50; i++) {
                commentList[i] = 0;
            }
            flag = false;
        }

        if (flag) {
            try {
                ois = new ObjectInputStream(fis);
                commentList = (int[]) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return commentList;
    }

    public static void writeCommentArray(String path, int[] arr) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean flag = true;

        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            flag = false;
        }
        if (flag) {
            try {
                oos = new ObjectOutputStream(fos);
                oos.writeObject(arr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
