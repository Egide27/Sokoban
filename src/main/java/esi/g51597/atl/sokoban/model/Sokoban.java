package esi.g51597.atl.sokoban.model;

import java.io.File;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * of the game
 *
 * @author Egide Kabanza
 */
public final class Sokoban implements Subject {

    private List<Observer> observers;

    private Maze maze;
    private Player player;
    private LoadFile loadFile;
    private MovePlayer movePlayer;
    private final String PATH = "levels/";
    private final String EXTENSION = ".xsb";

    private int totalGoals;
    private int missingGoals;

    /**
     * Built a sokoban game whit the first level.
     *
     * @throws IOException
     */
    public Sokoban() throws IOException {
        this(1);
    }

    /**
     * Built a game whith the a wanted level.
     *
     * @param level, the wanted level.
     * @throws IOException
     */
    public Sokoban(int level) throws IOException {
        loadFile = new LoadFile(new File(PATH + level + EXTENSION));
        maze = new Maze(loadFile.getMaze());
        player = new Player(maze.getStart());
        movePlayer = new MovePlayer(this);
        totalGoals = 0;
        missingGoals = 0;
        observers = new ArrayList<>();
    }

    /**
     * Move the player in the game.
     *
     * @param direction, direction of the movement.
     */
    public void movePlayer(Direction direction) {
        movePlayer.execute(direction);
        notifyObservers();
    }

    /**
     * Get the square at a wanted position
     *
     * @param x, x of the position
     * @param y, y of the position
     * @return Square,
     */
    public Square getSquareAt(int x, int y) {
        return maze.getSquareAt(x, y);
    }

    /**
     * Change the quare at a wanted position to another square
     *
     * @param x, x of the position
     * @param y, y of the position
     * @param sq, the new square for the position
     */
    void setSquareAt(int x, int y, Square sq) {
        maze.setSquareAt(x, y, sq);
    }

    /**
     * Get the maze of the game.
     *
     * @return Maze
     */
    public Maze getMaze() {
        return maze;
    }

    /**
     * Check the winning
     *
     * @return boolean, true if the player win, else false.
     */
    public boolean isWinner() {
        boolean win = true;
        for (int i = 0; i < getNbLines(); i++) {
            for (int j = 0; j < getNbCols(); j++) {
                Square sq = getSquareAt(i, j);
                if (sq.getType() == SquareType.GOAL && !(sq.getMovable() instanceof Box)) {
                    return false;
                }
            }
        }
        return win;
    }

    private void nberObjectif() {
        for (int i = 0; i < getNbLines(); i++) {
            for (int j = 0; j < getNbCols(); j++) {
                Square sq = getSquareAt(i, j);
                if (sq.getType() == SquareType.GOAL) {
                    totalGoals++;
                    if (!(sq.getMovable() instanceof Box)) {
                        missingGoals++;
                    }
                }
            }
        }
    }

    /**
     * Check if the game is over
     *
     * @return
     */
    public boolean isOver() {
        return isWinner();
    }

    /**
     * Undo the last action
     */
    public void undo() {
        movePlayer.undo();
        notifyObservers();
    }

    /**
     * Redo the last undone action
     */
    public void redo() {
        movePlayer.redo();
        notifyObservers();
    }

    /**
     * Get the start position, The position of the player.
     *
     * @return Position, the position of the player
     */
    public Position getStart() {
        return maze.getStart();
    }

    /**
     * Get the player
     *
     * @return Player, the player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Give the height of the maze
     *
     * @return int, number of line of the maze
     */
    public int getNbLines() {
        return maze.getNbLine();
    }

    /**
     * Give the height of the maze
     *
     * @return int, number of line of the maze
     */
    public int getNbCols() {
        return maze.getNbCol();
    }

    /**
     * Get the level of the game.
     *
     * @return int, the level of the game
     */
    public int getLevel() {
        return loadFile.getLevel();
    }

    /**
     * Get the number of moves done.
     *
     * @return int, number of moves done
     */
    public int getNbMoves() {
        return movePlayer.getNbMoves();
    }

    @Override
    public void registerObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    private void notifyObservers() {
        observers.forEach((observer) -> {
            observer.update();
        });
    }

    /**
     * Change the level to the next one.
     *
     * @throws IOException
     */
    public void nextLevel() throws IOException {
        int level = getLevel() + 1;
        loadFile = new LoadFile(new File(PATH + level + EXTENSION));
        maze = new Maze(loadFile.getMaze());
        player = new Player(maze.getStart());
        movePlayer = new MovePlayer(this);
        notifyObservers();
    }

    /**
     * Restart the game
     *
     * @throws IOException
     */
    public void restart() throws IOException {
        loadFile = new LoadFile(new File(PATH + getLevel() + EXTENSION));
        maze = new Maze(loadFile.getMaze());
        player = new Player(maze.getStart());
        movePlayer = new MovePlayer(this);
        notifyObservers();
    }

    /**
     * Get a stack of all done moves
     *
     * @return Stack<CommandMove>
     */
    public Stack<CommandMove> getUndoStack() {
        return movePlayer.getUndoStack();
    }

    /**
     * Get a stack of all undone moves
     *
     * @return Stack<CommandMove>
     */
    public Stack<CommandMove> getRedoStack() {
        return movePlayer.getRedoStack();
    }

    /**
     * Get the number of missing goals to win.
     *
     * @return int.
     */
    public int getMissingGoals() {
        missingGoals = 0;
        nberObjectif();
        return missingGoals;
    }

    /**
     * Get the total of Goals in a level.
     *
     * @return int.
     */
    public int getTotalGoals() {
        totalGoals = 0;
        nberObjectif();
        return totalGoals;
    }

}
