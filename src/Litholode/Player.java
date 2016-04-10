package Litholode.src.Litholode;
import java.util.Scanner;
public class Player extends Entity{
    private Place place;
    private int fuel;
    private static int fuelmax = 10;
    private int cash=0;
    private boolean alive;
    Scanner scan = new Scanner(System.in);
    public Player (String n, String desc, int s, int i, Place p){
        super (n, desc, s, i);
        place = p;
        fuel = fuelmax;
        alive = true;
    }
    public void move(Place a){
        if (fuel >0){
        place = a;
        fuel--;
        }
        else {
        System.out.println("You perish, stranded in your craft. Game Over.");   
        System.out.println("-------------------------------------------------");
        alive = false;
        }
    }
    public void battle(Enemy b){
        if(getStrength()>b.getStrength()){
        System.out.println("You win!");
        transferItems(b);
    }
        else if (getStrength()<b.getStrength()){
        alive = false;
        System.out.println("You lose the fight. Game Over");
    }
        else System.out.println("Tie battle. You leave alive but exhausted.");
    }
    public String getStatus(){
        String end="";
        
        end+=(getName()+" "+getDescription() + "\nStrength: " + getStrength() + "\nCash: " + cash  + "\nLocation: " + place.toString()  + "\nFuel level: (" + fuel + "/"+fuelmax + ")\nCoordinates: (" + place.coordinateToString() + ")\nInventory: "+ inventoryToString());
        end+="\n-------------------------------------------------";
        return end;
    }
    public void fuelUpgrade(int x){
        fuelmax +=x;
        System.out.println("More fuel, more gruel.");
    }
    public void refillFuel(){
        if (cash > (fuelmax-fuel)){
        cash-=(fuelmax-fuel);
        fuel = fuelmax;
        System.out.println("Your fuel is back to full.");
    }
        else {
        System.out.println("You're too poor for fuel.");
    }
    }
    public void mine(Item x){
        if (numItem()<getInventoryLength()){
            if(getStrength()> x.getHardness()){
            pickupitems(x);
            fuel--;
            System.out.println("You mined " + x.toString());
            }
            else System.out.println("You need a stronger drill to mine this.");
        }
        else System.out.println("Inventory full.");
    }
    public void sellItems(Player a){
        if (a.numItem()==0)
        System.out.println("Nothing to sell.");
        else{
            int totalvalue = 0;
            for(int x = 0; x<a.numItem(); x++)
                totalvalue += a.getInventory()[x].getValue();
            cash += totalvalue;
            clearItems();
                    System.out.println("You made " + totalvalue + " cash.");
        }
        System.out.println("-------------------------------------------------");
        }
    public Place getLocation(){
        return place;
    }
    public String getFuel(){
        return "("+fuel+"/"+fuelmax+")";
    }
    public boolean getAliveStatus(){
        return alive;
    }
        public void setAliveStatus(boolean x){
        alive = x;
    }
    public int getCash(){
        return cash;
    }
    public void subtractCash(int x){
        cash-=x;
    }
    public void upgradeFuel(){
        fuelmax+=5;
    }
}
