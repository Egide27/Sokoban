package esi.g51597.atl.sokoban.model;

/**
 * A movable is an objet of the game that can change position.
 *
 * @author Egide Kabanza
 */
public abstract class Movable {

    private final Position position;
    private final char movableType;

    /**
     * Construct a movable whit a positionand a type.
     *
     * @param position
     * @param movableType
     */
    Movable(Position position, char movableType) {
        this.position = position;
        this.movableType = movableType;
    }

    /**
     * A movable can move by changing his position
     *
     * @param direction, the direction of the movement.
     */
    protected abstract void move(Direction direction);

    /**
     * Get the situation of the player in the maze
     *
     * @return position, the situation of the player
     */
    protected Position getPosition() {
        return position;
    }

    /**
     * Get the type of movable.
     *
     * @return char, the type.
     */
    public char getMovableType() {
        return movableType;
    }

}
