package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import util.ClientState;
import util.NetworkUtil;

import java.io.IOException;

public class Python3TutorialController {
    ShowGui main;
    NetworkUtil util;
    HomePageController homePageController;
    public void load(ShowGui showGui, NetworkUtil util, HomePageController homePageController) {
        this.main =showGui;
        this.util = util;
        this.homePageController = homePageController;
    }

    @FXML
    void backButtonHandle(ActionEvent event) {
        try {
            //util.write(ClientState.BACK_TO_HOMEPAGE);
            //new SendThread(util, ClientState.BACK_TO_HOMEPAGE);
            homePageController.mainLayout.setCenter(homePageController.tabPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleBasicConcept(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // util.write(ClientState.PYTHON_BASIC_CONCEPT);
        new SendThread(util, ClientState.PYTHON_BASIC_CONCEPT);
        ShowGui.clientState = ClientState.PYTHON;
        System.out.println("successfully sent");
    }

    @FXML
    void handleControlStructure(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        util.write(ClientState.PYTHON_CONTROL_STRUCTURE);
        new SendThread(util, ClientState.PYTHON_CONTROL_STRUCTURE);
        ShowGui.clientState = ClientState.PYTHON;
        System.out.println("successfully sent");
    }

    @FXML
    void handleException(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //util.write(ClientState.PYTHON_EXCEPTION);
        new SendThread(util, ClientState.PYTHON_EXCEPTION);
        ShowGui.clientState = ClientState.PYTHON;
        System.out.println("successfully sent");
    }

    @FXML
    void handleFiles(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //util.write(ClientState.PYTHON_FILE);
        new SendThread(util, ClientState.PYTHON_FILE);
        ShowGui.clientState = ClientState.PYTHON;
        System.out.println("successfully sent");
    }

    @FXML
    void handleFunction(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
       // util.write(ClientState.PYTHON_FUNCTION);
        new SendThread(util, ClientState.PYTHON_FUNCTION);
        ShowGui.clientState = ClientState.PYTHON;
        System.out.println("successfully sent");
    }

    @FXML
    void handleIFunctionalProgramming(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //util.write(ClientState.PYTHON_FUNCTIONAL_PROGRAMMING);
        new SendThread(util, ClientState.PYTHON_FUNCTIONAL_PROGRAMMING);
        ShowGui.clientState = ClientState.PYTHON;
        System.out.println("successfully sent");
    }

    @FXML
    void handleOOP(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //util.write(ClientState.PYTHON_CLASSES);
        new SendThread(util, ClientState.PYTHON_CLASSES);
        ShowGui.clientState = ClientState.PYTHON;
        System.out.println("successfully sent");
    }

    @FXML
    void handleRegularExpression(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //util.write(ClientState.PYTHON_REGULAR_EXPRESSION);
        new SendThread(util, ClientState.PYTHON_REGULAR_EXPRESSION);
        ShowGui.clientState = ClientState.PYTHON;
        System.out.println("successfully sent");
    }

    @FXML
    void handleString(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //util.write(ClientState.PYTHON_STRINGS);
        new SendThread(util, ClientState.PYTHON_STRINGS);
        ShowGui.clientState = ClientState.PYTHON;
        System.out.println("successfully sent");
    }

}
