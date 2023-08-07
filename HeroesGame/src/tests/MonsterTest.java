package tests;

import Imp.Enemies.*;
import Imp.Heros.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
    private Monster monster;
    private Warrior warrior;

    public MonsterTest(){}
    @BeforeEach
    void setUp() {
        warrior = new Warrior(1,1,"Alexander",100,100,50,10,3);
        monster = new Monster("monster",20,20,10,15,3,1,'m',10,5,warrior);
    }

    @Test
    void action() {
        int[] actual = (monster.Action());
        assertEquals(2,actual[0]);
    }
}