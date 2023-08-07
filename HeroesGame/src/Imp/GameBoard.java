package Imp;
import Imp.Tiles.*;
import java.util.LinkedList;
import java.util.List;
import Imp.Heros.*;
import Imp.Enemies.*;
public class GameBoard {
    private tile[][] Tiles;
    private Hero player;
    private List<Unit> EnemiesBoard = new LinkedList<>();

    public GameBoard() {}
    public List<Unit> GetEnemies(){return EnemiesBoard;}

    public void play(Unit unit){
        int x  = unit.getX() , y = unit.getY();
        int [] postion = unit.Action();
        //postion = unit.Action();
        if(postion[0] != x || postion[1] !=y) {
            tile t = Tiles[postion[1]][postion[0]];
            unit.communicate(t);
            if(unit.getY() != y || unit.getX() !=x){
                //new places
                Tiles[unit.getY()][unit.getX()] = unit;
                Tiles[t.getY()][t.getX()] = t;
            }
        }
    }

    public void PrepareBoard(List<String> accepted, Hero hero) {
        Tiles = new tile[accepted.size()][accepted.get(1).length()];
        this.player = hero;
        for (int i = 0; i < Tiles.length; i++) {
            String Current = accepted.get(i);
            for (int j = 0; j < Current.length(); j++) {
                char charAt = Current.charAt(j);
                if (charAt == '.') {
                    Tiles[i][j] = new Empty(j, i);
                } else if (charAt == '#') {
                    Tiles[i][j] = new Wall(j, i);
                } else if (charAt == '@') {
                    Tiles[i][j] = player;
                    player.setX(j);
                    player.setY(i);
                } else if (charAt == 'B' || charAt == 'Q' || charAt == 'D') {
                    if (charAt == 'B') {
                        Unit trap = new Trap("Bonus Trap", 1, 1, 1, 1, j, i, charAt, 250, 1, 5, player);
                        Tiles[i][j] = trap;
                        EnemiesBoard.add(trap);
                    } else if (charAt == 'Q') {
                        Unit trap = new Trap("Queen's Trap", 250, 250, 50, 10, j, i, charAt, 100, 3, 7, player);
                        Tiles[i][j] = trap;
                        EnemiesBoard.add(trap);

                    } else {
                        Unit trap = new Trap("Death Trap", 500, 500, 100, 20, j, i, charAt, 250, 1, 10, player);
                        Tiles[i][j] = trap;
                        EnemiesBoard.add(trap);
                    }
                } else {
                    AddMonster(j, i, charAt);
                }
            }
        }
    }

    private void AddMonster(int X, int Y, char input){
        Unit monster = null;
        switch (input){
            case 's':
                monster = new Monster("Lannister Solider",80,80,8,3,X,Y,input,25,3,player);break;
            case 'k':
                monster = new Monster("Lannister Knight" ,200,200,14,8,X,Y,input,50,4,player);break;
            case 'q':
                monster = new Monster("Queen’s Guard",400,400,20,15,X,Y,input,100,5,player);break;
            case 'z':
                monster = new Monster("Wright",600,600,30,15,X,Y,input,100,3,player);break;
            case 'b':
                monster = new Monster("Bear-Wright",1000,1000,75,30,X,Y,input,250,4,player);break;
            case 'g':
                monster = new Monster("Giant-Wright" , 1500,1500,100,40,X,Y,input,500,5,player);break;
            case 'w':
                monster = new Monster("White Walker",2000,2000,150,50,X,Y,input,1000,6,player);break;
            case 'M':
                monster = new Boss("The Mountain" , 1000,1000,60,25,X,Y,input,500,6,player,5);break;
            case 'C':
                monster = new Boss("Queen Cersei",100,100,10,10,X,Y,input,1000,1,player,8);break;
            case 'K':
                monster = new Boss("Night’s King",5000,5000,300,150,X,Y,input,5000,8,player,3);break;
        }
        EnemiesBoard.add(monster);
        Tiles[Y][X] = monster;
    }

    public void Begin(){
        while (player.GetHealthAmount() > 0 && !EnemiesBoard.isEmpty()){
            System.out.println(toString() + player.description());
            play(player);
            List<Unit> deads = new LinkedList<>();
            for (Unit e: EnemiesBoard) {
                if (e.GetHealthAmount() <= 0) {
                    Tiles[e.getY()][e.getX()] = new Empty(e.getX(), e.getY());
                    deads.add(e);
                    player.getEnemies().remove(e);
                }
            }
            for (Unit dead: deads) {
                EnemiesBoard.remove(dead);
            }
            for (Unit toplay: EnemiesBoard)
                play(toplay);
        }
        if (player.GetHealthAmount()<=0){System.out.println("the player died !" + "\n" + toString() + player.description());}
    }

    public String toString(){
        String board = new String("");
        for (tile [] tile: Tiles) {
            board = board+"\n";
            for (int i = 0 ; i<tile.length;i++)
                if(tile[i] !=null)
                    board = board + tile[i].toString();
        }
        board = board +"\n";
        return board;
    }

}
