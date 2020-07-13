package AustinFranks;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StageService
{
    private static final int DEFAULT_HEIGHT = 768;
    private static final int DEFAULT_WIDTH  = 1200;

    private static SceneBuilder builder     = null;

    private StageService( String resourceFile, Map<String,Object> properties, Boolean modal )
    {
        builder = new SceneBuilder(resourceFile, properties, modal);
    }

    public static void showScene(String resourceFile, Map<String, Object> properties, Boolean modal )
    {
        try
        {
            StageService service = new StageService(resourceFile, properties, modal);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public static void showMainScene()
    {
        showScene("MainScene.fxml", new HashMap<String,Object>(), false);
    }

    private class SceneBuilder
    {
        String             resourceFile;
        Map<String,Object> properties;
        Boolean            modal;

        public SceneBuilder( String resourceFile, Map<String,Object> properties, Boolean modal )
        {
            this.resourceFile = resourceFile;
            this.properties   = properties;
            this.modal        = modal;

            buildScene();
        }

        public void buildScene()
        {
            try
            {
                if( !resourceFile.isEmpty() )
                {
                    Stage primaryStage = VolatileMemoryService.getPrimaryStage();

                    if( primaryStage != null )
                    {
                        Parent root = FXMLLoader.load( getClass().getResource(resourceFile) );

                        int width;
                        int height;

                        if( root != null )
                        {
                            if( properties.containsKey("height") )
                            {
                                height = (Integer) properties.get("height");
                            }
                            else
                            {
                                height = StageService.DEFAULT_HEIGHT;
                            }

                            if( properties.containsKey("width") )
                            {
                                width = (Integer) properties.get("width");
                            }
                            else
                            {
                                width = StageService.DEFAULT_WIDTH;
                            }

                            Scene scene = new Scene(root, width, height);

                            if( modal )
                            {
                                Stage stage = new Stage();

                                stage.initOwner(primaryStage);
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setScene(scene);
                                VolatileMemoryService.setActiveStage(stage);
                                stage.showAndWait();
                            }
                            else
                            {
                                primaryStage.setScene(scene);
                                primaryStage.show();
                            }
                        }
                    }
                }
            }
            catch( Exception e )
            {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }
}
