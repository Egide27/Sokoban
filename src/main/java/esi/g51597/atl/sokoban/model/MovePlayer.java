package esi.g51597.atl.sokoban.model;

import java.util.Stack;

/**
 *
 * @author Egide Kabanza
 */
public class MovePlayer {

    private int nbMove;
    private final Sokoban game;
    private final Stack<CommandMove> undoStack;
    private final Stack<CommandMove> redoStack;

    /**
     * Constuctor of a move of a player.
     *
     * @param game the game in which the move happen.
     */
    public MovePlayer(Sokoban game) {
        nbMove = 0;
        this.game = game;
        undoStack = new Stack();
        redoStack = new Stack();
    }

    private boolean canUndo() {
        return !undoStack.isEmpty();
    }

    private boolean canRedo() {
        return !redoStack.isEmpty();
    }

    private Direction oppositeDirection(Direction direction) {
        switch (direction) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case RIGHT:
                return Direction.LEFT;
            case LEFT:
                return Direction.RIGHT;
        }
        return null;
    }

    /**
     * Undo the last done move
     */
    public void undo() {
        if (canUndo()) {
            CommandMove move = undoStack.pop();
            int x = game.getStart().getX();
            int y = game.getStart().getY();
            
            Direction direction = oppositeDirection(move.getDirection());
            int xDest = game.getStart().getX() + direction.getX();
            int yDest = game.getStart().getY() + direction.getY();
            Square sqDest = game.getSquareAt(xDest, yDest);
            int xBox = game.getStart().getX() - direction.getX();
            int yBox = game.getStart().getY() - direction.getY();

            switch (sqDest.getType()) {
                case EMPTY:
                    movePlayer(x, y, direction);
                    if (move.isBoxMoved())
                        moveBox(xBox, yBox, direction);
                    break;
                case GOAL:
                    movePlayer(x, y, direction);
                    if (move.isBoxMoved()) 
                        moveBox(xBox, yBox, direction);
                    break;
            }
            redoStack.push(move);
            nbMove--;

        }
    }

    /**
     * Redo the last undone move
     */
    public void redo() {
        if (canRedo()) {
            CommandMove move = redoStack.pop();
            Direction direction = move.getDirection();
            execute(direction);
        }

    }

    /**
     * Execute the move of a player
     *
     * @param direction, direction of the move
     */
    public void execute(Direction direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction can't be null");
        }
        
        int x = game.getStart().getX();
        int y = game.getStart().getY();
        int xDest = game.getStart().getX() + direction.getX();
        int yDest = game.getStart().getY() + direction.getY();
        Square sqDest = game.getSquareAt(xDest, yDest);
        CommandMove command = new CommandMove(direction, false);

        switch (sqDest.getType()) {
            case EMPTY:
                if ((sqDest.getMovable() instanceof Box)) {
                    if (canBeMoved(xDest, yDest, direction)) {
                        moveBox(xDest, yDest, direction);
                        movePlayer(x, y, direction);
                        command = new CommandMove(direction, true);
                        undoStack.push(command);
                        nbMove++;
                    }else
                        break;
                }else{
                    movePlayer(x, y, direction);
                    undoStack.push(command);
                    nbMove++;
                    break;
                }
            case GOAL:
                if (sqDest.getMovable() instanceof Box) {
                    if (canBeMoved(xDest, yDest, direction)) {
                        moveBox(xDest, yDest, direction);
                        movePlayer(x, y, direction);
                        command = new CommandMove(direction, true);
                        undoStack.push(command);
                        nbMove++;
                    }
                }else{
                    movePlayer(x, y, direction);
                    undoStack.push(command);
                    nbMove++;
                }
                
        }
        

    }

    private void movePlayer(int x, int y, Direction direction) {
        if (!(game.getSquareAt(x, y).getMovable() instanceof Player)) {
            throw new IllegalArgumentException("This square don't have a Player");
        }
        int xDest = x + direction.getX();
        int yDest = y + direction.getY();
        Square sqSrc = game.getSquareAt(x, y);
        Player player = (Player) game.getSquareAt(x, y).getMovable();
        Square sqDest = game.getSquareAt(xDest, yDest);

        switch (sqDest.getType()) {
            case EMPTY:
                if (sqSrc.getType() == SquareType.EMPTY) {
                    game.setSquareAt(x, y, new Square(SquareType.EMPTY, null));
                } else {
                    game.setSquareAt(x, y, new Square(SquareType.GOAL, null));
                }
                game.setSquareAt(xDest, yDest, new Square(SquareType.EMPTY, new Player(new Position(xDest, yDest))));
                player.move(direction);
                break;
            case GOAL:
                if (sqSrc.getType() == SquareType.EMPTY) {
                    game.setSquareAt(x, y, new Square(SquareType.EMPTY, null));
                } else {
                    game.setSquareAt(x, y, new Square(SquareType.GOAL, null));
                }
                game.setSquareAt(xDest, yDest, new Square(SquareType.GOAL, new Player(new Position(xDest, yDest))));
                player.move(direction);
                break;
        }

    }
    
    /**
     * Get the number of mouvements made
     *
     * @return int, the number of mouvements
     */
    int getNbMoves() {
        return nbMove;
    }

    private void moveBox(int x, int y, Direction direction) {
        if (!(game.getSquareAt(x, y).getMovable() instanceof Box)) {
            throw new IllegalArgumentException("This square don't have a Box");
        }
        int xDest = x + direction.getX();
        int yDest = y + direction.getY();
        Square sqSrc = game.getSquareAt(x, y);
        Box box = (Box) game.getSquareAt(x, y).getMovable();
        Square sqDest = game.getSquareAt(xDest, yDest);

        switch (sqDest.getType()) {
            case EMPTY:
                if (sqSrc.getType() == SquareType.EMPTY) {
                    game.setSquareAt(x, y, new Square(SquareType.EMPTY, null));
                } else {
                    game.setSquareAt(x, y, new Square(SquareType.GOAL, null));
                }
                game.setSquareAt(xDest, yDest, new Square(SquareType.EMPTY, new Box(new Position(xDest, yDest))));
                box.move(direction);
                break;
            case GOAL:
                    if (sqSrc.getType() == SquareType.EMPTY) {
                        game.setSquareAt(x, y, new Square(SquareType.EMPTY, null));
                    } else {
                        game.setSquareAt(x, y, new Square(SquareType.GOAL, null));
                    }
                    game.setSquareAt(xDest, yDest, new Square(SquareType.GOAL, new Box(new Position(xDest, yDest))));
                    box.move(direction);
                    break;
        }

    }

    private boolean canBeMoved(int x, int y, Direction direction) {
        int xDest = x + direction.getX();
        int yDest = y + direction.getY();
        if (xDest > game.getNbLines() || xDest < 0 || yDest > game.getNbCols() || yDest < 0) {
            throw new IllegalArgumentException("Impossible to go in this direction!!");
        }
        Square sqDest = game.getSquareAt(xDest, yDest);
        if (sqDest.getType() == SquareType.WALL) {
            return false;
        }
        return sqDest.getMovable() == null;
    }

    Stack<CommandMove> getUndoStack() {
        return undoStack;
    }

    Stack<CommandMove> getRedoStack() {
        return redoStack;
    }

}
