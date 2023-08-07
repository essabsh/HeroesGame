package Imp.Enemies;
import Imp.Tiles.*;
import Imp.Visitor;
import Imp.Heros.*;

public abstract class Enemy extends Unit {
    private Unit hero;

    public Enemy(String name, int healthpool, int healthamount, int attackpoints, int defensepoints, int x, int y, char symbol,int experienceValue, Unit hero) {
        super(name, healthpool, healthamount, attackpoints, defensepoints, x, y, symbol,experienceValue);
        this.hero = hero;
    }
    public abstract int [] Action();
    public int getHealthAmount() {
        return super.GetHealthAmount();
    }

    public Unit getHero(){return hero;}
    public void accept(Visitor v){v.visit(this);}

}

