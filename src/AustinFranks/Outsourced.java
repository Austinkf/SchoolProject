package AustinFranks;

public class Outsourced extends Part
{
    private String companyName;
    
    public Outsourced(int id, String name, double price, int stock, int min, int max)
    {
        super(id, name, price, stock, min, max);
    }
    
    public void setCompanyName( String companyName )
    {
        this.companyName = companyName;
    }
    
    public String getCompanyName()
    {
        return this.companyName;
    }

    public static Part getTestPart()
    {
        Outsourced testPart = new Outsourced(Inventory.getPartNextId(),"SR71",123.66, 5, 3,7);
        testPart.setCompanyName("Terminal C");
        Inventory.addPart(testPart);

        return testPart;
    }
}
