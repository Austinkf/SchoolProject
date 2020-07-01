package AustinFranks;

import AustinFranks.Part;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Product
{
    private ObservableList<Part> associatedParts;
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
        return this.min;
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
                Map<Integer,Part> partMap = new HashMap<Integer,Part>();
                
                for ( Part p : associatedParts )
                {
                    if( part.getId() != p.getId() )
                    {
                        partMap.put(p.getId(),p);
                    }
                }
                
                associatedParts = (ObservableList<Part>) new ArrayList<Part>(partMap.values());
                isDeleted = true;
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
