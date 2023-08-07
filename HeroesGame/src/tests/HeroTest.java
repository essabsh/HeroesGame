package tests;
import Imp.Heros.*;
import Imp.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Imp.Enemies.*;
import Imp.Tiles.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    Hero hero;
    Monster monster;
public HeroTest(){}
    @BeforeEach
    void setUp(){
        hero = new Hero(0,2,"hero",100,100,50,30,2) {
            @Override
            public List<Unit> enemiesInRange(int range) {
                return null;
            }

            @Override
            public void castAbility() {

            }
        };
        monster= new Monster("monster",100,100,10,5,0,1,'m',20,5, hero);
    }
    @Test
    void getlevel(){
        assertEquals(hero.getLevel(),hero.HeroLevel);
    }
    @Test
    void LevelUp(){
        hero.HeroLevel = 1;
        hero.Experience = 70;
        assertEquals(true,hero.LevelUp());
    }
    @Test
    void gainExp(){
        hero.Experience=100;
        assertEquals(true,hero.gainEXP(20));
    }


    @Test
    void ToString(){
        assertEquals("@",hero.toString());
    }

}