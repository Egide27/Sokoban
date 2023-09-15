package esi.g51597.atl.sokoban.model;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Egide Kabanza
 */
public class MovePlayerTest {
    
    Position expectPos = new Position(8, 4);
    
    public MovePlayerTest() {
    }

    @Test
    public void testUndoEmptyList() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.undo();
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testUndoFromLeft() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.undo();
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testUndoFromRight() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.RIGHT);
        move.undo();
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testUndoFromUp() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        expectPos = game.getStart();
        move.execute(Direction.UP);
        move.undo();
        assertEquals(expectPos, game.getStart());
    }
    @Test
    public void testUndoFromDown() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.execute(Direction.UP);
        expectPos = game.getStart();
        move.execute(Direction.DOWN);
        move.undo();
        assertEquals(expectPos, game.getStart());
    }
    @Test
    public void testUndoWithBox() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.execute(Direction.UP);
        expectPos = game.getStart();
        move.execute(Direction.RIGHT);
        move.undo();
        assertTrue(game.getSquareAt(7, 4).getMovable()instanceof Box);
    }

    @Test
    public void testRedoEmptyList() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.redo();
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testRedoOK() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.undo();
        move.redo();
        assertEquals(new Position(8, 3), game.getStart());
    }

    @Test
    public void testExecuteToAWall() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.DOWN);
        assertEquals(expectPos, game.getStart());
    }
    @Test
    public void testExecuteToEmptyNullSquareLeft() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        expectPos.setPosition(8, 3);
        assertEquals(expectPos, game.getStart());
    }
    @Test
    public void testExecuteToEmptyNullSquareRight() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.RIGHT);
        expectPos.setPosition(8, 5);
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testExecuteToEmptyNullSquareUp() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.execute(Direction.UP);
        expectPos.setPosition(7, 3);
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testExecuteToEmptyNullSquareDown() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.execute(Direction.UP);
        move.execute(Direction.DOWN);
        expectPos.setPosition(8, 3);
        assertEquals(expectPos, game.getStart());
    }

    @Test
    public void testExecuteToBoxCanMove() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.execute(Direction.UP);
        move.execute(Direction.RIGHT);
        expectPos.setPosition(7, 4);
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testExecuteToBoxCannotMove() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.execute(Direction.UP);
        move.execute(Direction.UP);
        expectPos.setPosition(7, 3);
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testExecutePlayerToGoal() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.execute(Direction.UP);
        move.execute(Direction.RIGHT);
        move.execute(Direction.UP);
        int x = game.getStart().getX();
        int y = game.getStart().getY();
        assertTrue(game.getSquareAt(x, y).getType() == SquareType.GOAL && game.getSquareAt(x, y).getMovable()instanceof Player);
    }
    
    @Test
    public void testExecuteBoxToGoal() throws IOException {
        Sokoban game = new Sokoban();
        MovePlayer move = new MovePlayer(game);
        move.execute(Direction.LEFT);
        move.execute(Direction.UP);
        move.execute(Direction.RIGHT);
        move.execute(Direction.UP);
        move.execute(Direction.RIGHT);
        move.execute(Direction.UP);
        int x = game.getStart().getX() + Direction.UP.getX();
        int y = game.getStart().getY()+ Direction.UP.getY();
        assertTrue(game.getSquareAt(x, y).getType() == SquareType.GOAL && game.getSquareAt(x, y).getMovable()instanceof Box);
    }
    
}
