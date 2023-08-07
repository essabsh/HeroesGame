package Imp.Heros;
import Imp.Tiles.Unit;
import java.util.LinkedList;
import java.util.List;

public class Hunter extends Hero implements HeroicUnit {
    private int range;
    private int arrowscount = (getLevel()*10);
    private int tickscount = 0;
    public Hunter(int x, int y, String name , int healthamount,int healthpool,int attackpoints,int defensepoints,int range){
        super(x,y,name,healthamount,healthpool,attackpoints,defensepoints,1);
        this.range = range;
    }


    public String description(){
        return  super.description() + " arrowsCount :" + arrowscount +" ticksCount: " + tickscount +" range: " + range;
    }
    public boolean gainEXP(int xp) {
        if(super.gainEXP(xp))
            return this.LevelUps();
        return  false;
    }

    public boolean LevelUps(){
        arrowscount = arrowscount +(10*getLevel());
        SetAttack(GetAttack()+(2*getLevel()));
        SetDefense(GetDefense()+getLevel());
        System.out.println("Hunter bonus: " + 10*getLevel() + " Arrows" + 2*getLevel() + " Attackpoints" + getLevel() +" Defensepoints");
        return true;
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

    public int[] Action(){
        if(tickscount == 10){
            arrowscount = arrowscount + getLevel();
            tickscount = 0;
        }else{tickscount +=1;}
        return super.Action();
    }

    @Override
    public void castAbility() {
        if(arrowscount == 0){System.out.println(getName() +" tried to cast Shoot but there is not enough arrows!"); return;}
        arrowscount = arrowscount - 1;
        List<Unit> allenms = enemiesInRange(range);
        double [] distances = new double[allenms.size()];
        int idx = 0;
        if(!allenms.isEmpty()) {
            for (Unit e : allenms) {
                double range = Math.sqrt((Math.pow(this.getX() - e.getX(), 2)) + (Math.pow(this.getY() - e.getY(), 2)));
                distances[idx] = range;
                idx += 1;
            }
            idx = MinIdx(distances);
            Unit toAttack = allenms.get(idx);
            int def = toAttack.Roll(0);
            int damage=GetAttack() - def;
            if (damage > 0) {
                System.out.println(getName() + " cast Shoot and shot " + toAttack.getName() + " for " +damage +" damage");
                if (toAttack.Impair(damage)) {
                    gainEXP(toAttack.getExp());
                    allenms.remove(toAttack);
                    getEnemies().remove(toAttack);
                }
                System.out.println(toAttack.description());
            }else{System.out.println(getName() +" cast Shoot but " +toAttack.getName() +" blocked the damage");}
        }else{System.out.println(getName() + " cast Shoot but there is no enemy in range");}

    }



    public int MinIdx(double [] arr){
        int MinIdx = 0;
        for (int i = 0; i < arr.length ; i++){
            if(arr[MinIdx]>arr[i]){
                MinIdx = i;
            }
        }
        return MinIdx;
    }
    @Override
    public void visit(Hero hero){}
}
