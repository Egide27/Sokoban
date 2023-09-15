package esi.g51597.atl.sokoban.model;

/**
 * Enumeration of all direction to go to
 *
 * @author Egide Kabanza
 */
public enum Direction {

    /**
     * To go up
     */
    UP(-1, 0),
    /**
     * To go down
     */
    DOWN(1, 0),
    /**
     * To go to left
     */
    LEFT(0, -1),
    /**
     * To go to right
     */
    RIGHT(0, 1);

    private final int x;
    private final int y;

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the value of x
     *
     * @return int , x
     */
    public int getX() {
        return x;
    }

    /**
     * Get the value of y
     *
     * @return int, y
     */
    public int getY() {
        return y;
    }
}
