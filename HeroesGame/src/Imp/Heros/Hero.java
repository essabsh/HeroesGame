package Imp.Heros;
import Imp.Tiles.*;
import Imp.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Imp.Enemies.*;
public abstract class Hero extends Unit implements HeroicUnit {
    protected static char symbol = '@';
    public int Experience = 0;
    public int HeroLevel = 1;
    private Scanner scannner = new Scanner(System.in);
    private List<Unit> ingameEnemy = new LinkedList();
    public Hero(int x, int y, String name, int healthamount, int healthpool, int attackpoints, int defensepoints,int xp) {
        super(name, healthpool, healthamount, attackpoints, defensepoints, x, y, symbol,xp);
    }
    public abstract List<Unit> enemiesInRange(int range);

    public void addEnemy(Unit enemy){ingameEnemy.add(enemy);}
    public List<Unit> getEnemies(){return ingameEnemy;}

    public int [] Action(){
        int [] cordinates = new int[2];
        cordinates[0] = getX();
        cordinates[1] = getY();
        char input = scannner.next().charAt(0);
        switch (input){
            case 'w':
                cordinates[1] = getY()-1;; break;
            case 's':
                cordinates[1] = getY()+1;;break;
            case 'a':
                cordinates[0] = getX()-1;break;
            case 'd':
                cordinates[0] = getX()+1;break;
            case 'e':
                castAbility();break;
            default:
                return cordinates;
        }
        return cordinates;
    }


    public int getLevel() {
        return HeroLevel;
    }

    public String description() {
        return super.description() + "  Level: " + HeroLevel + "   Experience: " + Experience;
    }

    public boolean LevelUp() {
        int milestone = HeroLevel * 50;
        if (Experience >= milestone) {
            Experience -= milestone;
            HeroLevel += 1;
            SetHealthPool(GetHealthPool() + (10 * HeroLevel));
            SetHealthAmount(GetHealthPool());
            SetAttack(GetAttack() + (4 * HeroLevel));
            SetDefense(GetDefense() + HeroLevel);
            System.out.println(getName() + " levelled up ,, the new Level is " + HeroLevel +
                    " stats gained " + 10 * HeroLevel + " Healthpool, "
                    + 4 * GetAttack() + " AttackPoints, " + HeroLevel + " DefensePoints.");
            return true;
        }
        return false;
    }

    public boolean gainEXP(int xp) {
        this.Experience += xp;
        return this.LevelUp();
    }
    public void accept(Visitor v) {v.visit(this); }

    public void visit(Enemy opponent) {
        this.engage(opponent);
        if (opponent.getHealthAmount() <= 0) {
            gainEXP(opponent.getExp());
            int x=this.getX();
            int y=this.getY();
            this.setX(opponent.getX());
            this.setY(opponent.getY());
            opponent.setX(x);
            opponent.setY(y);
        }
    }
    public String toString() {
        if (GetHealthAmount() == 0) return "X";
        return super.toString();
    }
    @Override
    public void visit(Hero hero){}
}
