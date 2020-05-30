package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import util.ClientState;
import util.NetworkUtil;
import util.UserInfo;

import java.io.IOException;

public class StartUpController {

    private UserInfo userInfo;
    private Client client;
    private NetworkUtil networkUtil;
    private ShowGui main;

    public void load(ShowGui main)
    {
        client   = new Client();
        networkUtil = client.getNetworkUtil();
        this.main = main;
    }

    @FXML
     Label closeButton;

    @FXML
     TextField signInName;

    @FXML
     PasswordField signInPassword;

    @FXML
     Button signInButton;

    @FXML
     Text signUpAlert;

    @FXML
     Button signUpButton;

    @FXML
     TextField signUpName;

    @FXML
     PasswordField signUpPassword;

    @FXML
     PasswordField signUpRePassword;

    @FXML
     TextField signUpEmail;

    @FXML
     Text alertMessage;


    @FXML
    void closeWindow(MouseEvent event) {
        main.window.close();

    }

    @FXML
    void signIn(ActionEvent event) {
        userInfo = new UserInfo("signIn", signInName.getText(), signInPassword.getText());
        new SignThread(main,this,networkUtil, ClientState.SIGN_IN,userInfo);

    }

    @FXML
    void signUp(ActionEvent event) {
        if(signUpPassword.getText().equals(signUpRePassword.getText())) {
            if (signUpName.getLength() != 0 && signUpPassword.getLength() != 0 && signUpEmail.getLength() != 0 && signUpRePassword.getLength() != 0) {
                userInfo = new UserInfo("signUp", signUpName.getText(), signUpPassword.getText(), signUpRePassword.getText(), signUpEmail.getText());
                new SignThread(main, this, networkUtil, ClientState.SIGN_UP, userInfo);
                signUpAlert.setText("");
            } else
                signUpAlert.setText("No field can be empty!");
        }
        else
            signUpAlert.setText("Password has not matched!");

    }


    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }
}
