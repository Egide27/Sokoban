package esi.g51597.atl.sokoban.model;

import java.io.IOException;

/**
 * Maze is the playground
 *
 * @author Egide Kabanza
 */
public class Maze {

    private final Square[][] maze;
    private final int nbLine;
    private final int nbCol;
    private Position start;

    /**
     * Construct a Maze by receiving a 2 dimensional table of square
     *
     * @param maze, the received table
     * @throws java.io.IOException
     */
    public Maze(Square[][] maze) throws IOException {
        this.maze = maze;
        nbLine = maze.length;
        nbCol = maze[0].length;
    }

    /**
     * Get thhe number of the lines of the maze
     *
     * @return int, number of lines
     */
    public int getNbLine() {
        return nbLine;
    }

    /**
     * Get thhe number of the column of the maze
     *
     * @return int, number of columns
     */
    public int getNbCol() {
        return nbCol;
    }

    /**
     * Get the start position The position of the player in the maze
     *
     * @return Position, the starting position
     */
    Position getStart() {
        for (int i = 0; i < nbLine; i++) {
            for (int j = 0; j < nbCol; j++) {
                if (maze[i][j].getMovable() instanceof Player) {
                    start = new Position(i, j);
                    return start;
                }
            }
        }
        return null;
    }

    /**
     * Get the square at a position
     *
     * @param x
     * @param y
     * @return Square, if the position has a square, else null;
     */
    public Square getSquareAt(int x, int y) {
        if(x < 0 || x >= nbLine || y< 0|| y >= nbCol)
            throw new IllegalArgumentException("Invalid argument for getSquareAt(int,int)");
        return maze[x][y];
    }

    /**
     * Change the square at maze[x][y] to sq
     *
     * @param x, the ligne of the maze
     * @param y, the column of the maze
     * @param sq, the new square to put at the position
     */
    void setSquareAt(int x, int y, Square sq) {
        if(y< 0 || y >= nbCol|| null == sq || x < 0 || x >= nbLine)
            throw new IllegalArgumentException("Invalid argument for setQuareAt(int,int,Squre)");
        maze[x][y] = sq;
    }

}
