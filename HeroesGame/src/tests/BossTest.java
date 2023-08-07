package tests;

import Imp.Enemies.Boss;
import Imp.Heros.Warrior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BossTest {
    private Warrior warrior;
    private Warrior warrior2;
    private Boss boss;
    private Boss boss2;
    public BossTest(){}
    @BeforeEach
    void setUp() {
        warrior = new Warrior(1,1,"hero",100,100,50,10,3);
        warrior2 = new Warrior(2,3,"hero2",90,100,50,10,3);
        boss = new Boss("boss",100,100,20,3,1,0,'b',12,4,warrior,3);
        boss2 = new Boss("boss2",100,100,30,3,1,0,'b',12,4,warrior2,3);
    }

    @Test
    void castAbility() {
        boss.castAbility();
        assertEquals(80,warrior.GetHealthAmount());
        boss.castAbility();
        assertEquals(60,warrior.GetHealthAmount());
        boss2.castAbility();
        assertEquals(60,warrior2.GetHealthAmount());
        boss2.castAbility();
        assertEquals(30,warrior2.GetHealthAmount());
    }
}