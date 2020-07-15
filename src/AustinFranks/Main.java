package AustinFranks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        VolatileMemoryService.setPrimaryStage(primaryStage);
        FXMLLoader load = new FXMLLoader( getClass().getResource("MainScene.fxml") );

        Parent root = load.load();
        VolatileMemoryService.setMainController(load.getController());
        Scene scene = new Scene(root, 1020, 633);
        primaryStage.setTitle("Inventory");
        primaryStage.setScene( scene );
        primaryStage.show();
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
}