package Imp.Tiles;
import Imp.*;
import Imp.Heros.Hero;

public class Wall extends tile {
    public  static final char symbol = '#';
    public Wall(int x , int y){
        super(symbol,x,y);
    }
    @Override
    public void accept(Visitor v){
        v.visit(this);
    }
    @Override
    public void visit(Hero hero){}
}
