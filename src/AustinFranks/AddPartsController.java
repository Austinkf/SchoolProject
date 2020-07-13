package AustinFranks;

import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.text.ParsePosition;

public class AddPartsController
{
    public ToggleGroup inventory_sourced;
    public Button save;
    public Button cancel;
    public RadioButton outsourced;
    public RadioButton inHouse;
    public TextField id;
    public TextField name;
    public TextField inventoryLevel;
    public TextField max = new TextField();
    public TextField min = new TextField();
    public TextField companyName;
    public Label companyNameLabel;
    public Label machineIdLabel;
    public TextField machineId;
    public TextField price;
    
    private ErrorService errorService = new ErrorService();
    //Dou
    /*
    public AddPartsController()
    {
        init();
    }
    
    private void init()
    {
        try
        {
            System.out.println("Trying Init");
            max.textProperty().addListener( (c, newText, oldText) -> {
                System.out.println("New Text: " + newText);
                System.out.println("Old Text: " + oldText);
            } );
        }
        catch( Exception e )
        {
            //ErrorService.openErrorScene(e.getMessage());
            System.out.println("Exception: " + e.getMessage());
            ErrorService.printStacktrace(e);
        }
        
    }
    */
    public void showMainScene( ActionEvent event )
    {
        Stage stage = VolatileMemoryService.getActiveStage();
        stage.close();
    }
    
    public void createPart( ActionEvent event )
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
                    if( Integer.parseInt(inventoryLevel.getText()) >= Integer.parseInt(min.getText()) && Integer.parseInt(inventoryLevel.getText()) <= Integer.parseInt(max.getText()) )
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
                 
                Inventory.addPart(newPart);
                //addProductToScreen( newProduct );
                System.out.println("Product Added");
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
