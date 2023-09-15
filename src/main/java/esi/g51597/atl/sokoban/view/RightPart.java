package esi.g51597.atl.sokoban.view;

import esi.g51597.atl.sokoban.model.CommandMove;
import esi.g51597.atl.sokoban.model.Sokoban;
import java.util.Stack;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Contains the navigator button and informations about the state of the game.
 *
 * @author Egide Kabanza
 */
public class RightPart extends VBox {

    private final VBox undoStack;
    private final VBox redoStack;

    private final Label undoLabel;
    private final Label redoLabel;

    private final Label levelLabel;
    private final Label nbMoveLabel;
    private final Label missingGoalsLabel;
    private final Navigation navigation;
    private final String styleColorLabel = "-fx-text-fill: #F0F8FF !important; -fx-highlight-text-fill: #000 !important; -fx-font-family: Arial";
    private final String styleColorAlert = "-fx-text-fill: #000 !important; -fx-highlight-text-fill: #000 !important; -fx-font-family: Arial ";
    private final Label totalGoalsLabel;

    public RightPart(Sokoban game) {
        //----info jeu
        levelLabel = new Label("Level : " + game.getLevel());
        levelLabel.setStyle(styleColorLabel);
        levelLabel.setWrapText(true);

        missingGoalsLabel = new Label("Missing Goals : " + game.getMissingGoals());
        missingGoalsLabel.setStyle(styleColorLabel);
        missingGoalsLabel.setWrapText(true);

        totalGoalsLabel = new Label("Total Goals : " + game.getTotalGoals());
        totalGoalsLabel.setStyle(styleColorLabel);
        totalGoalsLabel.setWrapText(true);

        nbMoveLabel = new Label("Moves : " + game.getNbMoves());
        nbMoveLabel.setStyle(styleColorLabel);
        nbMoveLabel.setWrapText(true);

        levelLabel.setPadding(new Insets(15, 15, 0, 15));
        missingGoalsLabel.setPadding(new Insets(15, 15, 0, 15));
        totalGoalsLabel.setPadding(new Insets(15, 15, 0, 15));
        nbMoveLabel.setPadding(new Insets(15, 15, 15, 15));
        this.getChildren().addAll(levelLabel, missingGoalsLabel, totalGoalsLabel, nbMoveLabel);

        navigation = new Navigation(game);
        navigation.setPadding(new Insets(0, 15, 15, 15));
        this.getChildren().add(navigation);

        HBox undoRedoFx = new HBox();
        //---undo part
        undoStack = new VBox();
        undoStack.setPadding(new Insets(0, 10, 0, 0));
        undoLabel = new Label("START");
        undoLabel.setStyle(styleColorLabel);
        undoLabel.setFont(new Font("grand hotel", 10));
        undoStack.getChildren().add(undoLabel);
        undoRedoFx.getChildren().add(undoStack);

        //---redo part
        redoStack = new VBox();
        redoStack.setPadding(new Insets(0, 0, 0, 20));
        redoLabel = new Label("EMPTY");
        redoLabel.setStyle(styleColorLabel);
        redoLabel.setFont(new Font("grand hotel", 10));
        redoStack.getChildren().add(redoLabel);
        undoRedoFx.getChildren().add(redoStack);

        undoRedoFx.setPadding(new Insets(0, 0, 0, 20));
        this.getChildren().add(undoRedoFx);

        setPadding(new Insets(0, 30, 0, 30));
    }

    void updateStack(Stack<CommandMove> stack, Label label) {
        if (stack.empty()) {
            label.setText("EMPTY");
        } else {
            if(stack.peek().isBoxMoved())
                label.setText(stack.peek().getDirection().name() + " |+BOX" );
            else
                label.setText(stack.peek().getDirection().name() );
        }

    }

    /**
     * Update information about the game
     *
     * @param level; the level of the game
     * @param nbMove, number of done moves
     * @param missingGoals, the number of missing goal to achive before winning.
     */
    public void updateInfo(int level, int nbMove, int missingGoals) {
        levelLabel.setText("Level : " + level);
        missingGoalsLabel.setText("Missing Goals : " + missingGoals);
        nbMoveLabel.setText("Moves : " + nbMove);
    }

    void stacksAlert(Stack<CommandMove> stack) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Les PILES");
        Label lab = new Label("\n");
        alert.setHeaderText(null);
        if (stack.empty()) {
            Label moveLabel = new Label("EMPTY");
            moveLabel.setStyle(styleColorAlert);
            moveLabel.setWrapText(true);
            moveLabel.setFont(new Font("grand hotel", 10));
            alert.setContentText(moveLabel.getText());
        } else {
            stack.forEach((move) -> {
                Label moveLabel = new Label(move.getDirection().name() + " | " + move.isBoxMoved());
                moveLabel.setStyle(styleColorAlert);
                moveLabel.setWrapText(true);
                moveLabel.setFont(new Font("grand hotel", 10));
                lab.setText(lab.getText() + "\n" + moveLabel.getText());

            });
        }
        alert.setContentText(lab.getText());
        alert.showAndWait();
    }

    /**
     * Get the control panel for moves.
     *
     * @return Navigation.
     */
    public Navigation getNavigation() {
        return navigation;
    }

    /**
     * Get the label of information for undo
     *
     * @return Label
     */
    public Label getUndoLabel() {
        return undoLabel;
    }

    /**
     * Get the label of information for redo
     *
     * @return Label
     */
    public Label getRedoLabel() {
        return redoLabel;
    }

}
