package client;

import util.NetworkUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecieveFIle {

    public static String recieveFile(NetworkUtil util) {

        String message = null;

        Object o = util.read();
        if (o instanceof String) {
            message = (String) o;
        } else {
            System.out.println("file receiving exception");
        }
        return message;
    }


    /*public static String recieveFile(NetworkUtil util) {

        String message = "";
        while (true) {
            Object o = util.read();
            File file = null;
            if (o instanceof File) {
                file = (File) o;
            }
            if (file != null) {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (scanner.hasNext()) {
                    message += scanner.nextLine();
                    message += "\n";
                }
                break;
            }
        }
        return message;
    }*/

}
