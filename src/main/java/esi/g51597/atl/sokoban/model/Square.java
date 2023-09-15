package esi.g51597.atl.sokoban.model;

/**
 * The maze of a sokoban game is made of squareq.
 *
 * @author Egide Kabanza
 */
public class Square {

    private final SquareType type;
    private final Movable movable;

    /**
     * Constructor of a Square
     *
     * @param type
     * @param movable
     */
    Square(SquareType type, Movable movable) {
        this.type = type;
        this.movable = movable;
    }

    /**
     * Get the type of the squeare
     *
     * @return SquareType, the type
     */
    public SquareType getType() {
        return type;
    }

    /**
     * GEt the movable of the Sqaure if the is a movable.
     *
     * @return Movable if there is a movable, else null.
     */
    public Movable getMovable() {
        return movable;
    }

    @Override
    public String toString() {
        return "Square{" + "type=" + type + ", movable=" + movable + '}';
    }

}
