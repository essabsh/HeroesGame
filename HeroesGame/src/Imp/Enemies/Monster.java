package Imp.Enemies;
import Imp.Heros.Hero;
import Imp.Tiles.Unit;

public class Monster extends Enemy{
    private int visionRange;
//consturctor
    public Monster(String name, int healthpool, int healthamount, int attackpoints, int defensepoints, int x, int y, char symbol, int experienceValue,int visionRange, Unit hero) {
        super(name, healthpool, healthamount, attackpoints, defensepoints, x, y, symbol, experienceValue, hero);
        this.visionRange = visionRange;
    }

    public int getVisionRange(){return visionRange;}
    @Override
    public int [] Action(){
        int[] Postion = new int[2];
        int x,y;
        Postion[0] = getX();
        Postion[1]= getY();
        Unit Hero = getHero();
        double Range=this.range(Hero);
        if(Range<=(double)visionRange){
            x=this.getX()- Hero.getX();
            y=this.getY()-Hero.getY();
            int tmpX=x,tmpY=y;
            if(x<0)
                tmpX=x*-1;
            if(y<0)
                tmpY=y*-1;
            if(tmpX>tmpY){
                if(x>0)
                    Postion=this.move(Movements.LEFT,Postion);
                else
                    Postion=this.move(Movements.RIGHT,Postion);
            }
            else{
                if(y>0)
                    Postion=this.move(Movements.UP,Postion);
                else
                    Postion=this.move(Movements.DOWN,Postion);
            }
        }
        else{
            int randomize=(int)(Math.random()*(4+1));
            switch (randomize){
                case 0: Postion=this.move(Movements.NONE,Postion);
                    break;
                case 1: Postion=this.move(Movements.LEFT,Postion);
                    break;
                case 2: Postion=this.move(Movements.RIGHT,Postion);
                    break;
                case 3: Postion=this.move(Movements.UP,Postion);
                    break;
                case 4: Postion=this.move(Movements.DOWN,Postion);
                    break;
            }

        }
        return Postion;
    }
    public int[] move(Movements direction,int [] Postion){
        switch (direction){
            case LEFT: Postion[0] = Postion[0] - 1;
                break;
            case DOWN: Postion[1] = Postion[1] + 1;
                break;
            case UP: Postion[1] = Postion[1] - 1;
                break;
            case RIGHT: Postion[0] = Postion[0] + 1;
                break;
            case NONE: break;
        }
        return Postion;
    }

    @Override
    public void visit(Hero hero){}
}