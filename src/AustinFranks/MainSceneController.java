package AustinFranks;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;

import java.util.HashMap;

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
    
    public void openAddScene( ActionEvent event )
    {
        try
        {
            StageService.showScene("AddParts.fxml",new HashMap<String,Object>(), true);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }

    public void openModifyScene( ActionEvent event )
    {
        try
        {
            StageService.showScene("ModifyParts.fxml",new HashMap<String,Object>(), true);
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
