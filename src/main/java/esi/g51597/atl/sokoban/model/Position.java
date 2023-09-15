package esi.g51597.atl.sokoban.model;

/**
 * The position in the maze
 *
 * @author Egide Kabanza
 */
public class Position {

    private int x;
    private int y;

    /**
     * Construct a Position with
     *
     * @param x, position on the horizontal
     * @param y, the position on the vertical
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x
     *
     * @return int, the x
     */
    int getX() {
        return x;
    }

    /**
     * Get the y
     *
     * @return int, the y
     */
    int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }

    /**
     * Get the X of the position
     *
     * @param x,
     */
    private void setX(int x) {
        this.x = x;
    }

    /**
     * Get the y of the position
     *
     * @param y
     */
    private void setY(int y) {
        this.y = y;
    }

    /**
     * Change the information of the position
     *
     * @param x, int
     * @param y, int
     */
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.x;
        hash = 83 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

}
