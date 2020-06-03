package AustinFranks;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;

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
        System.out.println("Event Type: " + event.getEventType());
    }
}
