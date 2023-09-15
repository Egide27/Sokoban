package esi.g51597.atl.sokoban.view;

import esi.g51597.atl.sokoban.model.Direction;
import esi.g51597.atl.sokoban.model.Sokoban;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Contains the graphique view and controller for moving
 *
 * @author Egide Kabanza
 */
public class Navigation extends GridPane {

    private final Sokoban game;
    private final Rectangle upButton;
    private final int SQUARE_SIZE = 30;
    private final Rectangle downButton;
    private final Rectangle undoButton;
    private final Rectangle redoButton;
    private final Rectangle leftButton;
    private final Rectangle rightButton;
    private final ImagePattern UNDO_IMG = new ImagePattern(new Image("file:images/undo.png"));
    private final ImagePattern CANNOT_UNDO_IMG = new ImagePattern(new Image("file:images/cannotUndo.png"));
    private final ImagePattern REDO_IMG = new ImagePattern(new Image("file:images/redo.png"));
    private final ImagePattern CANNOT_REDO_IMG = new ImagePattern(new Image("file:images/cannotRedo.png"));
    private final ImagePattern FLECHE_UP_IMG = new ImagePattern(new Image("file:images/fleche_up.png"));
    private final ImagePattern FLECHE_DOWN_IMG = new ImagePattern(new Image("file:images/fleche_down.png"));
    private final ImagePattern FLECHE_LEFT_IMG = new ImagePattern(new Image("file:images/fleche_left.png"));
    private final ImagePattern FLECHE_RIGHT_IMG = new ImagePattern(new Image("file:images/fleche_right.png"));

    public Navigation(Sokoban game) {
        this.game = game;
        //----direction
        leftButton = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
        addButton(leftButton, FLECHE_LEFT_IMG);
        GridPane.setConstraints(leftButton, 1, 1);
        leftButton.setOnMouseClicked((MouseEvent event) -> {
            game.movePlayer(Direction.LEFT);
        });

        upButton = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
        addButton(upButton, FLECHE_UP_IMG);
        GridPane.setConstraints(upButton, 2, 0);
        upButton.setOnMouseClicked((MouseEvent event) -> {
            game.movePlayer(Direction.UP);
        });

        rightButton = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
        addButton(rightButton, FLECHE_RIGHT_IMG);
        GridPane.setConstraints(rightButton, 3, 1);
        rightButton.setOnMouseClicked((MouseEvent event) -> {
            game.movePlayer(Direction.RIGHT);
        });

        downButton = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
        addButton(downButton, FLECHE_DOWN_IMG);
        GridPane.setConstraints(downButton, 2, 2);
        downButton.setOnMouseClicked((MouseEvent event) -> {
            game.movePlayer(Direction.DOWN);
        });

        //----undo redo
        undoButton = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
        addButton(undoButton, CANNOT_UNDO_IMG);
        GridPane.setConstraints(undoButton, 1, 3);
        undoButton.setOnMouseClicked((MouseEvent event) -> {
            game.undo();
        });

        redoButton = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
        addButton(redoButton, CANNOT_REDO_IMG);
        GridPane.setConstraints(redoButton, 3, 3);
        redoButton.setOnMouseClicked((MouseEvent event) -> {
            game.redo();
        });

        this.setPadding(new Insets(0, 50, 10, 0));
    }

    private void addButton(Rectangle square, ImagePattern image) {
        this.getChildren().add(square);
        square.setFill(image);
        square.setStyle("-fx-stroke: black; -fx-stroke-width: 4;");
    }

    void updateRedoLogo() {
        this.getChildren().remove(redoButton);
        if (!game.getRedoStack().empty()) {
            addButton(redoButton, REDO_IMG);
        } else {
            addButton(redoButton, CANNOT_REDO_IMG);
        }
        GridPane.setConstraints(redoButton, 3, 3);
    }

    void updateUndoLogo() {
        this.getChildren().remove(undoButton);
        if (!game.getUndoStack().empty()) {
            addButton(undoButton, UNDO_IMG);
        } else {
            addButton(undoButton, CANNOT_UNDO_IMG);
        }
        GridPane.setConstraints(undoButton, 1, 3);
    }

}
