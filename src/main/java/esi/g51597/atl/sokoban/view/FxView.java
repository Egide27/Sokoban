package esi.g51597.atl.sokoban.view;

import java.io.File;
import java.util.List;
import java.util.Stack;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.geometry.Pos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
import javafx.scene.control.MenuBar;
import javafx.application.Application;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import esi.g51597.atl.sokoban.model.Sokoban;
import javafx.scene.control.Alert.AlertType;
import esi.g51597.atl.sokoban.model.Observer;
import esi.g51597.atl.sokoban.model.Direction;
import esi.g51597.atl.sokoban.model.CommandMove;
import javafx.scene.control.Label;

/**
 * Main graphique view of the game
 *
 * @author Egide Kabanza
 */
public class FxView extends Application implements Observer {

    private VBox root;
    private FxMenu menu;
    private FxMaze maze;
    private Sokoban game;
    private HBox gameBox;
    private File[] files;
    private RightPart right;
    private File levelsFolder;

    /**
     * Main methode of the graphhique view
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setMaxHeight(700);
        primaryStage.setMaxWidth(1100);
        primaryStage.setTitle("Sokoban");
        root = new VBox();
        root.setStyle("-fx-background-color:POWDERBLUE");

        levelsFolder = new File("levels");
        files = levelsFolder.listFiles();

        gameBox = new HBox();
        gameBox.setStyle("-fx-background-color:#003333");

        game = new Sokoban();

        // ALERT Niveau
        introAlert();

        game.registerObserver(this);

        // LA BAR MENU
        menu = new FxMenu(game, this);
        MenuBar menuBar = new MenuBar(menu);
        root.getChildren().add(menuBar);

//        LE MAZE
        maze = new FxMaze(game);
        gameBox.getChildren().add(maze);
        maze.setAlignment(Pos.CENTER);

        root.getChildren().add(gameBox);

        right = new RightPart(game);
        gameBox.getChildren().add(right);

        Scene scene = new Scene(root);

        // Keyboard using
        moveByKeyboard(scene);

//        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void update() {
        maze.updateMaze();
        right.updateInfo(game.getLevel(), game.getNbMoves(), game.getMissingGoals());
        right.getNavigation().updateRedoLogo();
        right.getNavigation().updateUndoLogo();
        updateStack(game.getUndoStack(), right.getUndoLabel());
        updateStack(game.getRedoStack(), right.getRedoLabel());
        if (game.isWinner()) {
            try {
                winAlert();
            } catch (IOException ex) {
                Logger.getLogger(FxView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void updateStack(Stack<CommandMove> stack, Label label) {
        right.updateStack(stack, label);
    }

    private void winAlert() throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Congratulation!");
        alert.setHeaderText(null);
        alert.setContentText("Congratulation!! You succeed!! \n "
                      + " Level : " + game.getLevel() + "\n"
                      + " Number of moves : " + game.getNbMoves()
                      + "\n Make a choice for.");
        ButtonType nextLevelButton = new ButtonType("Next Level");
        ButtonType restartButton = new ButtonType("Restart");
        ButtonType quitButton = new ButtonType("Quit");
        alert.getButtonTypes().setAll(nextLevelButton, restartButton, quitButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(nextLevelButton)) {
            maze.getChildren().clear();
            game.nextLevel();
        }
        if (result.get().equals(restartButton)) {
            game.restart();
        }
        if (result.get().equals(quitButton)) {
            Platform.exit();
        }
    }

    private void introAlert() throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sokoban - Intro");
        alert.setHeaderText(null);
        alert.setContentText("You are going to play the level " + game.getLevel() + "\n  Keep going on this level ?");
        ButtonType changeButton = new ButtonType("Change Level");
        ButtonType continueButton = new ButtonType("Continue");
        ButtonType quitButton = new ButtonType("Quit");
        alert.getButtonTypes().setAll(continueButton, changeButton, quitButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == changeButton) {
            changeLevelAlert();
        } else if (result.get() == quitButton) {
            Platform.exit();
        }
    }

    private void changeLevelAlert() throws IOException {
        List<String> levels = new ArrayList<>();
        for (File file : files) {
            levels.add(file.getName());
        }
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Select a level", levels);
        dialog.setTitle("Choose a level");
        dialog.setHeaderText(null);
        dialog.setContentText("Choose your level : ");
        Optional<String> chooseLevel = dialog.showAndWait();
        if (chooseLevel.isPresent()) {
            char a = chooseLevel.get().charAt(0);
            int b = Integer.parseInt(String.valueOf(a));
            game = new Sokoban(b);
        }
    }

    public void stacksAlert(Stack<CommandMove> stack) {
        right.stacksAlert(stack);
    }

    /**
     * Allow to moe the player by using the keyboard
     *
     * @param scene
     */
    private void moveByKeyboard(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                switch (ke.getCode()) {
                    case UP:
                        game.movePlayer(Direction.UP);
                        ke.consume();
                        break;
                    case DOWN:
                        game.movePlayer(Direction.DOWN);
                        ke.consume();
                        break;
                    case LEFT:
                        game.movePlayer(Direction.LEFT);
                        ke.consume();
                        break;
                    case RIGHT:
                        game.movePlayer(Direction.RIGHT);
                        ke.consume();
                        break;
                }
            }
        });
    }
}
