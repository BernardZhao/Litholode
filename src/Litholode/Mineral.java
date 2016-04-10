package Litholode.src.Litholode;

public class Mineral extends Item{
    private int value;
    public Mineral(String n, String d, int v, int h){
        super(n, d, h);
        value = v;
    }
    public int getValue(){
        return value;
    }
}