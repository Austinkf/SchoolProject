package AustinFranks;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class VolatileMemoryService
{
    private static volatile Stage primaryStage = null;
    private static volatile Stage activeStage = null;
    private static volatile MainSceneController mainController = null;

    public static synchronized void setPrimaryStage( Stage stage )
    {
        primaryStage = stage;
    }

    public static synchronized Stage getPrimaryStage()
    {
        return primaryStage;
    }

    public static synchronized void setActiveStage( Stage stage )
    {
        activeStage = stage;
    }

    public static synchronized Stage getActiveStage()
    {
        return activeStage;
    }

    public static synchronized void setMainController( MainSceneController controller )
    {
        mainController = controller;
    }

    public static synchronized MainSceneController getMainController()
    {
        return mainController;
    }
}
