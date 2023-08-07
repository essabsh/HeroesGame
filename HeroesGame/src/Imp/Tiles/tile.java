package Imp.Tiles;
import Imp.*;
import Imp.Enemies.*;
import Imp.Heros.*;
import Imp.Visitor;
public abstract class tile implements Visitor{

    private char tile;
    private int [] Position = new int [2];
    protected tile(char t, int x , int y) {
        this.tile = t;
        Position[0] = x;
        Position[1] = y;
    }
    //------------getters & setters--------------------
    public int getX(){return Position[0];}
    public int getY(){return Position[1];}
    public void setX(int x){Position[0] = x;}
    public void setY(int y){Position[1] = y;}
    //-----------------------------------------------
    @Override
    public void visit(Empty empty){
        int tempX= empty.getX();
        int tempY= empty.getY();
        empty.setX(Position[0]);
        empty.setY(Position[1]);
        this.setX(tempX);
        this.setY(tempY);
    }
    @Override
    public void visit(Wall wall){
        //visiting a wall does nothing
    }
    @Override
    public void visit(Enemy enemy){}
    @Override
    public void accept(Visitor visitor){}
    public void communicate(tile tile){
        tile.accept(this);
    }
    public String toString(){
        return String.valueOf(tile);
    }



}
