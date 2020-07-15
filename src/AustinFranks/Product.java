package AustinFranks;

import AustinFranks.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product
{
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int    id;
    private String name;
    private double price;
    private int    stock;
    private int    min;
    private int    max;
    
    public Product( int id,
                    String name,
                    double price,
                    int stock,
                    int min,
                    int max )
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min   = min;
        this.max   = max;
    }
    
    public void setId( int id )
    {
        this.id = id;
    }
    
    public void setName( String name )
    {
        this.name = name;
    }
    
    public void setPrice( double price )
    {
        this.price = price;
    }
    
    public void setStock( int stock )
    {
        this.stock = stock;
    }
    
    public void setMin( int  min )
    {
        this.min = min;
    }
    
    public void setMax( int max )
    {
        this.max = max;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public int getStock()
    {
        return this.stock;
    }
    
    public int getMin()
    {
        return this.min;
    }
    
    public int getMax()
    {
        return this.max;
    }
    
    public double getPrice()
    {
        return this.price;
    }
    
    public void addAssociatedPart( Part part )
    {
        if( part != null )
        {
            associatedParts.add( part );
        }
    }
    
    public Boolean deleteAssociatedPart( Part part )
    {
        Boolean isDeleted = false;
        
        try
        {
            if( associatedParts != null && associatedParts.size() > 0 )
            {
                associatedParts.remove(part);
            }
        }
        catch( Exception e )
        {
            isDeleted = false;
        }
        
        return isDeleted;
    }
    
    public ObservableList getAllAssociatedParts()
    {
        return this.associatedParts;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
