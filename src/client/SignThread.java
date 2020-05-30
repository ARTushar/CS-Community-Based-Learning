package client;

import javafx.application.Platform;
import util.ClientState;
import util.NetworkUtil;
import util.UserInfo;

import java.io.IOException;

public class SignThread implements Runnable {
    private ShowGui main;
    private StartUpController startUpController;
    private NetworkUtil util;
    private ClientState clientState;
    private UserInfo userInfo;
    private String message;

    public SignThread(ShowGui main, StartUpController startUpController, NetworkUtil util, ClientState clientState, UserInfo userInfo) {
        this.main = main;
        this.startUpController = startUpController;
        this.util = util;
        this.clientState = clientState;
        this.userInfo = userInfo;
        Thread thread = new Thread( this);
        thread.start();
    }

    @Override
    public void run() {
        if(clientState == ClientState.SIGN_UP)
        {
            util.write(clientState);
            util.write(userInfo);
            String msg = (String) util.read();
            Platform.runLater(()->
            {
                startUpController.signUpAlert.setText(msg);
                eraseField();
            });

        }
        else if(clientState == ClientState.SIGN_IN)
        {
            util.write(clientState);
            util.write(userInfo);
            String message = null;
            message = (String) util.read();
            if( !message.equals("found"))
            {

                Platform.runLater(()->
                {
                    startUpController.alertMessage.setText("Invalid UserName or Email");
                    startUpController.signInName.setText("");
                    startUpController.signInPassword.setText("");
                });

            }
            else
            {
                userInfo = (UserInfo) util.read();
                util.setUserName( startUpController.signInName.getText());
                    System.out.println("Hello "+util.getUserName());
                    Platform.runLater(()->
                    {
                        try {
                            main.showHomePage(util,userInfo);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            }
        }
    }
    private void eraseField()
    {
        startUpController.signUpName.setText("");
        startUpController.signUpPassword.setText("");
        startUpController.signUpRePassword.setText("");
        startUpController.signUpEmail.setText("");
    }

}
