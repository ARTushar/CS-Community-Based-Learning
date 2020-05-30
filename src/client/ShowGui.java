package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.ClientState;
import util.NetworkUtil;
import util.UserInfo;

import java.io.IOException;

public class ShowGui extends Application {

    Stage window;
    Parent parent;
    static ClientState clientState = ClientState.SIGN_IN;
    private static final int SCR_WIDTH = 1120;
    private static final int SCR_HEIGHT = 700;
    private boolean atStart = true;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        //window.initStyle(StageStyle.UNDECORATED);
        window.resizableProperty().setValue(Boolean.FALSE);
        showStartUp();
    }

    public void showStartUp() throws IOException {
		
        // loading the start up page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("\\startUp.fxml"));
        Parent root = loader.load();

        // loading its controller
        StartUpController controller = loader.getController();
        controller.load(this);

        // showing the scene
        window.setScene(new Scene(root, SCR_WIDTH, SCR_HEIGHT));
        window.show();

    }


    public void showHomePage(NetworkUtil util, UserInfo userInfo) throws IOException {
        // loading the Home page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/homePage.fxml"));
        Parent root = loader.load();
        parent = root;
        atStart = false;

        // loading its controller
        HomePageController controller = loader.getController();

        window.setOnCloseRequest(event ->
        {
            if(!atStart)
                new StopThread(util, ClientState.STOP_THREAD);
            else
            {
                new StopThread(util,ClientState.CLOSE_CONNECTION);
            }

        });

        //passing main class (the class that contains window or stage) reference and socket reference
        controller.load(this,util,userInfo);
        controller.learn.setOnSelectionChanged(e->
        {
            if(controller.learn.isSelected()) {
                System.out.println("Tab changed to Learn");
            }
        });
        controller.discussions.setOnSelectionChanged(event ->
        {
            if(controller.discussions.isSelected()) {
                System.out.println("Tab Changed to Discuss");
            }
        });

        // showing the scene
        window.setScene(new Scene(root, SCR_WIDTH, SCR_HEIGHT));
        window.show();
    }

    public void showCppTutorial(HomePageController homePageController, NetworkUtil util) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/CPPTutorials.fxml"));
        Parent root = loader.load();
        CPPTutorialController controller = loader.getController();


        //passing main class (the class that contains window or stage) reference and socket reference
        controller.load(this,util, homePageController);
        //showing the scene
        homePageController.mainLayout.setCenter(root);

    }

    public void showJavaTutorial(HomePageController homePageController, NetworkUtil util) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/javaTutorial.fxml"));
        Parent root = loader.load();
        JavaTutorialController controller = loader.getController();

        //passing main class (the class that contains window or stage) reference and socket reference
        controller.load(this,util, homePageController);
        //showing the scene
        homePageController.mainLayout.setCenter(root);
    }

    public void showPythonTutorial(HomePageController homePageController, NetworkUtil util) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/python3Tutorial.fxml"));
        Parent root = loader.load();
        Python3TutorialController controller = loader.getController();

        //passing main class (the class that contains window or stage) reference and socket reference
        controller.load(this,util, homePageController);
        //showing the scene
        homePageController.mainLayout.setCenter(root);
    }
    public void showCSharpTutorial(HomePageController homePageController, NetworkUtil util) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/csharpTutorial.fxml"));
        Parent root = loader.load();
        CSharpTutorialController controller = loader.getController();

        //passing main class (the class that contains window or stage) reference and socket reference
        controller.load(this,util, homePageController);
        //showing the scene
        homePageController.mainLayout.setCenter(root);
    }


    public void showTutorial(NetworkUtil util, HomePageController homePageController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/tutorialPage.fxml"));
        Parent root = loader.load();
        TutorialPageController controller = null;
        controller = loader.getController();

        //passing main class (the class that contains window or stage) reference and socket reference
        controller.load(this, util, homePageController);
        //showing the scene
        homePageController.mainLayout.setCenter(root);
    }
    public void showNewDiscussion(HomePageController homePageController, NetworkUtil util) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/newQuestion.fxml"));
        Parent root = loader.load();
        NewQuestionController controller = loader.getController();

        controller.load(this,util,homePageController);
        homePageController.mainLayout.setCenter(root);
    }


}
