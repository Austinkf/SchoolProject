package AustinFranks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.List;

public class ModifyProductsController
{
    
    public Button      addProduct;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn partInvLevel;
    public TableColumn partPPU;
    public TextField   partSearch;
    public TableColumn aPartId;
    public TableColumn aPartName;
    public TableColumn aPartInvLevel;
    public TableColumn aPartPPU;
    public TableView   relatedPartsView;
    public TableView   partsSearchView;
    public TextField   productId;
    public TextField   name;
    public TextField   inventoryLevel;
    public TextField   price;
    public TextField   max;
    public TextField   min;
    
    ErrorService errorService = new ErrorService();
    
    Product product = null;
    Integer index   = null;
    
    ObservableList<Part> searchPartsList = FXCollections.observableArrayList();
    
    
    public ModifyProductsController( Product prod, Integer index )
    {
        if( prod != null )
        {
            this.product = prod;
        }
        
        this.index = index;
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
        
        if( product != null )
        {
            productId.setText(String.valueOf(product.getId()));
            price.setText(String.valueOf(product.getPrice()));
            inventoryLevel.setText(String.valueOf(product.getStock()));
            max.setText(String.valueOf(product.getMax()));
            min.setText(String.valueOf(product.getMin()));
            name.setText(product.getName());

            relatedPartsView.setItems(product.getAllAssociatedParts());
        }
        
    }
    
    public void updateProduct( ActionEvent event )
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
                product.setMax(Integer.parseInt(max.getText()));
                product.setMin(Integer.parseInt(min.getText()));
                product.setName(name.getText());
                product.setPrice(Double.parseDouble(price.getText()));
                product.setStock(Integer.parseInt(inventoryLevel.getText()));
                
                Inventory.updateProduct( index, product );
                MainSceneController.resetMainScene();
                openMainScene( new ActionEvent() );
            }
            else
            {
                errorService.openScene();
            }
        }
        catch( Exception e )
        {
            ErrorService.printStacktrace(e);
            ErrorService.openErrorScene("Exception: " + e.getMessage());
        }
    }
    
    public void openMainScene( ActionEvent event )
    {
        Stage stage = VolatileMemoryService.getActiveStage();
        stage.close();
    }
    
    public void addRelatedPart( ActionEvent event )
    {
        try
        {
            Part part = (Part)partsSearchView.getSelectionModel().getSelectedItem();
            
            product.getAllAssociatedParts().add(part);
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
                List<Part> partsList = Inventory.lookupPart(partSearch.getText() );
                
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
            Boolean deleted = product.deleteAssociatedPart(part);
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
