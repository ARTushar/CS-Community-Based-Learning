package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.ClientState;
import util.NetworkUtil;

import java.io.IOException;

public class JavaTutorialController {
    private ShowGui main;
    private NetworkUtil util;
    private HomePageController homePageController;

    @FXML
    void collectionButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_COLLECTIONS );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void enumButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_ENUMERATION );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void fileButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_FILES );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void inheritanceButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_INHERITANCE );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void introductionButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_INTRODUCTION );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void arrayButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_ARRAYS );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void networkButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_NETWORKING );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void packageButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_PACKAGE );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void stringButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_STRINGS );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void threadButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, homePageController);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new SendThread(util,ClientState.JAVA_THREADING );
        ShowGui.clientState = ClientState.JAVA;
    }

    @FXML
    void backButtonHandle(ActionEvent event) {
        homePageController.mainLayout.setCenter(homePageController.tabPane);
    }



    public void load(ShowGui showGui, NetworkUtil util, HomePageController homePageController) {
        this.main = showGui;
        this.util = util;
        this.homePageController = homePageController;
    }
}
