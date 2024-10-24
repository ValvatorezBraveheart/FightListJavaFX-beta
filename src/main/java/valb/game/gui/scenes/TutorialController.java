package valb.game.gui.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

// Not a concern for now
// Might delete since there's a before game tutorial now
public class TutorialController extends SubController{
    @FXML
    private Button buttonBack;

    @FXML
    private void buttonBackOnAction(ActionEvent event){
        mainController.playButtonSFX();
        mainController.switchToMenu();
    }
}
