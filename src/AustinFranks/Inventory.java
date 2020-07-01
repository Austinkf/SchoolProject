package AustinFranks;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Inventory
{
    private static ObservableList<Part>    allParts;
    private static ObservableList<Product> allProducts;
    
    private static int productNextId = 0;
    private static int partNextId    = 0;
    
    static
    {
        try
        {
            allParts    = FXCollections.emptyObservableList();
            allProducts = FXCollections.emptyObservableList();
        }
        catch( Exception e )
        {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public static void addPart( Part newPart )
    {
        allParts.add( newPart );
        partNextId++;
    }
    
    public static void addProduct( Product newProduct )
    {
        allProducts.add( newProduct );
        productNextId++;
    }
    
    public static Part lookupPart( int id )
    {
        Part part = null;
        
        try
        {
        
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
        
        }
        catch( Exception e )
        {
            printException(e);
        }
        
        return product;
    }
    
    public static ObservableList<Part> lookupPart( String partName )
    {
        ObservableList<Part> partList = null;
        
        try
        {
        
        }
        catch( Exception e )
        {
            printException(e);
        }
        
        return partList;
    }
    
    public static ObservableList<Product> lookupProduct( String productName )
    {
        ObservableList<Product> productList = null;
        
        try
        {
        
        }
        catch( Exception e )
        {
            printException(e);
        }
        
        return productList;
    }
 
    public static void updatePart( int index, Part selectedPart )
    {
    
    }
    
    public static void updateProduct( int index, Product selectedProduct )
    {
    
    }
    
    public static Boolean deletePart( Part part )
    {
        return false;
    }
    
    public static Boolean deleteProduct( Product product )
    {
        return false;
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