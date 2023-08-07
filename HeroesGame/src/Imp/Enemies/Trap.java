package Imp.Enemies;
import Imp.Heros.Hero;
import Imp.Tiles.Unit;

public class Trap extends Enemy{
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount=0;
    private boolean visible=true;
    public Trap(String Name, int HealthPool, int HealthAmount, int AttackPoints, int DefensePoints, int x, int y, char symbol, int EperienceValue,int VisibilityTime,int InvisibilityTime, Unit Hero) {
        super(Name, HealthPool, HealthAmount, AttackPoints, DefensePoints, x, y, symbol, EperienceValue,Hero);
        this.visibilityTime=VisibilityTime;
        this.invisibilityTime=InvisibilityTime;
    }


    @Override
    public int[] Action(){
        if(!visible){
            visible=false;
            ticksCount=0;

            if(visibilityTime!=ticksCount){
                ticksCount++;
            }
            else{
                visible=false;
                ticksCount=0;
            }
        }
        else{
            if(invisibilityTime!=ticksCount){
                ticksCount++;

            }
            else{
                visible=true;
                ticksCount=0;
            }

        }
        if(this.range(getHero())<2)
        {
            this.engage(getHero());
        }
        int [] position = new int [2];
        position[0] = getX();
        position[1] = getY();
        return position;
    }


    public String toString(){
        if(visible) return super.toString();
        return String.valueOf('.');
    }

    @Override
    public void visit(Hero hero){}

}
