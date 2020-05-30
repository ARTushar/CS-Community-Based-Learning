package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import util.ClientState;
import util.NetworkUtil;

import java.io.IOException;

public class CPPTutorialController {
    private ShowGui main;
    private NetworkUtil util;
    private HomePageController controller;

    @FXML
    private ImageView imageView;


    @FXML
    void basicConceptButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HomePageController.receiveListThread.setCppTutorialController(this);
        new SendThread(util, ClientState.CPP_BASIC_CONCEPT);
        ShowGui.clientState = ClientState.CPP;
    }


    @FXML
    void classesButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HomePageController.receiveListThread.setCppTutorialController(this);
        new SendThread(util, ClientState.CPP_CLASSES);
        ShowGui.clientState = ClientState.CPP;
    }

    @FXML
    void conditionalButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HomePageController.receiveListThread.setCppTutorialController(this);
        new SendThread(util, ClientState.CPP_LOOPS);
        ShowGui.clientState = ClientState.CPP;
    }

    @FXML
    void dataTypesButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HomePageController.receiveListThread.setCppTutorialController(this);
        new SendThread(util, ClientState.CPP_DATA_TYPES);
        ShowGui.clientState = ClientState.CPP;
    }

    @FXML
    void functionButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HomePageController.receiveListThread.setCppTutorialController(this);
        new SendThread(util, ClientState.CPP_FUNCTIONS);
        ShowGui.clientState = ClientState.CPP;
    }

    @FXML
    void inheritanceButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HomePageController.receiveListThread.setCppTutorialController(this);
        new SendThread(util, ClientState.CPP_INHERITANCE);
        ShowGui.clientState = ClientState.CPP;
    }

    @FXML
    void templatesButtonHandle(ActionEvent event) {
        try {
            main.showTutorial(util, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HomePageController.receiveListThread.setCppTutorialController(this);
        new SendThread(util, ClientState.CPP_TEMPLATE);
        ShowGui.clientState = ClientState.CPP;
    }

    @FXML
    void backButtonHandle(ActionEvent event) {
        try {
            controller.mainLayout.setCenter(controller.tabPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(ShowGui showGui, NetworkUtil util, HomePageController controller) {
        this.main = showGui;
        this.util = util;
        this.controller = controller;
    }

}
