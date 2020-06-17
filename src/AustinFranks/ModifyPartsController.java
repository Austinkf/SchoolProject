package AustinFranks;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ModifyPartsController
{

    public ToggleGroup inventory_sourced;
    public Button save;
    public Button cancel;

    public void showMainScene(ActionEvent actionEvent)
    {
        Stage stage = VolatileMemoryService.getActiveStage();
        stage.close();
    }
}
