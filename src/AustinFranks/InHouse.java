package AustinFranks;

public class InHouse extends Part
{
    private int machineId;
    
    public InHouse(int id, String name, double price, int stock, int min, int max)
    {
        super(id, name, price, stock, min, max);
    }
    
    public void setMachineId( int machineId )
    {
        this.machineId = machineId;
    }
    
    public int getMachineId()
    {
        return this.machineId;
    }

    public static Part getTestPart()
    {
        InHouse testPart = new InHouse(Inventory.getPartNextId(),"MFP",249.99, 3, 1,5);
        testPart.setMachineId(52244);

        Inventory.addPart(testPart);

        return testPart;
    }
}
