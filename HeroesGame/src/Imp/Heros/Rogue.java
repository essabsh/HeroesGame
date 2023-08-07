package Imp.Heros;
import Imp.Tiles.Unit;
import java.util.LinkedList;
import java.util.List;
public class Rogue extends Hero implements HeroicUnit {
    private int cost;
    private int currentEnergy = 100;
    public Rogue(int x ,int y, String name , int healamount, int healthpool,int attackpoints,int defensepoints, int cost){
        super(x,y,name,healamount,healthpool,attackpoints,defensepoints,1);
        this.cost = cost;
    }


    public boolean gainEXP(int xp) {
        if(super.gainEXP(xp))
            return this.LevelUps();
        return  false;
    }

    @Override
    public List<Unit> enemiesInRange(int range) {
        List<Unit> enemies = getEnemies();
        List<Unit> inrange = new LinkedList<>();
        for (Unit e:enemies)
        {
            double currRange = Math.sqrt((Math.pow(this.getX() - e.getX(),2))+(Math.pow(this.getY() - e.getY(),2)));
            if(currRange < range){inrange.add(e);}
        }
        return inrange;
    }

    public boolean LevelUps(){
        currentEnergy = 100;
        SetAttack(GetAttack() + (3*getLevel()));
        System.out.println("Rogue bonus: Energy filled, " + 3*getLevel() + " attackpoints" );
        return true;
    }

    @Override
    public void castAbility() {
        if(currentEnergy<cost){
            System.out.println(getName() +" tried to cast Fan of Knives but there is not enough energy!" );
            return;
        }
        currentEnergy = currentEnergy - cost;
        List<Unit> inrange = enemiesInRange(2);
        if(!inrange.isEmpty()) {
            for (Unit e : inrange) {
                int def = e.Roll(0);
                int damage=GetAttack() - def;
                if (damage > 0) {
                    System.out.println(getName() +" cast Fan of Knives on " + e.getName() +" and dealt " + damage +" damage");
                    if (e.Impair(damage)){
                        gainEXP(e.getExp());
                        inrange.remove(e);
                        getEnemies().remove(e);
                    }
                    System.out.println(e.description());
                } else {
                    System.out.println(e.getName() + " Blocked Fan of Knives damage");
                }
            }
        }else{System.out.println(getName()+ "cast Fan of Knives but there is no enemies in range!");}

    }
    public String description(){
        return super.description() + " Current energy: " + currentEnergy  +"/" + 100;
    }

    public int [] Action(){
        currentEnergy = Math.min(currentEnergy+10, 100);
        return super.Action();
    }
    @Override
    public void visit(Hero hero){}
}
