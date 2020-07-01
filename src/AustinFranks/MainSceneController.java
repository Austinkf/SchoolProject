package AustinFranks;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MainSceneController
{
    public SplitPane mainSceneView;
    public Button exitApplicationButton;
    public Button addButton;
    public Button modifyButton;
    public Button deleteButton;
    public Button searchButton;
    public TextField searchField;
    public TreeTableColumn treeView;
    
    Map<String,Object> props = new HashMap<String,Object>();
    
    public void openAddProductscene(ActionEvent event )
    {
        props.put("height", 768);
        props.put("width", 1200);
        try
        {
            StageService.showScene("AddProducts.fxml", props, false);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }

    public void openModifyProductScene(ActionEvent event )
    {
        props.put("height", 768);
        props.put("width", 1200);
        try
        {
            StageService.showScene("ModifyProducts.fxml", props, false);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }
    
    public void openAddPartScene(ActionEvent event )
    {
        props.put("height", 500);
        props.put("width", 450);
        try
        {
            StageService.showScene("AddParts.fxml", props, true);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }
    
    public void openModifyPartScene(ActionEvent event )
    {
        props.put("height", 500);
        props.put("width", 450);
        try
        {
            StageService.showScene("ModifyParts.fxml", props, true);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }

    public void closeApplication( ActionEvent event )
    {
        Stage stage = VolatileMemoryService.getPrimaryStage();
        stage.close();
    }
}
