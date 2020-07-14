package AustinFranks;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ModifyPartsController
{

    public ToggleGroup inventory_sourced;
    public Button save;
    public Button cancel;
    public RadioButton inHouse;
    public RadioButton outsourced;
    public TextField id;
    public TextField name;
    public TextField inventoryLevel;
    public TextField price;
    public TextField max;
    public TextField min;
    public Label companyNameLabel;
    public Label machineIdLabel;
    public TextField machineId;
    public TextField companyName;

    ErrorService errorService = new ErrorService();
    InHouse    housePart   = null;
    Outsourced sourcedPart = null;
    Integer    index;

    public ModifyPartsController( InHouse ihPart, Outsourced osPart, Integer index )
    {
        if( ihPart != null )
        {
            this.housePart = ihPart;
        }
        if( osPart != null )
        {
            this.sourcedPart = osPart;
        }

        this.index = index;
    }

    public void showMainScene(ActionEvent actionEvent)
    {
        Stage stage = VolatileMemoryService.getActiveStage();
        stage.close();
    }

    @FXML
    public void initialize()
    {
        if( housePart != null )
        {
            inventory_sourced.selectToggle(inHouse);
            machineId.setText(String.valueOf(housePart.getMachineId()));
            id.setText(String.valueOf(housePart.getId()));
            price.setText(String.valueOf(housePart.getPrice()));
            inventoryLevel.setText(String.valueOf(housePart.getStock()));
            max.setText(String.valueOf(housePart.getMax()));
            min.setText(String.valueOf(housePart.getMin()));
            name.setText(housePart.getName());
        }
        else if( sourcedPart != null )
        {
            inventory_sourced.selectToggle(outsourced);
            companyName.setText(sourcedPart.getCompanyName());
            id.setText(String.valueOf(sourcedPart.getId()));
            price.setText(String.valueOf(sourcedPart.getPrice()));
            inventoryLevel.setText(String.valueOf(sourcedPart.getStock()));
            max.setText(String.valueOf(sourcedPart.getMax()));
            min.setText(String.valueOf(sourcedPart.getMin()));
            name.setText(sourcedPart.getName());
        }


        handleToggleChange(new ActionEvent());

    }

    public void savePart( ActionEvent event )
    {
        System.out.println("Pressed");
        try
        {
            if( name.getText().isEmpty() )
                errorService.addError("Name cannot be null");

            if( price.getText().isEmpty() )
                errorService.addError("Price cannot be null");

            if( max.getText().isEmpty() )
                errorService.addError("Max cannot be null");

            if( min.getText().isEmpty() )
                errorService.addError("Min cannot be null");

            if( inventoryLevel.getText().isEmpty() )
            {
                errorService.addError("Inventory Level cannot be null");
            }
            else
            {
                if( min.getText() != null && max.getText() != null )
                {
                    if( Integer.parseInt(inventoryLevel.getText()) < Integer.parseInt(min.getText()) || Integer.parseInt(inventoryLevel.getText()) > Integer.parseInt(max.getText()) )
                    {
                        errorService.addError("Inventory Level cannot excede min and max");
                    }
                }
            }

            if( !errorService.getIsErrorSet() )
            {
                RadioButton selected = (RadioButton) inventory_sourced.getSelectedToggle();
                Part newPart;

                if( selected.getId().equals("inHouse") )
                {
                    newPart = new InHouse( Inventory.getProductNextId(),
                            name.getText(),
                            Double.parseDouble(price.getText()),
                            Integer.parseInt(inventoryLevel.getText()),
                            Integer.parseInt(min.getText()),
                            Integer.parseInt(max.getText()) );
                }
                else
                {
                    newPart = new Outsourced( Inventory.getProductNextId(),
                            name.getText(),
                            Double.parseDouble(price.getText()),
                            Integer.parseInt(inventoryLevel.getText()),
                            Integer.parseInt(min.getText()),
                            Integer.parseInt(max.getText()) );
                }

                Inventory.updatePart(index, newPart);
                //addProductToScreen( newProduct );
                System.out.println("Product Added");
                MainSceneController.resetMainScene();
                showMainScene( new ActionEvent() );
            }
            else
            {
                errorService.openScene();
            }
        }
        catch( Exception e )
        {
            System.out.println("HERE");
            ErrorService.openErrorScene("Exception: " + e.getMessage());
        }
    }

    public void handleToggleChange( ActionEvent event )
    {
        try
        {
            RadioButton selected = (RadioButton) inventory_sourced.getSelectedToggle();

            System.out.println("Button: " + selected.getId());


            if( selected.getId().equals("inHouse") )
            {
                companyNameLabel.setVisible(false);
                companyName.setVisible(false);
                machineIdLabel.setVisible(true);
                machineId.setVisible(true);
            }
            else
            {
                machineIdLabel.setVisible(false);
                machineId.setVisible(false);
                companyNameLabel.setVisible(true);
                companyName.setVisible(true);
            }
        }
        catch( Exception e )
        {
            ErrorService.openErrorScene("Exception: " + e.getMessage());
        }
    }


    public void restrictTextfieldToNumber( KeyEvent event )
    {
        String returnText = "";

        try
        {
            TextField tf          = (TextField) event.getSource();
            String    newText     = event.getCode().getName();
            String    currentText = tf.getText();

            if( !newText.isEmpty() )
            {
                if( newText.contains("Numpad") )
                {
                    newText = newText.replace("Numpad ", "");
                }
                System.out.println("New Text: " + newText);
                if( newText.matches("[0-9]*") )
                {
                    returnText = currentText;
                }
                else if( newText.toLowerCase().contains("backspace") )
                {
                    returnText = currentText.substring(0, currentText.length() );
                }
                else
                {
                    returnText = "";
                }
                System.out.println("ReturnText: " + returnText);
                tf.setText(returnText);
            }
        }
        catch( Exception e )
        {
            ErrorService.openErrorScene("Exception: " + e.getMessage());
        }
    }



    public void clearText( KeyEvent event )
    {
        try
        {
            TextField tf = (TextField)event.getSource();
            String text = tf.getText();

            if( !text.matches("[0-9]*") )
            {
                tf.clear();
            }
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
