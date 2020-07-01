package AustinFranks;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class ErrorService
{
    private List<String> errorList  = new ArrayList<String>();
    private Boolean      isErrorSet = false;
    
    public ErrorService()
    {
    
    }
    
    public void addError( String error )
    {
        System.out.println(error);
        errorList.add( error );
        isErrorSet = true;
    }
    
    public Boolean getIsErrorSet()
    {
        return this.isErrorSet;
    }
    
    public void openScene()
    {
        ErrorService.openErrorScene( errorList );
    }
    
    private static Map<String,Object> props = new HashMap<String,Object>();
    
    public static void openErrorScene( String error )
    {
        props.put("height", 500);
        props.put("width", 600);
        List<String> errorList = new ArrayList<String>();
        
        try
        {
            errorList.add(error);
            buildScene("ErrorScene.fxml", props, errorList);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }
    
    public static void openErrorScene( List<String> errorList )
    {
        props.put("height", 500);
        props.put("width", 600);
        
        try
        {
            buildScene("ErrorScene.fxml", props, errorList);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
            printStacktrace(e);
        }
    }
    
    public static void buildScene( String resourceFile, Map<String,Object> properties, List<String> errorList )
    {
        try
        {
            if( !resourceFile.isEmpty() )
            {
                Stage primaryStage = VolatileMemoryService.getPrimaryStage();
                
                if( primaryStage != null )
                {
                    ErrorSceneController controller = new ErrorSceneController( errorList );
                    FXMLLoader load = new FXMLLoader( new URL("/" + resourceFile) );
                    load.setControllerFactory( c -> {
                        return new ErrorSceneController(errorList);
                    });
                    Parent root = load.load();//FXMLLoader.load(controller.getClass().getResource(resourceFile) );
                    
                    int width = 0;
                    int height = 0;
                    
                    if( root != null )
                    {
                        if( properties.containsKey("height") )
                        {
                            height = (Integer) properties.get("height");
                        }
                        
                        if( properties.containsKey("width") )
                        {
                            width = (Integer) properties.get("width");
                        }
                        
                        Scene scene = new Scene(root, width, height);
                        
                        //if( modal )
                        {
                            Stage stage = new Stage();
                            
                            stage.initOwner(primaryStage);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setScene(scene);
                            VolatileMemoryService.setActiveStage(stage);
                            stage.showAndWait();
                        }
                    }
                }
            }
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage());
            printStacktrace(e);
        }
    }
    
    private static void printStacktrace( Exception e )
    {
        try
        {
            List<StackTraceElement> stack = Arrays.asList(e.getStackTrace());
            
            for( StackTraceElement ele : stack )
            {
                System.out.println(ele.toString());
            }
        }
        catch( Exception ex )
        {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}
