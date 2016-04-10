package Litholode.src.Litholode;

public class Entity extends NamedThing{
    private int strength;
    private Item[] inventory;
    public Entity (String nm, String desc, int s, int inventorysize){
        super (nm, desc);
        strength = s;
        inventory = new Item[inventorysize];
    }
    public int getStrength(){
        return strength;
    }

    public void transferItems(Enemy b){
        if(b.numItem()==0)
                System.out.println("You find nothing.");
        else {
            inventory[numItem()] = b.getInventory()[0];
            System.out.println("You found " + b.inventoryToString());
            }
        System.out.println("-------------------------------------------------");
    }
    public int numItem(){
        int counter= 0;
        for(int x = 0; x<inventory.length; x++)
            if(inventory[x]!=null)
            counter++;
        return counter;
    }
    public Item[] getInventory(){
        return inventory;
    }
    public void upgradeDrill(){
        strength++;
    }
    public void upgradeInventory(int x){
        Item[] upgrade = new Item[inventory.length+x];
        for (int y = 0; y<inventory.length; y++)
            upgrade[y] = inventory[y];
        inventory = upgrade;
    }
    public void pickupitems(Item x){

        inventory[numItem()] = x;
    }
    public String inventoryToString(){
        String result = "";
        for(int x = 0; x<inventory.length; x++)
            if(inventory[x]!=null)
                result += ("\n" + (x+1) + ". " + inventory[x].toString());
        if (result.equals(""))
            return "nothing";
        else return result;
    }
    public void clearItems(){
        for(int x = 0; x<inventory.length; x++)
            if (!(inventory[x] instanceof AncientKey))
                inventory[x] = null;
    }
    public void forceaddItems(Item a){
        inventory[0] = a;
    }
    public boolean hasKey(){
    for(int x = 0; x<inventory.length; x++){
            if (inventory[x] instanceof AncientKey)
                return true;
            }
    return false;            
    }
    public int getInventoryLength(){
        return inventory.length;
    }
}