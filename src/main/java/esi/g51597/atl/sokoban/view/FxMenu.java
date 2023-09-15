package esi.g51597.atl.sokoban.view;

import esi.g51597.atl.sokoban.model.Sokoban;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * Menu of the game.
 *
 * @author Egide Kabanza
 */
public class FxMenu extends Menu {

    /**
     * Built the graphique view for the menu
     *
     * @param game, the game's menu
     * @param view, the root
     */
    public FxMenu(Sokoban game, FxView view) {
        super("Option");
        MenuItem help = new MenuItem("Help");
        MenuItem restart = new MenuItem("Restart");
        restart.setOnAction((ActionEvent event) -> {
            try {
                game.restart();
            } catch (IOException ex) {
                Logger.getLogger(FxMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        MenuItem undoList = new MenuItem("Show done moves");

        undoList.setOnAction((ActionEvent event) -> {
            view.stacksAlert(game.getUndoStack());
        });
        MenuItem redoList = new MenuItem("Show undone moves");
        redoList.setOnAction((ActionEvent event) -> {
            view.stacksAlert(game.getRedoStack());
        });

        this.getItems().addAll(help, restart, undoList, redoList, quit);

    }

}
