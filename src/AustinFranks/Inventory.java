package AustinFranks;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.util.HashMap;
import java.util.Map;

public class Inventory
{
    private static ObservableList<Part>    allParts;
    private static ObservableList<Product> allProducts;
    private static Map<Integer,Part>       allPartsMap;
    private static Map<Integer,Product>    allProductsMap;
    
    private static int productNextId = 0;
    private static int partNextId    = 0;
    
    static
    {
        try
        {
            allParts       = FXCollections.observableArrayList();
            allProducts    = FXCollections.emptyObservableList();
            allPartsMap    = new HashMap<Integer,Part>();
            allProductsMap = new HashMap<Integer,Product>();
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage());
            ErrorService.printStacktrace(e);
        }
    }
    
    public static void addPart( Part newPart )
    {
        try
        {
            allParts.add( newPart );
            allPartsMap.put( newPart.getId(), newPart );
            partNextId++;
        }
        catch( Exception e )
        {
            ErrorService.print("Exception: " + e.getMessage());
            ErrorService.printStacktrace(e);
        }
    }
    
    public static void addProduct( Product newProduct )
    {
        allProducts.add( newProduct );
        allProductsMap.put( newProduct.getId(), newProduct );
        productNextId++;
    }
    
    public static Part lookupPart( int id )
    {
        Part part = null;
        
        try
        {
            if( allPartsMap.containsKey(id) )
            {
                part = allPartsMap.get(id);
            }
        }
        catch( Exception e )
        {
            printException(e);
        }
        
        return part;
    }
    
    public static Product lookupProduct( int id )
    {
        Product product = null;
        
        try
        {
            if( allProductsMap.containsKey(id) )
            {
                product = allProductsMap.get(id);
            }
        }
        catch( Exception e )
        {
            printException(e);
        }
        
        return product;
    }
    
    public static ObservableList<Part> lookupPart( String partName )
    {
        ObservableList<Part> partList = FXCollections.observableArrayList();
        
        try
        {
            for( Part part : allParts )
            {
                if( part.getName().contains(partName) )
                {
                    partList.add(part);
                }
            }
        }
        catch( Exception e )
        {
            printException(e);
        }
        
        return partList;
    }
    
    public static ObservableList<Product> lookupProduct( String productName )
    {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        
        try
        {
            for( Product prod : allProducts )
            {
                if( prod.getName().contains(productName) )
                {
                    productList.add(prod);
                }
            }
        }
        catch( Exception e )
        {
            printException(e);
        }
        
        return productList;
    }
 
    public static void updatePart( int index, Part selectedPart )
    {
        try
        {
            allParts.set(index, selectedPart);
            allPartsMap.put(selectedPart.getId(),selectedPart);
        }
        catch( Exception e )
        {
            ErrorService.print("Exception: " + e.getMessage());
        }
    }
    
    public static void updateProduct( int index, Product selectedProduct )
    {
        try
        {
            allProducts.set(index, selectedProduct);
            allProductsMap.put(selectedProduct.getId(),selectedProduct);
        }
        catch( Exception e )
        {
            ErrorService.print("Exception: " + e.getMessage());
        }
    }
    
    public static Boolean deletePart( Part part )
    {
        Boolean isDelete = false;

        try
        {
            allPartsMap.remove(part.getId());
            allParts = FXCollections.observableArrayList(allPartsMap.values());

            isDelete = true;
        }
        catch( Exception e )
        {
            ErrorService.print("Exception: " + e.getMessage());
        }
        return isDelete;
    }
    
    public static Boolean deleteProduct( Product product )
    {
        Boolean isDelete = false;

        try
        {
            allProductsMap.remove(product.getId());
            allProducts = FXCollections.observableArrayList(allProductsMap.values());

            isDelete = true;
        }
        catch( Exception e )
        {
            ErrorService.print("Exception: " + e.getMessage());
        }
        return isDelete;
    }
    
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }
    
    
    public static int getProductNextId()
    {
        return productNextId;
    }
    
    public static int getPartNextId()
    {
        return partNextId;
    }
    
    
    
    
    
    private static void printException( Exception e )
    {
        if( e != null )
        {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    
}