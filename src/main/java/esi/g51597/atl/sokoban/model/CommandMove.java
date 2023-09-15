package esi.g51597.atl.sokoban.model;

;

/**
 * CommandMove represent the action choosen by the player to move in the game.
 *
 * @author Egide Kabanza
 */
public class CommandMove {

    private final Direction direction;
    private final boolean isBoxMoved;

    /**
     * Constructor
     *
     * @param direction, direction of the move
     * @param isBoxMoved, true if the player moved a box
     */
    CommandMove(Direction direction, boolean isBoxMoved) {
        this.direction = direction;
        this.isBoxMoved = isBoxMoved;
    }

    /**
     * Get the direction of the mmove
     *
     * @return
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Get if a box has been moved in the player move.
     *
     * @return true if a box has moved, else no.
     */
    public boolean isBoxMoved() {
        return isBoxMoved;
    }
}
