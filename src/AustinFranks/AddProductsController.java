package AustinFranks;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class AddProductsController
{
    public Button addProduct;
    public TableColumn partId;
    public TableColumn partName;
    public TableColumn partInvLevel;
    public TableColumn partPPU;
    public TextField partSearch;
    public TableColumn aPartId;
    public TableColumn aPartName;
    public TableColumn aPartInvLevel;
    public TableColumn aPartPPU;
    public TableView relatedPartsView;
    public TableView partsSearchView;
    ErrorService errorService = new ErrorService();
    public TextField productId;
    public TextField name;
    public TextField inventoryLevel;
    public TextField price;
    public TextField max;
    public TextField min;
    
    public AddProductsController()
    {
        //init();
    }
    
    
    
    public void createProduct( ActionEvent event )
    {
        System.out.println("Pressed");
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
                Inventory.addProduct(newProduct);
                MainSceneController.resetMainScene();
                openMainScene( new ActionEvent() );
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
    
    public void openMainScene( ActionEvent event )
    {
        StageService.showMainScene();
    }

    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
