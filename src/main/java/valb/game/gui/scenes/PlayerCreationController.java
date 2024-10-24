package valb.game.gui.scenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

// Should only take in name and choose a profile pic (for later)
public final class PlayerCreationController extends SubController {

    @FXML
    private Button buttonEnter;
    @FXML
    private Button buttonBack;
    @FXML
    private TextField textFieldPlayerName;

    @FXML
    private void buttonEnterOnAction(ActionEvent event){
        mainController.playButtonSFX();
         String playerName = textFieldPlayerName.getText();
         if (!playerName.isEmpty()){
             mainController.createNewGame(playerName);
             mainController.switchToGameplay();
         }
         // Add in else make the box red or something later
    }

    @FXML
    private void buttonBackOnAction(ActionEvent event){
        mainController.playButtonSFX();
        mainController.switchToMenu();
    }

}
