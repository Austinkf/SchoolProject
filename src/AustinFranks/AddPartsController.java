package AustinFranks;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddPartsController
{
    public ToggleGroup inventory_sourced;
    public Button save;
    public Button cancel;

    public void showMainScene( ActionEvent event )
    {
        Stage stage = VolatileMemoryService.getActiveStage();
        stage.close();
    }

}
