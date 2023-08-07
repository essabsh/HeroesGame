package tests;

import Imp.Enemies.*;
import Imp.Heros.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {
    private Hunter hunter;
    private Monster monster;
    public HunterTest(){}
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        hunter= new Hunter(0,0,"hunter",100,100,20,20,5);
        monster= new Monster("monster",30,30,10,0,2,2,'m',100,5,hunter);
        hunter.addEnemy(monster);
    }
    @Test
    void castAbility(){
        hunter.castAbility();
        assertEquals(100,hunter.GetHealthAmount());
        assertEquals(100,hunter.GetHealthPool());
        assertEquals(false,hunter.LevelUp());
        assertEquals(10,monster.GetHealthAmount());
    }

    @Test
    void MinIdx(){
        double[] arr = {0,1,2,3};
        assertEquals(0,hunter.MinIdx(arr));
        double[] arr2 = {1,0,2,3};
        assertEquals(1,hunter.MinIdx(arr2));
    }

}