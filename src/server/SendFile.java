package server;

import util.NetworkUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SendFile {

    public static void sendFile(NetworkUtil nc , String path)
    {
        File file = new File(path);
        String message = "";
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
        }

        nc.write(message);
    }

    /*public static void sendFile(NetworkUtil nc , String path)
    {
        File file = new File(path);
        nc.write(file);
        System.out.println("Sent successfully to client");
    }*/
}
