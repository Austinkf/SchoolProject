package AustinFranks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

public class AddProductsController
{
    public Button      addProduct;
    public TableColumn partId           = new TableColumn();;
    public TableColumn partName         = new TableColumn();;
    public TableColumn partInvLevel     = new TableColumn();;
    public TableColumn partPPU          = new TableColumn();;
    public TextField   partSearch;
    public TableColumn aPartId          = new TableColumn();;
    public TableColumn aPartName        = new TableColumn();;
    public TableColumn aPartInvLevel    = new TableColumn();;
    public TableColumn aPartPPU         = new TableColumn();
    public TableView   relatedPartsView = new TableView();
    public TableView   partsSearchView  = new TableView();
    public TextField   productId;
    public TextField   name;
    public TextField   inventoryLevel;
    public TextField   price;
    public TextField   max;
    public TextField   min;
    
    ErrorService errorService = new ErrorService();
    
    ObservableList<Part> searchPartsList = FXCollections.observableArrayList();
    ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();
    
    public AddProductsController()
    {
        //init();
    }
    
    public void initialize()
    {
        partId.setCellValueFactory( new PropertyValueFactory<>("id") );
        partName.setCellValueFactory( new PropertyValueFactory<>("name") );
        partInvLevel.setCellValueFactory( new PropertyValueFactory<>("stock") );
        partPPU.setCellValueFactory( new PropertyValueFactory<>("price") );
    
        aPartId.setCellValueFactory( new PropertyValueFactory<>("id") );
        aPartName.setCellValueFactory( new PropertyValueFactory<>("name") );
        aPartInvLevel.setCellValueFactory( new PropertyValueFactory<>("stock") );
        aPartPPU.setCellValueFactory( new PropertyValueFactory<>("price") );
        
        partsSearchView.setItems(searchPartsList);
        relatedPartsView.setItems(associatedPartsList);
    }
    
    public void createProduct( ActionEvent event )
    {
        try
        {
            if( name.getText().isEmpty() )
                errorService.addError("Name cannot be null");
            
            if( inventoryLevel.getText().isEmpty() )
                errorService.addError("Inventory Level cannot be null");
            
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
                Product newProduct = new Product( Inventory.getProductNextId(),
                                                  name.getText(),
                                                  Double.parseDouble(price.getText()),
                                                  Integer.parseInt(inventoryLevel.getText()),
                                                  Integer.parseInt(min.getText()),
                                                  Integer.parseInt(max.getText()) );
                
                if( associatedPartsList != null && associatedPartsList.size() > 0 )
                {
                    for( Part part : associatedPartsList )
                    {
                        newProduct.addAssociatedPart(part);
                    }
                }
                
                Inventory.addProduct(newProduct);
                //MainSceneController.resetMainScene();
                openMainScene( new ActionEvent() );
            }
            else
            {
                errorService.openScene();
            }
        }
        catch( Exception e )
        {
            ErrorService.print("Exception: " + e.getMessage());
            ErrorService.printStacktrace(e);
            ErrorService.openErrorScene("Exception: " + e.getMessage());
        }
    }
    
    public void openMainScene( ActionEvent event )
    {
        StageService.showMainScene();
    }
    
    public void addRelatedPart( ActionEvent event )
    {
        try
        {
            Part part = (Part)partsSearchView.getSelectionModel().getSelectedItem();
            
            associatedPartsList.add(part);
        }
        catch( Exception e )
        {
            ErrorService.openErrorScene(e.getMessage());
        }
    }
    
    public void lookupPart( ActionEvent event )
    {
        try
        {
            if( !partSearch.getText().isEmpty() )
            {
                List<Part> partsList = Inventory.lookupPart( partSearch.getText() );
                
                if( partsList != null && partsList.size() > 0 )
                {
                    searchPartsList = FXCollections.observableArrayList(partsList);
                    partsSearchView.getItems().setAll(searchPartsList);
                }
            }
        }
        catch( Exception e )
        {
            ErrorService.openErrorScene(e.getMessage());
        }
    }
    
    public void deleteRelatedPart( ActionEvent event )
    {
        try
        {
            Part part = (Part)relatedPartsView.getSelectionModel().getSelectedItem();
            
            if( part != null )
            {
                associatedPartsList.remove(relatedPartsView.getSelectionModel().getSelectedIndex());
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

                if( newText.matches("[0-9]*") )
                {
                    returnText = currentText;
                }
                else if( newText.toLowerCase().contains("backspace") )
                {
                    returnText = currentText.substring(0, currentText.length()-1 );
                }
                else if( newText.toLowerCase().contains("tab") )
                {
                    returnText = currentText;
                }
                else
                {
                    returnText = "";
                }
                
                tf.setText(returnText);
                
                if( tf.getText().length() > 0 )
                    tf.positionCaret(returnText.length());
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
