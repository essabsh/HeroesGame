package Imp;
import Imp.Tiles.*;
import Imp.Enemies.*;
import Imp.Heros.*;

public interface Visitor {
    void visit(Empty empty);
    void visit(Wall wall);
    void visit(Enemy enemy);
    void visit(Hero hero);
    void accept(Visitor visitor);
}
