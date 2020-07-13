package AustinFranks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class ErrorSceneController
{
    @FXML
    public ListView<String> errorList = new ListView<String>();
    ObservableList<String> errorArr = FXCollections.observableArrayList();
    
    public ErrorSceneController()
    {
        
        try
        {
            errorArr = FXCollections.observableArrayList();
            this.errorList.setItems(errorArr);
            //this.errorList.getItems().setAll(errorArr);
            
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage());
            ErrorService.printStacktrace(e);
        }
    }
    
    public void setErrorList( List<String> errorList )
    {
        errorArr = FXCollections.observableArrayList();
        errorArr.setAll(errorList);
        this.errorList.getItems().setAll(errorArr);
    }
}
