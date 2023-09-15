package esi.g51597.atl.sokoban.model;

/**
 * Sokoban is played by one player
 *
 * @author Egide Kabanza
 */
public class Player extends Movable {

    /**
     * Construct a player
     *
     * @param position, the situation of the player on the maze
     */
    public Player(Position position) {
        super(position, 'p');

    }

    @Override
    protected void move(Direction direction) {
        int x = direction.getX();
        int y = direction.getY();
        super.getPosition().setPosition(super.getPosition().getX() + x, super.getPosition().getY() + y);
    }

}
