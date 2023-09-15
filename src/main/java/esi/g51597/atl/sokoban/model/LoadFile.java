package esi.g51597.atl.sokoban.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * Loard a file
 *
 * @author Egide Kabanza
 */
public final class LoadFile {

    private int nbCol;
    private int nbLine;
    private Position start;
    private final File file;
    private final Square[][] maze;
    private final char BOX = '$';
    private final char WALL = '#';
    private final char PLAYER = '@';
    private final char EMPTY_ZONE = ' ';
    private final char DESTINATION = '.';
    private final char BOX_IN_ZONE = '*';
    private final char PLAYER_IN_ZONE = '+';
    

    /**
     * Create a file loader
     *
     * @param file, the loaded file
     * @throws IOException
     */
    public LoadFile(File file) throws IOException {
        this.file = file;
        getSizeOfMaze();
        maze = new Square[nbLine][nbCol];
        updadeMaze();
    }

    private int[] getSizeOfMaze() throws IOException {

        nbLine = 0;
        nbCol = 0;
        int[] mazeSize = new int[2];
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String readLine;

            while ((readLine = buffer.readLine()) != null) {
                nbCol = readLine.length();
                nbLine++;
            }
            mazeSize[0] = nbLine;
            mazeSize[1] = nbCol;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mazeSize;
    }

    /**
     * Turn the containt of the file into a 2 dimensional table
     *
     * @return 2 dimensional table,
     * @throws IOException
     */
    public Square[][] getMaze() throws IOException {
        return maze;
    }
    
    private void updadeMaze(){
        int i = 0;
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String readLine;

            while ((readLine = buffer.readLine()) != null) {
                for (int j = 0; j < nbCol; j++) {
                    char c = readLine.charAt(j);
                    switch (c) {
                        case BOX:
                            Position po = new Position(i, j);
                            maze[i][j] = new Square(SquareType.EMPTY, new Box(po));
                            break;
                        case DESTINATION:
                            maze[i][j] = new Square(SquareType.GOAL, null);
                            break;
                        case WALL:
                            maze[i][j] = new Square(SquareType.WALL, null);
                            break;
                        case PLAYER:
                            start = new Position(i, j);
                            maze[i][j] = new Square(SquareType.EMPTY, new Player(start));
                            break;
                        case EMPTY_ZONE:
                            maze[i][j] = new Square(SquareType.EMPTY, null);
                            break;
                        case BOX_IN_ZONE:
                            maze[i][j] = new Square(SquareType.GOAL, new Box(new Position(i, j)));
                            break;
                        case PLAYER_IN_ZONE:
                            maze[i][j] = new Square(SquareType.GOAL, new Player(start));
                            break;

                    }

                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    /**
     * Get the level of the File
     *
     * @return int, the level
     */
    int getLevel() {
        return Character.getNumericValue(file.getName().charAt(0));
    }

}
