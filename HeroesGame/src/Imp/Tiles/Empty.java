package Imp.Tiles;
import Imp.Heros.Hero;
import Imp.Visitor;

public class Empty extends tile {
    public static final char symbol = '.';
    public Empty(int x , int y){
        super(symbol, x, y);
    }
    @Override
    public void visit(Hero hero){}
    public void accept(Visitor v){v.visit(this);}
}
