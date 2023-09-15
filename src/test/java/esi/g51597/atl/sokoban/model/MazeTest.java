package esi.g51597.atl.sokoban.model;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Egide Kabanza
 */
public class MazeTest {
   
    File testingFile = new File("levels/1.xsb");
    public MazeTest() {
    }

    @Test
    public void testGetMaze() throws IOException {
        LoadFile lf = new LoadFile(testingFile);
        Maze maze = new Maze(lf.getMaze());
        assertEquals(maze.getSquareAt(0, 0).getType(), SquareType.EMPTY );
    }

    @Test
    public void testGetNbLine() throws IOException {
        LoadFile lf = new LoadFile(testingFile);
        Maze maze = new Maze(lf.getMaze());
        assertEquals(10, maze.getNbLine());
    }
    @Test
    public void testGetNbCol() throws IOException {
        LoadFile lf = new LoadFile(testingFile);
        Maze maze = new Maze(lf.getMaze());
        assertEquals(9, maze.getNbCol());
    }


   @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamXLessZero() throws IOException {
        LoadFile lf = new LoadFile(testingFile);
        Maze maze = new Maze(lf.getMaze());
        maze.getSquareAt(-1, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamYLessZero() throws IOException {
        LoadFile lf = new LoadFile(testingFile);
        Maze maze = new Maze(lf.getMaze());
        maze.getSquareAt(0, -1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamXMoreThanNbLine() throws IOException {
        LoadFile lf = new LoadFile(testingFile);
        Maze maze = new Maze(lf.getMaze());
        maze.getSquareAt(maze.getNbLine(), 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetSquareAtWhenParamYMoreThanNbCols() throws IOException {
        LoadFile lf = new LoadFile(testingFile);
        Maze maze = new Maze(lf.getMaze());
        maze.getSquareAt(0, maze.getNbCol());
    }
    
    @Test
    public void testGetStartWhenOk() throws IOException {
       LoadFile lf = new LoadFile(testingFile);
        Maze maze = new Maze(lf.getMaze());
        Position pos = new Position(8,4);
        assertEquals(pos, maze.getStart());
    }

    @Test
    public void testGetStartWhenWrong() throws IOException {
       LoadFile lf = new LoadFile(testingFile);
        Maze maze = new Maze(lf.getMaze());
        assertFalse(maze.getSquareAt(7, 5).getMovable()instanceof Player);
    }

}
