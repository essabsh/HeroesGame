package Imp.Heros;

import Imp.Tiles.*;
import java.util.*;

public class Warrior extends Hero implements HeroicUnit {
    private int AbilityCD;
    private int Cooldown = 0;
    public Warrior(int x , int y, String name , int healamount, int healthpool,int attackpoints, int defensepoints, int abilityCD){
        super(x,y,name,healamount,healthpool,attackpoints,defensepoints,1);
        this.AbilityCD = abilityCD;
    }

    @Override
    public void castAbility(){
        if(Cooldown !=0){
            System.out.println(getName() + " tried to cast Avenger's Shield," + " Ability is on cooldown , CD = " + Cooldown + " Game ticks");
            return;
        }
        Cooldown = AbilityCD;
        int healingamt = Math.min(GetHealthAmount() + (10*GetDefense()) , GetHealthAmount());
        int prev = GetHealthAmount();
        SetHealthAmount(healingamt);
        System.out.println(getName() + " has cast Avenger's Shield and healed for " + (healingamt-prev));
        double damageinit = (GetHealthAmount() * 0.1);
        List<Unit> inrange = enemiesInRange(3);
        if(!inrange.isEmpty()) {
            Unit e = inrange.get((int) (Math.random() * (inrange.size())));
            int def = e.Roll(0);
            int damage = (int)(damageinit - def);
            if(damage > 0) {
                System.out.println(getName() + " dealt " + damage + " damage to " + e.getName());
                if (e.Impair(damage)) {
                    this.gainEXP(e.getExp());
                    inrange.remove(e);
                    getEnemies().remove(e);
                }
                System.out.println(e.description());
            }
        }

    }

    @Override
    public List<Unit> enemiesInRange(int range){
        List<Unit> enemies = getEnemies();
        List<Unit> inrange = new LinkedList<>();
        for (Unit e:enemies)
        {
            double currRange = Math.sqrt((Math.pow(this.getX() - e.getX(),2))+(Math.pow(this.getY() - e.getY(),2)));
            if(currRange < range){inrange.add(e);}
        }
        return inrange;
    }

    public boolean LevelUps()
    {
        Cooldown = 0;
        SetHealthPool(GetHealthPool() + (5*getLevel()));
        SetAttack(GetAttack() + (2*getLevel()));
        SetDefense(GetDefense() + getLevel());
        System.out.println(" Warrior Bouns: " + 5*getLevel() + " Healthpool " + 2*getLevel() + " Attackpoints " + getLevel() + " Defensepoints ");
        return true;
    }
    public boolean gainEXP(int Exp){
        if(super.gainEXP(Exp))
            return LevelUps();
        return false;
    }

    public String description(){
        return super.description() + " SA Cooldown: " + Cooldown;
    }

    public int [] Action(){
        Cooldown = Cooldown - 1;
        if (Cooldown < 0) Cooldown = 0;
        return super.Action();
    }

    @Override
    public void visit(Hero hero){}
}
