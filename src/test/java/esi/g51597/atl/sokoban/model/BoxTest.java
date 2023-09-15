package esi.g51597.atl.sokoban.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class Box
 * @author Egide Kabanza
 */
public class BoxTest {
    
    public BoxTest() {
    }

    @Test
    public void testMove() {
        Position position = new Position(7, 7);
        Box box = new Box(position);
        box.move(Direction.DOWN);
       assertEquals( new Position(8, 7), box.getPosition());
    }
    
}
