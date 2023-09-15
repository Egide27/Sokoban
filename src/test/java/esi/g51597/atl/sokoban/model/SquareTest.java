package esi.g51597.atl.sokoban.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Egide Kabanza
 */
public class SquareTest {
    
    public SquareTest() {
    }

    @Test
    public void testGetType() {
        Square square = new Square(SquareType.GOAL, new Player(new Position(0, 0)));
        assertEquals(SquareType.GOAL, square.getType());
    }

    @Test
    public void testGetMovable() {
        Square square = new Square(SquareType.GOAL, new Player(new Position(0, 0)));
        assertEquals('p', square.getMovable().getMovableType());
    }
  
}
