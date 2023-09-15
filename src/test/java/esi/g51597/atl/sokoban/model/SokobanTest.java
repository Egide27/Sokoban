package esi.g51597.atl.sokoban.model;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Egide Kabanza
 */
public class SokobanTest {
    
    public SokobanTest() {
    }


    @Test
    public void testStart() throws IOException {
        Sokoban soko = new Sokoban();
        Position po = new Position(8, 4);
        assertEquals(po, soko.getStart());
    }

    @Test
    public void testMovePlayerToAWall() throws IOException {
        Sokoban game = new Sokoban();
        Position expectPos = game.getStart();
        game.movePlayer(Direction.DOWN);
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testMovePlayerToEmptyNullSquareLeft() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        Position expectPos = new Position(8, 3);
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testMovePlayerToEmptyNullSquareRight() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.RIGHT);
        Position expectPos = new Position(8, 5);
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testMovePlayerToEmptyNullSquareUp() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        Position expectPos = new Position(7, 3);
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testMovePlayerToEmptyNullSquareDown() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.DOWN);
        Position expectPos = new Position(8, 3);
        assertEquals(expectPos, game.getStart());
    }
    @Test
    public void testMovePlayerToBoxCanMove() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.RIGHT);
        Position expectPos = new Position(7, 4);
        assertEquals(expectPos, game.getStart());
    }
    @Test
    public void testMovePlayerToBoxCannotMove() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.UP);
        Position expectPos = new Position(7, 3);
        assertEquals(expectPos, game.getStart());
    }
    
    @Test
    public void testMovePlayerPlayerToGoal() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.RIGHT);
        game.movePlayer(Direction.UP);
        int x = game.getStart().getX();
        int y = game.getStart().getY();
        assertTrue(game.getSquareAt(x, y).getType() == SquareType.GOAL && game.getSquareAt(x, y).getMovable()instanceof Player);
    }
    @Test
    public void testMovePlayerBoxToGoal() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.RIGHT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.RIGHT);
        game.movePlayer(Direction.UP);
        int x = game.getStart().getX() + Direction.UP.getX();
        int y = game.getStart().getY()+ Direction.UP.getY();
        assertTrue(game.getSquareAt(x, y).getType() == SquareType.GOAL && game.getSquareAt(x, y).getMovable()instanceof Box);
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamXLessZero() throws IOException {
        Sokoban game = new Sokoban();
        game.getSquareAt(-1, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamYLessZero() throws IOException {
        Sokoban game = new Sokoban();
        game.getSquareAt(0, -1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamXYLessZero() throws IOException {
        Sokoban game = new Sokoban();
        game.getSquareAt(-1, -1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamXTooHigh() throws IOException {
        Sokoban game = new Sokoban();
        game.getSquareAt(game.getNbLines(), 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamYTooHigh() throws IOException {
        Sokoban game = new Sokoban();
        game.getSquareAt(0, game.getNbCols());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamXYTooHigh() throws IOException {
        Sokoban game = new Sokoban();
        game.getSquareAt(game.getNbLines(), game.getNbCols());
    }

    @Test
    public void testGetMazeWhenEquals() throws IOException {
        Sokoban game = new Sokoban();
        assertEquals(new Position(8, 4), game.getMaze().getStart());
    }

    @Test
    public void testIsWinnerCaseFalse() throws IOException {
        Sokoban game = new Sokoban();
        assertFalse(game.isWinner());
    }
    @Test
    public void testIsWinnerCaseTrue() throws IOException {
        Sokoban game = new Sokoban(0);
        game.movePlayer(Direction.UP);
        assertTrue(game.isWinner());
    }

    @Test
    public void testIsOverCaseFasle() throws IOException {
        Sokoban game = new Sokoban();
        assertFalse(game.isOver());
    }
    
    @Test
    public void testIsOverCaseTrue() throws IOException {
        Sokoban game = new Sokoban(0);
        game.movePlayer(Direction.UP);
        assertTrue(game.isOver());
    }

    @Test
    public void testUndoCaseNoMove() throws IOException {
        Sokoban game = new Sokoban();
        game.undo();
        assertEquals(new Position(8, 4), game.getStart());
    }
    @Test
    public void testUndoCaseEmptyNoBox() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.undo();
        assertEquals(new Position(8, 4), game.getStart());
    }
    
    @Test
    public void testUndoCaseEmptyBox() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.RIGHT);
        game.undo();
        assertEquals(new Position(7, 3), game.getStart());
    }
    
    @Test
    public void testUndoCaseGoalNoBox() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.RIGHT);
        game.movePlayer(Direction.UP);
        game.undo();
        assertEquals(new Position(7, 4), game.getStart());
    }
    
    @Test
    public void testUndoCaseGoalBox() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.RIGHT);
        game.movePlayer(Direction.UP);
        game.movePlayer(Direction.RIGHT);
        game.undo();
        assertEquals(new Position(6, 4), game.getStart());
    }

    @Test
    public void testRedoNoMove() throws IOException {
        Sokoban game = new Sokoban();
        game.redo();
        assertEquals(new Position(8, 4), game.getStart());
    }
    
    @Test
    public void testRedoNoUndoButMove() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.redo();
        assertEquals(new Position(8, 3), game.getStart());
    }
    
    @Test
    public void testRedoAfterUndo() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.undo();
        game.redo();
        assertEquals(new Position(8, 3), game.getStart());
    }

    @Test
    public void testGetStart() throws IOException {
        Sokoban game = new Sokoban();
        assertEquals(new Position(8, 4), game.getStart());
    }

    @Test
    public void testGetNbLines() throws IOException {
        Sokoban game = new Sokoban();
        assertTrue(game.getNbLines() == 10);
    }

    @Test
    public void testGetNbCols() throws IOException {
        Sokoban game = new Sokoban();
        assertTrue(game.getNbCols()== 9);
    }

    @Test
    public void testGetLevel() throws IOException {
        Sokoban game = new Sokoban();
        assertTrue(game.getLevel() == 1);
    }

    @Test
    public void testGetNbMovesCaseNoMove() throws IOException {
        Sokoban game = new Sokoban();
        assertTrue(game.getNbMoves() == 0);
    }
    
    @Test
    public void testGetNbMovesMoves() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        assertTrue(game.getNbMoves() == 1);
    }
    @Test
    public void testGetNbMovesAfterUndo() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.undo();
        assertTrue(game.getNbMoves() == 0);
    }
    
    @Test
    public void testGetNbMovesAfterRedo() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.undo();
        game.redo();
        assertTrue(game.getNbMoves() == 1);
    }

    @Test
    public void testNextLevel() throws Exception {
        Sokoban game = new Sokoban();
        game.nextLevel();
        assertTrue(game.getLevel() == 2);
    }

    @Test
    public void testRestart() throws Exception {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.movePlayer(Direction.UP);
        game.restart();
        assertEquals(new Position(8, 4), game.getStart());
    }

    @Test
    public void testGetUndoStackCaseEmpty() throws IOException {
        Sokoban game = new Sokoban();
        assertTrue(game.getUndoStack().empty());
    }
    
    @Test
    public void testGetUndoStackAfterMove() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        assertTrue(game.getUndoStack().size() == 1);
    }

    @Test
    public void testGetRedoStackCaseEmpty() throws IOException {
        Sokoban game = new Sokoban();
        assertTrue(game.getRedoStack().empty());
    }
    @Test
    public void testGetRedoStackAfterMoveUndo() throws IOException {
        Sokoban game = new Sokoban();
        game.movePlayer(Direction.LEFT);
        game.undo();
        assertTrue(game.getRedoStack().size() == 1);
    }

    @Test
    public void testGetMissingGoals() throws IOException {
        Sokoban game = new Sokoban();
        assertEquals(2, game.getMissingGoals());
    }

    @Test
    public void testGetTotalGoals() throws IOException {
        Sokoban game = new Sokoban();
        assertEquals(10, game.getTotalGoals());
    }
   
    
}
