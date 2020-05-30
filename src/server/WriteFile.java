package server;

import javafx.util.Pair;
import util.Comment;
import util.UserInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class WriteFile {

    public static void writeClientInfo(String path, ArrayList<UserInfo> userInfoList) {
        File file = new File(path);

        try {
            FileWriter fileWriter = new FileWriter(file);
            Iterator itr = userInfoList.iterator();

            fileWriter.write(Server.totalClient + "\n");

            while (itr.hasNext()) {
                UserInfo userInfo = (UserInfo) itr.next();
                String str = userInfo.getId() + " " + userInfo.getUpName() + " " + userInfo.getUpPassword() + " " + userInfo.getUpEmail();
                fileWriter.write(str + "\n");
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File writer exception");
        }
        /*try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            Iterator itr = userInfoList.iterator();

            objectOutputStream.writeObject(Server.totalClient);

            while (itr.hasNext()) {
                UserInfo userInfo = (UserInfo) itr.next();
                //String str = userInfo.getId() + " " + userInfo.getUpName() + " " + userInfo.getUpPassword() + " " + userInfo.getUpEmail();
                //objectOutputStream.write(str + "\n");
                objectOutputStream.writeObject(userInfo);
            }
            objectOutputStream.flush();

            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("File writer exception");
        }*/
    }

    public static void writeComment(String path, ArrayList<Comment> commentsList) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
        } catch (IOException e) {
            System.out.println("file open exception in filewriter");
        }

        Iterator iterator = commentsList.iterator();
        while (iterator.hasNext()) {
            Comment comment = (Comment) iterator.next();
            try {
                fileWriter.write(comment.getCommenterInfo().getId() + "\n");
                fileWriter.write(comment.getCommenterInfo().getUpName() + "\n");
                fileWriter.write(comment.getComment() + "\n");
                Iterator<Pair<String, String>> itr = comment.getReplies().iterator();
                while (itr.hasNext()) {
                    Pair<String, String> p = itr.next();
                    fileWriter.write(p.getKey() + ":" + p.getValue() + "\n");
                }
                fileWriter.write("*\n");
            } catch (IOException e) {
                System.out.println("file write exception");
            }
        }

        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        } catch (IOException e) {
            System.out.println("file open exception in objectOutputStream");
        }

        Iterator iterator = commentsList.iterator();
        while (iterator.hasNext()){
            Comment comment = (Comment) iterator.next();
            try {
                objectOutputStream.writeObject(comment);
                objectOutputStream.flush();
            } catch (IOException e) {
                System.out.println("file write exception");
            }
        }

        try {
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static void commentArrayWrite(String path, int [] arr){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
        } catch (IOException e) {
            System.out.println("file open exception in filewriter");
        }

        for (int i : arr) {
            try {
                fileWriter.write(i);
                fileWriter.write("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
