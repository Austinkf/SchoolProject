package AustinFranks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.List;

public class ErrorSceneController
{
    public ListView errorList;
    
    public ErrorSceneController( List<String> errorList )
    {
        try
        {
            ObservableList<String> errorArr = FXCollections.observableList(errorList);
            //this.errorList.setItems(errorArr);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
