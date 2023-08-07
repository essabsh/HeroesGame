package Imp;
import Imp.Tiles.*;
import Imp.Heros.*;
import java.util.List;
import java.util.Scanner;
public class LevelManage {
    private List<List<String>> levels;
    private Hero myHero;
    private Scanner scannner = new Scanner(System.in);
    public LevelManage(List<List<String>> levels) {
        this.levels = levels;
        System.out.println(Selection());
        String player = scannner.next();
        switch (player) {
            case "1":
                this.myHero = new Warrior(0, 0, "Jon Snow", 300, 300, 30, 4, 3);
                System.out.println("You have chosen Jon Snow");
                break;
            case "2":
                this.myHero = new Warrior(0, 0, "The Hound", 400, 400, 20, 6, 5);
                System.out.println("You have chosen The Hound");
                break;
            case "3":
                this.myHero = new Mage(0, 0, "Melisandre", 100, 100, 5, 1, 300, 30, 15, 5, 6);
                System.out.println("You have chosen Melisandre (Mage) !");
                break;
            case "4":
                this.myHero = new Mage(0, 0, "Thoros of Myr", 250, 250, 25, 4, 150, 20, 20, 3, 4);
                System.out.println("You have chosen Thoros of Myr (Mage) !");
                break;
            case "5":
                this.myHero = new Rogue(0, 0, "Arya Stark", 150, 150, 40, 2, 20);
                System.out.println("You have chosen Arya (Rogue) !");
                break;
            case "6":
                this.myHero = new Rogue(0, 0, "Bronn", 250, 250, 35, 3, 50);
                System.out.println("You have chosen Bronn (Rogue) !");
                break;
            case "7":
                this.myHero = new Hunter(0, 0, "Ygritte", 220, 220, 30, 2, 6);
                System.out.println("You have chosen Ygritte (Hunter) !");
                break;
        }
    }


    private String Selection() {
        String starter = "Select player:  \n" +
                "1. Jon Snow \tHealth: 300/300\t\tAttack: 30\tDefense: 4\tLevel: 1\t\tExperience: 0/50\t\tCooldown: 0/3\n" +
                "2. The Hound\t  \t\tHealth: 400/400\t\tAttack: 20\t\tDefense: 6\t\tLevel: 1\t\tExperience: 0/50\t\tCooldown: 0/5\n" +
                "3. Melisandre \t\tHealth: 100/100\t\tAttack: 5 \t\tDefense: 1\t\tLevel: 1\t\tExperience: 0/50\t\tMana: 75/300\t\tSpell Power: 15\n" +
                "4. Thoros of Myr\t\tHealth: 250/250\t\tAttack: 25\t\tDefense: 4\t\tLevel: 1\t\tExperience: 0/50\t\tMana: 37/150\t\tSpell Power: 20\n" +
                "5. Arya Stark\t\t\tHealth: 150/150\t\tAttack: 40\t\tDefense: 2\t\tLevel: 1\t\tExperience: 0/50\t\tEnergy: 100/100\n" +
                "6. Bronn\t\t\t\tHealth: 250/250\t\tAttack: 35\t\tDefense: 3\t\tLevel: 1\t\tExperience: 0/50\t\tEnergy: 100/100\n" +
                "7. Ygritte\t \t\tHealth: 220/220\t\tAttack: 30\t\tDefense: 2\t\tLevel: 1\t\tExperience: 0/50\t\tArrows: 10\t\tRange: 6\n";
        return starter;
    }


    public void StartGame() {
        boolean won = false;
        for (int level = 0; level < this.levels.size(); level++) {
            GameBoard CurrentLevel = new GameBoard();
            CurrentLevel.PrepareBoard(levels.get(level), myHero);
            List<Unit> enemies = CurrentLevel.GetEnemies();
            for (Unit enemy : enemies)
                myHero.addEnemy(enemy);
            CurrentLevel.Begin();
            if (myHero.GetHealthAmount() <= 0) {
                break;
            }
            if(level == this.levels.size()-1){ won = true;}
            if(!won) {
                System.out.println("Level completed now to the next level");
            }
        }
        if (won){System.out.println("Winner");}
    }
}
