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
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        Scene scene = new Scene(root, 1021, 635);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene( scene );
        primaryStage.show();
    }
    
    
    public static void main(String[] args)
    {
        launch(args);
    }
}