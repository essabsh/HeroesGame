package tests;

import Imp.Enemies.*;
import Imp.Heros.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TrapTest {
    private Trap trap;
    private Warrior warrior;
    public TrapTest(){}
    @BeforeEach
    void setUp() {
        warrior = new Warrior(1,1,"Alexander",100,100,50,10,3);
        trap = new Trap("trap",100,100,20,2,0,1,'t',20,2,2,warrior);
    }

    @Test
    void action() {
        trap.Action();
        trap.Action();
        trap.Action();
        assertEquals(100,trap.getHealthAmount());
    }

    @Test
    void testToString() {
        assertEquals("t",trap.toString());
    }
}