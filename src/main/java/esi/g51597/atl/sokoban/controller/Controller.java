package esi.g51597.atl.sokoban.controller;

import java.io.IOException;
import esi.g51597.atl.sokoban.view.View;
import esi.g51597.atl.sokoban.model.Sokoban;
import esi.g51597.atl.sokoban.model.Direction;

/**
 * Controller of the SOkoban Game
 *
 * @author Egide Kabanza
 */
public class Controller {

    private Sokoban game;
    private final View view;
    private final int COMMAND_LENGHT = 1;
    private final int COMMAND_SHOW_MOVE = 3;
    private final int COMMAND_MOVE_LENGHT = 2;

    /**
     * Construct a controller for a sokoban game.
     *
     * @param game, the game controlled.
     * @param view, view of the game.
     */
    public Controller(Sokoban game, View view) {
        this.game = game;
        this.view = view;
    }

    /**
     * The main
     *
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Sokoban game = new Sokoban();
        View view = new View();
        Controller ctr = new Controller(game, view);
        ctr.start();

    }

    /**
     * Start the Game
     *
     * @throws java.io.IOException
     */
    void start() throws IOException {

        boolean quit = false;
        String command;
        String[] tokens;
        view.showStart(game.getMaze(), game.getLevel());
        changeLevelCtr();
        view.showOption();
        while (!game.isOver() && !quit) {

            view.showGoals("Nombre de goals manquant : ", game.getMissingGoals());
            view.showGoals("Nombre de goals total : ", game.getTotalGoals());

            command = view.prompt();
            tokens = command.split(" ");
            if (!lengtCommandCtrl(tokens)) {
                view.showBadCommand();
            } else {
                switch (tokens[0].toLowerCase()) {
                    case "move":
                        game.movePlayer(moveDirection(tokens));
                        break;
                    case "help":
                        view.showOption();
                        break;
                    case "undo":
                        game.undo();
                        break;
                    case "redo":
                        game.redo();
                        break;
                    case "next":
                        game.nextLevel();
                        break;
                    case "restart":
                        game.restart();
                        break;
                    case "show":
                        if (tokens[1].equals("undo")) {
                            view.showStack(game.getUndoStack());
                        } else {
                            view.showStack(game.getRedoStack());
                        }
                        break;
                    case "quit":
                        quit = true;
                        break;
                    default:
                        view.showBadCommand();
                        break;
                }
            }

            view.showMaze(game.getMaze(), game.getLevel());
            view.showNbMoves(game.getNbMoves());
        }
        if (game.isOver()) {
            view.showWinningGame(game.getLevel(), game.getNbMoves());
            changeLevelCtr();
        }
    }

    /**
     * Control when the player want to change the level.
     *
     * @throws IOException
     */
    private void changeLevelCtr() throws IOException {
        int level;
        int nbAvailableLevels = 10;
        if (view.askChangeLevel()) {
            level = view.askLevel(nbAvailableLevels);
            this.game = new Sokoban(level);
            view.showMaze(game.getMaze(), game.getLevel());
        }
    }

    /**
     * Get the direction of the move from player's command.
     *
     * @param tokens, player's command.
     * @return Direction.
     */
    private Direction moveDirection(String[] tokens) {
        switch (tokens[1].toUpperCase()) {
            case "UP":
                return Direction.UP;
            case "DOWN":
                return Direction.DOWN;
            case "LEFT":
                return Direction.LEFT;
            case "RIGHT":
                return Direction.RIGHT;
            default:
                view.showBadCommand();
                break;
        }
        return null;
    }

    /**
     * Check if the command has enouth argument.
     *
     * @param tokens, the command.
     * @return true if the lengt is good.
     */
    private boolean lengtCommandCtrl(String[] tokens) {
        if (null == tokens[0].toLowerCase()) {
            return tokens.length == COMMAND_LENGHT;
        } else {
            switch (tokens[0].toLowerCase()) {
                case "move":
                    return tokens.length == COMMAND_MOVE_LENGHT;
                case "show":
                    return tokens.length == COMMAND_SHOW_MOVE;
                default:
                    return tokens.length == COMMAND_LENGHT;
            }
        }
    }

}
