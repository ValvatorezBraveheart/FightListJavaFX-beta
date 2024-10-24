package valb.game.gui.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import valb.game.logic.gameplay.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class GameSummaryController extends SubController implements Initializable {
    @FXML private VBox vBoxRound1;
    @FXML private VBox vBoxRound2;
    @FXML private VBox vBoxRound3;
    @FXML private VBox vBoxRound4;
    @FXML private VBox vBoxRound5;
    @FXML private Label labelScore1;
    @FXML private Label labelScore2;
    @FXML private Label labelScore3;
    @FXML private Label labelScore4;
    @FXML private Label labelScore5;
    @FXML private Label labelScoreOverall;
    private Game currentGame;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setUpLogic();
        this.setUpGUI();
    }

    private void setUpLogic(){
        currentGame = mainController.getGame();
    }
    private void setUpGUI(){
        // Set up tabs of games
        labelScore1.setText(Integer.toString(currentGame.getRoundScore(0)));
        labelScore2.setText(Integer.toString(currentGame.getRoundScore(1)));
        labelScore3.setText(Integer.toString(currentGame.getRoundScore(2)));
        labelScore4.setText(Integer.toString(currentGame.getRoundScore(3)));
        labelScore5.setText(Integer.toString(currentGame.getRoundScore(4)));
        labelScoreOverall.setText(Integer.toString(currentGame.getCurrentScore()));
    }
    @FXML
    private void finish(ActionEvent event){
        mainController.switchToMenu();
    }


}
