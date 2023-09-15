package esi.g51597.atl.sokoban.view;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import esi.g51597.atl.sokoban.model.Player;
import esi.g51597.atl.sokoban.model.Square;
import esi.g51597.atl.sokoban.model.Movable;
import esi.g51597.atl.sokoban.model.Sokoban;
import esi.g51597.atl.sokoban.model.Position;

/**
 * The graphique view of the maze
 *
 * @author Egide Kabanza
 */
public final class FxMaze extends GridPane {

    private final Sokoban game;
    private final char CHAR_BOX = 'b';
    private final char CHAR_PLAYER = 'p';
    private final int SQUARE_SIZE = 30;
    private final ImagePattern WALL_IMG = new ImagePattern(new Image("file:images/wall.png"));
    private final ImagePattern BOX_IMG = new ImagePattern(new Image("file:images/boxUse.png"));
    private final ImagePattern PLAYER_IMG = new ImagePattern(new Image("file:images/player.png"));
    private final ImagePattern EMPTY_ZONE_IMG = new ImagePattern(new Image("file:images/empty.png"));
    private final ImagePattern BOX_IN_ZONE_IMG = new ImagePattern(new Image("file:images/boxInZone.png"));
    private final ImagePattern DESTINATION_IMG = new ImagePattern(new Image("file:images/destination.png"));
    private final ImagePattern PLAYER_IN_ZONE_IMG = new ImagePattern(new Image("file:images/playerInZone.png"));

    /**
     * Contruct the maze of a game
     *
     * @param game
     */
    public FxMaze(Sokoban game) {
        this.game = game;
        updateMaze();
    }

    /**
     * Update the maze when it changes
     */
    public void updateMaze() {
        int nbCol = game.getNbCols();
        int nbLine = game.getNbLines();
        Player player = new Player(new Position(0, 0));
        for (int i = 0; i < nbCol; i++) {
            for (int j = 0; j < nbLine; j++) {

                Rectangle rectangle = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);

                Square sq = game.getSquareAt(j, i);
                if (sq.getType() != null) {
                    switch (sq.getType()) {
                        case GOAL:
                            if (sq.getMovable() != null) {
                                switch (sq.getMovable().getMovableType()) {
                                    case CHAR_BOX:
                                        addSquare(rectangle, BOX_IN_ZONE_IMG);
                                        break;
                                    case CHAR_PLAYER:
                                        addSquare(rectangle, PLAYER_IN_ZONE_IMG);
                                        break;
                                }
                            } else {
                                addSquare(rectangle, DESTINATION_IMG);
                            }
                            GridPane.setConstraints(rectangle, i, j);
                            break;
                        case WALL:
                            addSquare(rectangle, WALL_IMG);
                            GridPane.setConstraints(rectangle, i, j);
                            break;
                        case EMPTY:
                            if (sq.getMovable() == null) {
                                addSquare(rectangle, EMPTY_ZONE_IMG);
                            } else {
                                if (sq.getMovable().getMovableType() == CHAR_BOX) {
                                    addSquare(rectangle, BOX_IMG);
                                } else {
                                    addSquare(rectangle, PLAYER_IMG);
                                }
                            }
                            GridPane.setConstraints(rectangle, i, j);
                    }
                } else {
                    Movable movable = game.getSquareAt(j, i).getMovable();
                    if (movable.getClass() == player.getClass()) {
                        addSquare(rectangle, PLAYER_IMG);
                    } else {
                        addSquare(rectangle, BOX_IMG);
                    }
                    GridPane.setConstraints(rectangle, i, j);
                }
            }
        }
    }

    private void addSquare(Rectangle square, ImagePattern image) {
        this.getChildren().add(square);
        square.setFill(image);
        square.setStyle("-fx-stroke: black; -fx-stroke-width: 1;");
    }

}
