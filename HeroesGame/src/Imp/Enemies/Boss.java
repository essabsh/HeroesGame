package Imp.Enemies;
import Imp.Heros.Hero;
import Imp.Heros.HeroicUnit;
import Imp.Tiles.*;

    public class Boss extends Monster implements HeroicUnit {
        private int abilityfrequency;
        private int Ticks = 0;

        public Boss(String Name, int HealthPool, int HealthAmout, int AttackPoint, int DefensePoint, int x, int y, char symbol, int ExperienceValue, int VisionRange, Unit Hero, int Freq) {
            super(Name, HealthPool, HealthAmout, AttackPoint, DefensePoint, x, y, symbol, ExperienceValue, VisionRange, Hero);
            this.abilityfrequency = Freq;

        }

        @Override
        public int[] Action() {
            int[] position = new int[2];
            position[0] = getX();
            position[1] = getY();
            if (this.range(getHero()) < getVisionRange()) {
                if (Ticks != abilityfrequency) {
                    Ticks += 1;
                    return super.Action();
                } else {
                    Ticks = 0;
                    this.castAbility();
                    return position;
                }
            } else {
                Ticks = 0;
                return super.Action();
            }
        }

        @Override
        public void castAbility() {
            System.out.println("Boss " + getName() + " cast an ability!");
            int res = GetAttack() - getHero().Roll(0);
            if (res > 0) {
                System.out.println("Boss " + getName() + " dealt " + GetAttack() + " damage to " + getHero().getName());
                getHero().Impair(GetAttack());
            } else {
                System.out.println(getHero().getName() + " blocked damage from the ability ");
            }
        }
        @Override
        public void visit(Hero hero){}
    }

