package Litholode.src.Litholode;

public abstract class Item extends NamedThing{
    private int hardness;
    public Item(String n, String d, int h){
        super(n, d);
        hardness = h;
    }
    public int getHardness(){
        return hardness;
    }
    public abstract int getValue();
    public String toString(){
        return super.toString();
    }
}
