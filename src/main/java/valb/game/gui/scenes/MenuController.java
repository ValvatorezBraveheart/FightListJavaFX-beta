package valb.game.gui.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

//Main menu
public final class MenuController extends SubController {

    // But the variables can be private
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonLeaderboard;
    @FXML
    private Button buttonTutorial;
    @FXML
    private Button buttonSetting;
    @FXML
    private Button buttonExit;

    @FXML
    private void buttonStartOnAction(ActionEvent event){
        mainController.playButtonSFX();
        mainController.switchToPlayerCreation();
    }
    @FXML
    private void buttonLeaderboardOnAction(ActionEvent event){
        mainController.playButtonSFX();
        mainController.switchToLeaderboard();
    }
    @FXML
    private void buttonTutorialOnAction(ActionEvent event){
        mainController.playButtonSFX();
        mainController.switchToTutorial();
    }
    @FXML
    private void buttonSettingOnAction(ActionEvent event){
        mainController.playButtonSFX();
        mainController.switchToSetting();
    }
    @FXML
    private void buttonExitOnAction(ActionEvent event){
        mainController.playButtonSFX();
        mainController.exit();
    }

}
