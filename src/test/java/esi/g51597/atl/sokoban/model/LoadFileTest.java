package esi.g51597.atl.sokoban.model;

import java.io.File;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;



/**
 *
 * @author Egide Kabanza
 */
public class LoadFileTest {
    
    File testingFile = new File("levels/1.xsb");
    
    @Test
    public void testGetMazeNbCols() throws Exception {
        LoadFile loadFile = new LoadFile(testingFile);
        assertEquals(9, loadFile.getMaze()[0].length);
    }
    @Test
    public void testGetMazeNbLines() throws Exception {
        LoadFile loadFile = new LoadFile(testingFile);
        assertEquals(10, loadFile.getMaze().length);
    }

    
  
}
