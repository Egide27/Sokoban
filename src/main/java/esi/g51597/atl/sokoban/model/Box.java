package esi.g51597.atl.sokoban.model;

/**
 * In the game, the boxes has to be moved to be on the right position
 *
 * @author Egide Kabanza
 */
public class Box extends Movable {

    /**
     * Built a Movabe of type Box
     *
     * @param position
     */
    public Box(Position position) {
        super(position, 'b');
    }

    @Override
    protected void move(Direction direction) {
        int x = direction.getX();
        int y = direction.getY();
        super.getPosition().setPosition(super.getPosition().getX() + x, super.getPosition().getY() + y);
    }

}
