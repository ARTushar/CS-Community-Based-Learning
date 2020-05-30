package server;

import javafx.util.Pair;
import util.Comment;
import util.UserInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ReadFile {

    public static ArrayList<UserInfo> readUserInfo(String path, ArrayList<UserInfo> userInfoList) {
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String str = null;
            try {
                str = bufferedReader.readLine();
                Server.totalClient = Integer.parseInt(str);
                str = bufferedReader.readLine();
                while (str != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(str);
                    String id = stringTokenizer.nextToken();
                    String upName = stringTokenizer.nextToken();
                    String upPassword = stringTokenizer.nextToken();
                    String upEmail = stringTokenizer.nextToken();
                    UserInfo userInfo = new UserInfo(upName, upPassword, upEmail, id);
                    userInfoList.add(userInfo);

                    str = bufferedReader.readLine();
                }

                fileReader.close();
            } catch (IOException e) {
                System.out.println("Cannot find any string in file.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return userInfoList;

        /*ObjectInputStream objectInputStream = null;
        FileInputStream fis = null;
        try {
            try {
                fis = new FileInputStream(path);
                objectInputStream = new ObjectInputStream(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            UserInfo userInfo = null;
            try {
                Server.totalClient = (Integer) objectInputStream.readObject();
                userInfo = (UserInfo) objectInputStream.readObject();
            } catch (ClassNotFoundException e1) {
                //e1.printStackTrace();
                System.out.println("exception in first reading");
            }

            while (userInfo != null) {
                userInfoList.add(userInfo);
                try {
                    userInfo = (UserInfo) objectInputStream.readObject();
                } catch (ClassNotFoundException e1) {
                    //e1.printStackTrace();
                    System.out.println("exception in while loop");
                }
            }

        } catch (IOException e) {
            System.out.println("Cannot find any string in file.");
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return userInfoList;*/
    }

    public static ArrayList<Comment> readComment(String path , ArrayList<Comment> commentsList, ArrayList<UserInfo> userInfoList) {
        FileReader fileReader;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String id = bufferedReader.readLine();
            String name = bufferedReader.readLine();
            String cmt = bufferedReader.readLine();
            ArrayList<Pair<String, String>> replies = new ArrayList<>();

            while (id != null) {
                int c = 0;
                String str[] = new String[100];
                str[c] = bufferedReader.readLine();
                while(str[c]!=null){
                    if(str[c]!="*") break;
                    String reply[] = str[c].split(":");
                    if(reply.length>1) replies.add(new Pair<>(reply[0], reply[1]));
                    c++;

                    str[c] = bufferedReader.readLine();
                }
                for (UserInfo userInfo : userInfoList) {
                    if (userInfo.getUpName().equals(name)) {
                        Comment comment = new Comment(userInfo, cmt, replies);
                        comment.setId(id);
                        System.out.println("readed comment");
                        commentsList.add(comment);
                    }
                }
                id = bufferedReader.readLine();
                name = bufferedReader.readLine();
                cmt = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commentsList;

        /*ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Comment comment = null;
            try {
                comment = (Comment) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            while (comment != null) {

                commentsList.add(comment);

                try {
                    comment = (Comment) objectInputStream.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return commentsList;*/
    }

    public static int[] readCommentArray(String path, int[] arr){
        FileReader fileReader;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(path);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0; i<50; i++){
            try {
                arr[i] = bufferedReader.read();
                //System.out.println("reading comment array:"+arr[i]);
                bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return arr;
    }

}
