package esi.g51597.atl.sokoban.view;

import esi.g51597.atl.sokoban.model.CommandMove;
import java.util.Scanner;
import esi.g51597.atl.sokoban.model.Maze;
import esi.g51597.atl.sokoban.model.Movable;
import esi.g51597.atl.sokoban.model.Player;
import esi.g51597.atl.sokoban.model.Position;
import esi.g51597.atl.sokoban.model.Square;
import java.util.Stack;

/**
 * THe console view of Sokoban
 * @author Egide Kabanza
 */
public class View {
    
    private final Scanner in;
    
    private final char WALL = '#';
    private final char BOX = '$';
    private final char DESTINATION = '.';
    private final char BOX_IN_ZONE = '*';
    private final char PLAYER = '@';
    private final char PLAYER_IN_ZONE = '+';
    private final char EMPTY_ZONE = ' ';
    private final char CHAR_BOX = 'b';
    private final char CHAR_PLAYER = 'p';
    
    /**
     * Construct the view of a sokoban game 
     */
    public View(){
        this.in = new Scanner(System.in);
    }
    
    /**
     * Display the start of the game
     * @param maze, the playground
     * @param level, the level of the game.
     */
    public void showStart(Maze maze, int level){
        System.out.println("###################################");
        System.out.println("***** WELCOME TO SOKOBAN GAME ****");
        System.out.println("###################################");
        showMaze(maze, level);
        
    }
    
    /**
     * Show the numbers of moes the player has already made
     * @param nbMoves, the numbers of moves 
     */
    public void showNbMoves(int nbMoves){
        System.out.println("  ########## You have made "+ nbMoves +" moves #########");
    }
    
    /**
     * Display when tha game is winned.
     * @param level, level of the winned game.
     * @param nbMoves, number of done moves. 
     */
    public void showWinningGame(int level, int nbMoves){
        System.out.println("################################################");
        System.out.println("    ****** BRAVO YOU WON THE LEVEL "+level+" ******");
        showNbMoves(nbMoves);
        System.out.println("################################################");
    }
    
    /**
     * Ask the level of the wanted maze.
     * @param nbFiles, number of levels available.
     * @return int, the level of the game.
     */
    public int askLevel(int nbFiles){
        System.out.println("Enter the number of the level.");
        int level = in.nextInt();
        while(level < 0 || level > nbFiles){
            System.out.println("Unknown level");
            level = in.nextInt();
        }
        return level;
    }
    
    /**
     * Ask if the plyer want to change the level of the maze.
     * @return true if he want, else, return false
     */
    public boolean askChangeLevel(){
        System.out.println("Do you want to change level ? yes / no");
        String change = in.nextLine();
        while(!"yes".equals(change.toLowerCase()) && !"no".equals(change.toLowerCase())){
            System.out.println("You entered an inapropriate answer");
            System.out.println("Choose between the two ansewrs just bellow");
            System.out.println("yes");
            System.out.println("no");
            change = in.nextLine();
        }
        return "yes".equals(change.toLowerCase());
    }
    
    /**
     * Display the possible option for the player.
     */
    public void showOption(){
        System.out.println("***********************************");
        System.out.println(" move direction ");
        System.out.println(" help ");
        System.out.println(" undo ");
        System.out.println(" redo ");
        System.out.println(" next ");
        System.out.println(" Show undo list ");
        System.out.println(" Show redo list ");
        System.out.println(" restart ");
        System.out.println(" quit ");
        System.out.println("***********************************");
    }
    
    /**
     * space where the player can put his choice.
     * @return String, the choice of the player.
     */
    public String prompt() {
        String command;
        System.out.print("Sokoban >> ");
        command = in.nextLine();
        return command;
    }


    /**
     * Show the maze where the game is played
     * @param maze, the played maze
     * @param level
     */
    public void showMaze(Maze maze, int level){
        System.out.println("THIS IS THE LEVEL : "+ level);
        Player player = new Player(new Position(0, 0));
        for(int i = 0; i< maze.getNbLine(); i++){
            for(int j = 0; j<maze.getNbCol(); j++){
                
                Square sq = maze.getSquareAt(i, j);
                if(sq.getType() != null){
                    switch(sq.getType()){
                            case GOAL:
                                if(sq.getMovable() != null){
                                    switch (sq.getMovable().getMovableType()) {
                                        case CHAR_BOX:
                                            System.out.print(BOX_IN_ZONE);
                                            break;
                                        case CHAR_PLAYER:
                                            System.out.print(PLAYER_IN_ZONE);
                                            break;
                                    }
                                }else {
                                    System.out.print(DESTINATION);

                                }
                                break;

                            case WALL:
                                System.out.print(WALL);
                                break;
                            case EMPTY:
                                if(sq.getMovable() == null){
                                    System.out.print(EMPTY_ZONE);
                                }else{
                                    if(sq.getMovable().getMovableType() == CHAR_BOX)
                                        System.out.print(BOX);
                                    else
                                        System.out.print(PLAYER);
                                }
                    }    
                } else {
                    Movable movable = maze.getSquareAt(i, j).getMovable();
                    if(movable.getClass() == player.getClass() )
                        System.out.print(PLAYER);
                    else
                        System.out.print(BOX);
                }
                
            }
            System.out.println("");
        }
        
    }
    
    public void showGoals(String head, int nbGoals){
        System.out.println(head +nbGoals);
    }
    
    /**
     * Displaa a message when the player's command is invalid.
     */
    public void showBadCommand(){
        System.out.println("THIS COMMAND IS INVALID");
    }
    
    /**
     * Display the content of a stack
     * @param stack
     */
    public void showStack(Stack<CommandMove> stack) {
        if (stack.empty()) {
            System.out.println("Empty stack");
        } else {
            stack.forEach((move) -> {
                System.out.println(move.getDirection());
            });
        }

    }
    
}
