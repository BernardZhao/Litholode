package Litholode.src.Litholode;

public class Place extends NamedThing{
    private Item whateverishere = null;
    private Enemy enemy = null;
    private boolean hasEnemy=false;
    private boolean hasItem=false;
    private int xcoordinate;
    private int ycoordinate;
    private boolean isShip;
    public Place(String n, String d, Item m, Enemy e, boolean s, int x, int y){
        super(n, d);
        whateverishere = m;
        enemy = e;
        isShip = s;
        xcoordinate = x;
        ycoordinate = y;
        hasEnemy = true;
        hasItem = true;
    }
    public Place(String n, String d, Enemy e, boolean s, int x, int y){
        super(n, d);
        enemy = e;
        isShip = s;
        hasEnemy = true;
        xcoordinate = x;
        ycoordinate = y;
    }
    public Place(String n, String d, Item m, boolean s, int x, int y){
        super(n, d);
        whateverishere = m;
        isShip = s;
        hasItem = true;
        xcoordinate = x;
        ycoordinate = y;
    }
    public Place(String n, String d, boolean s, int x, int y){
        super(n, d);
        isShip = s;
        xcoordinate = x;
        ycoordinate = y;
    }
    public boolean isitShip(){
        return isShip;
    }
    public int getXCoordinate(){
        return xcoordinate;
    }
    public int getYCoordinate(){
        return ycoordinate;
    }
    public String coordinateToString(){
        return (xcoordinate + "," + ycoordinate);
    }
    public boolean enemyexists(){
        return hasEnemy;
    }
    public boolean itemexists(){
        return hasItem;
    }
    public String toString(){
        return getName();
    }
    public Enemy getEnemy(){
        return enemy;
    }
    public Item getItem(){
        return whateverishere;
    }
        
}