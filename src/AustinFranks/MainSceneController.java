package AustinFranks;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainSceneController
{
    public SplitPane mainSceneView;
    public Button exitApplicationButton;
    public Button addButton;
    public Button modifyButton;
    public Button deleteButton;
    public Button searchButton;
    public TextField searchField;
    public Button partsAddButton;
    public Button partsModifyButton;
    public Button partsDeleteButton;
    public Button partSearchButton;
    public TextField productsSearchButton;
    public Button addProductButton;
    public Button modifyProductButton;
    public Button deleteProductButton;
    public TableColumn productId        = new TableColumn();
    public TableColumn productName      = new TableColumn();
    public TableColumn productInvLevel  = new TableColumn();
    public TableColumn productPPU       = new TableColumn();
    public TableView   partsList        = new TableView();
    public TableColumn partId           = new TableColumn();
    public TableColumn partName         = new TableColumn();
    public TableColumn partInvLevel     = new TableColumn();
    public TableColumn partPPU          = new TableColumn();
    public TableView   productTableView = new TableView();
    public TextField partSearch;
    public TextField productSearch;
    
    Map<String,Object>      props            = new HashMap<String,Object>();
    ObservableList<Part>    partsTableList   = FXCollections.observableArrayList();
    ObservableList<Product> productTableList = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
        InHouse.getTestPart();
        Outsourced.getTestPart();
        partId.setCellValueFactory( new PropertyValueFactory<>("id") );
        partName.setCellValueFactory( new PropertyValueFactory<>("name") );
        partInvLevel.setCellValueFactory( new PropertyValueFactory<>("stock") );
        partPPU.setCellValueFactory( new PropertyValueFactory<>("price") );

        productId.setCellValueFactory( new PropertyValueFactory<>("id") );
        productName.setCellValueFactory( new PropertyValueFactory<>("name") );
        productInvLevel.setCellValueFactory( new PropertyValueFactory<>("stock") );
        productPPU.setCellValueFactory( new PropertyValueFactory<>("price") );

        //System.out.println(partsTableList.size());
        refreshListViews();
    }

    public void openAddProductscene(ActionEvent event )
    {
        props.put("height", 633);
        props.put("width", 1020);
        try
        {
            StageService.showScene("AddProducts.fxml", props, false);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }

    public void openModifyProductScene(ActionEvent event )
    {
        //props.put("height", 768);
        //props.put("width", 1200);
        try
        {
            Product prod  = (Product)productTableView.getSelectionModel().getSelectedItem();
            if( prod != null )
            {
                Integer index = productTableView.getSelectionModel().getSelectedIndex();
    
                FXMLLoader load = new FXMLLoader(getClass().getResource("ModifyProducts.fxml") );
    
                load.setControllerFactory(c -> {
                    return new ModifyProductsController(prod, index);
                });
    
                Parent root = load.load();
                Scene scene = new Scene(root, 1020, 633);
                Stage stage = new Stage();
    
                stage.initOwner(VolatileMemoryService.getPrimaryStage());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                VolatileMemoryService.setActiveStage(stage);

                stage.show();
            }
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }
    
    public void openAddPartScene(ActionEvent event )
    {
        props.put("height", 500);
        props.put("width", 450);
        try
        {
            StageService.showScene("AddParts.fxml", props, true);
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }
    
    public void openModifyPartScene(ActionEvent event )
    {
        props.put("height", 500);
        props.put("width", 450);
        try
        {
            InHouse    ihPart = null;
            Outsourced osPart = null;
            Integer    index  = partsList.getSelectionModel().getSelectedIndex();
            
            if( partsList.getSelectionModel().getSelectedItem().getClass().getSimpleName().equals("InHouse") )
            {
                ihPart = (InHouse)partsList.getSelectionModel().getSelectedItem();
            }
            else
            {
                osPart = (Outsourced)partsList.getSelectionModel().getSelectedItem();
            }
            
            FXMLLoader load        = new FXMLLoader(getClass().getResource("ModifyParts.fxml") );
            InHouse    finalIhPart = ihPart;
            Outsourced finalOsPart = osPart;
            
            load.setControllerFactory(c -> {
                return new ModifyPartsController(finalIhPart, finalOsPart, index);
            });

            Parent root = load.load();
            Scene scene = new Scene(root, 450, 500);
            Stage stage = new Stage();

            stage.initOwner(VolatileMemoryService.getPrimaryStage());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            VolatileMemoryService.setActiveStage(stage);

            stage.show();

        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage() );
        }
    }

    public void closeApplication( ActionEvent event )
    {
        Stage stage = VolatileMemoryService.getPrimaryStage();
        stage.close();
    }

    public void refreshListViews()
    {
        partsTableList   = FXCollections.observableArrayList();
        productTableList = FXCollections.observableArrayList();

        //partsTableList.addAll(Inventory.getAllParts());
        //productTableList.addAll(Inventory.getAllProducts());

        partsList.getItems().setAll(partsTableList);
        productTableView.getItems().setAll(productTableList);
    }

    public static void resetMainScene()
    {
        try
        {
            MainSceneController controller = VolatileMemoryService.getMainController();
            if( controller != null )
            {
                controller.refreshListViews();
            }
        }
        catch( Exception e )
        {
            ErrorService.print("Exception: " + e.getMessage());
        }
    }
    
    public void lookupPart( ActionEvent event )
    {
        try
        {
            if( !partSearch.getText().isEmpty() )
            {
                List<Part> tempList = Inventory.lookupPart(partSearch.getText() );
                
                if( tempList != null && tempList.size() > 0 )
                {
                    partsTableList = FXCollections.observableArrayList(tempList);
                    partsList.getItems().setAll(partsTableList);
                }
            }
        }
        catch( Exception e )
        {
            ErrorService.openErrorScene(e.getMessage());
        }
    }
    
    public void lookupProduct( ActionEvent event )
    {
        try
        {
            if( !productSearch.getText().isEmpty() )
            {
                List<Product> tempList = Inventory.lookupProduct( productSearch.getText() );
                
                if( tempList != null && tempList.size() > 0 )
                {
                    productTableList = FXCollections.observableArrayList(tempList);
                    productTableView.getItems().setAll(productTableList);
                }
            }
        }
        catch( Exception e )
        {
            ErrorService.openErrorScene(e.getMessage());
        }
    }
    
    public void deletePart( ActionEvent event )
    {
        try
        {
            Part part = (Part)partsList.getSelectionModel().getSelectedItem();
            
            if( part != null )
            {
                Boolean isDeleted =  Inventory.deletePart(part);
                
                if( isDeleted )
                {
                    partsTableList.remove(part);
                    partsList.getItems().setAll(partsTableList);
                }
            }
        }
        catch( Exception e )
        {
            ErrorService.openErrorScene("Exception: " + e.getMessage());
        }
    }
    
    public void deleteProduct( ActionEvent event )
    {
        try
        {
            Product product = (Product) productTableView.getSelectionModel().getSelectedItem();
            
            if( product != null )
            {
                Boolean isDeleted = Inventory.deleteProduct(product);

                if( isDeleted )
                {
                    productTableList.remove(product);
                    productTableView.getItems().setAll(productTableList);
                }
            }
        }
        catch( Exception e )
        {
            ErrorService.openErrorScene("Exception: " + e.getMessage());
        }
    }
}
