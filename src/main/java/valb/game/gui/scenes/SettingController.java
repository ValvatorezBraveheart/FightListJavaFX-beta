package valb.game.gui.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

//For now nothing much
// Will be work on at a later date
public class SettingController extends SubController{
    @FXML
    private void buttonReturnOnAction(ActionEvent actionEvent) {
        mainController.playButtonSFX();
        mainController.switchToMenu();
    }
}
