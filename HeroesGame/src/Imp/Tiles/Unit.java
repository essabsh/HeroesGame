package Imp.Tiles;
import static java.lang.Math.sqrt;
public abstract class Unit extends tile {

    private String Name;
    private int Health_pool;
    private int Health_amount;
    private int AttackPoints;
    private int DefensePoints;
    private int XP;

    public Unit(String name, int healthpool,int healthamount,int attackpoints,int defensepoints ,int x, int y, char symbol,int xp){
        super(symbol,x,y);
        this.Name = name;
        this.Health_pool = healthpool;
        this.Health_amount = healthamount;
        this.AttackPoints = attackpoints;
        this.DefensePoints = defensepoints;
        this.XP = xp;

    }
    public int getExp(){return XP;}
    public String description() {
        return String.format("%s: ---> \t\tHealth: %s\t/\t%s\t\tAttack: %d\t\tDefense: %d", getName(), GetHealthAmount(),GetHealthPool(), GetAttack(), GetDefense());
    }

    public String getName(){return Name;}
    public int GetHealthPool(){return  Health_pool;}
    public void SetHealthPool(int HealthpPool){this.Health_pool = HealthpPool;}
    public int GetHealthAmount(){return Health_amount;}
    public void SetHealthAmount(int health){this.Health_amount = health;}
    public int GetAttack(){return AttackPoints;}
    public void SetAttack(int attack){this.AttackPoints = attack;}
    public int GetDefense(){return DefensePoints;}
    public void SetDefense(int def){this.DefensePoints = def;}
    public abstract int [] Action();
    public String toString(){
        return super.toString();
    }
    public  int Roll(int a){
        if(a == 1) {
            int rolled = (int) (Math.random() * (GetAttack() + 1));
            System.out.println(Name + " has rolled " + rolled + " to Attack.");
            return rolled;
        }
        else {
            int rolled = (int) (Math.random() * (GetDefense() + 1));
            System.out.println(Name + " has rolled " + rolled + " to Defend.");
            return rolled;
        }
    }
    public boolean Impair(int amount) {
        Health_amount = Health_amount - amount;
        if (Health_amount > 0) {
            return false;
        }
        Health_amount = 0;
        System.out.println(Name + " has died!.");
        return true;
    }
    public void engage(Unit opponent){
        System.out.println(Name + " engaged " + opponent.getName() + " to battle");
        int attackerDamage = this.Roll(1);
        int attackedDefense = opponent.Roll(0);
        int damage=attackerDamage - attackedDefense;
        if(damage > 0){
            System.out.println(Name +" dealt damage equal to " + damage + " to " + opponent.getName() + ".");
            opponent.Impair(damage);
            System.out.println(opponent.description());
            return;
        }
        else{System.out.println(opponent.getName() +" blocked the damage");}
        System.out.println(opponent.description());
    }
    public double range(Unit other){
        int p = ((this.getX()-other.getX())*(this.getX()-other.getX()));
        int q = ((this.getY()-other.getY())*(this.getY()-other.getY()));
     return sqrt((p+q));
    }
}
