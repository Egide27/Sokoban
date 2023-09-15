package esi.g51597.atl.sokoban.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Egide Kabanza
 */
public class PositionTest {
    
    public PositionTest() {
    }

    @Test
    public void testGetX() {
        Position po = new Position(7, 0);
        assertEquals(7, po.getX());
    }

    @Test
    public void testGetY() {
        Position po = new Position(0, 7);
        assertEquals(7, po.getY());
    }

    @Test
    public void testSetPosition() {
        Position po = new Position(0, 0);
        po.setPosition(7,7);
        assertEquals(7, po.getX());
        assertEquals(7, po.getY());

    }

    @Test
    public void testEqualsCaseTrue() {
        Position position1 = new Position(0, 0);
        Position position2 = new Position(0, 0);
        assertEquals(position1, position2);
    }
    @Test
    public void testEqualsCaseFalse() {
        Position position1 = new Position(0, 0);
        Position position2 = new Position(0, 1);
        assertNotEquals(position1, position2);
    }
 
}
