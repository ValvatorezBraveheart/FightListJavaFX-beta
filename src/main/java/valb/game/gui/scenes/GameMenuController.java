package valb.game.gui.scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import valb.game.gui.listviews.AnswerListView;
import valb.game.logic.gameplay.Game;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

//Should be the scene between rounds
 public final class GameMenuController extends SubController implements Initializable {

    @FXML private ListView<String> listViewPlayerGuesses;
    @FXML private ListView<String> listViewAllAnswers;
    @FXML private ToggleButton toggleButtonRevealAnswers;
    @FXML private  Label labelTimer;
    @FXML private  Label labelQuestionTitle;
    @FXML private  Label labelPlayerName;
    @FXML private  Label labelRoundNumber;
    @FXML private  Label labelMaxRoundNumber;
    @FXML private  Button buttonNext;
    private Game currentGame;
    //Maybe get a timer?

    void switchToGameplay(){
        mainController.switchToGameplay();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setUpLogic();
        this.setUpGUI();
    }
    private void setUpLogic(){
        currentGame = mainController.getGame();
        //Maybe add in timer to immediately go next round
    }
    private void setUpGUI(){
        assert currentGame !=null;
        // Player info
        labelPlayerName.setText(currentGame.getCurrentPlayerName());
        labelRoundNumber.setText(Integer.toString(currentGame.getCurrentRoundIndex()+1));
        labelMaxRoundNumber.setText(Integer.toString(Game.getMaxRoundIndex()+1));
        labelQuestionTitle.setText(currentGame.getQuestionTitle(currentGame.getCurrentRoundIndex()));
        // Guesses
        ObservableList<String> guesses = currentGame.getPlayerGuesses(currentGame.getCurrentRoundIndex());
        listViewPlayerGuesses.setItems(guesses);

        //Next button
        if (currentGame.getCurrentRoundIndex() == Game.getMaxRoundIndex()){
            buttonNext.setText("End Game");
        }
        labelTimer.setVisible(false);

        // Set up guesses table and styling
        listViewPlayerGuesses.setItems(guesses);
        listViewPlayerGuesses.setCellFactory(_ -> new AnswerListView());
        listViewAllAnswers.setCellFactory(_ -> new AnswerListView());
    }

    @FXML
    private void buttonNextOnAction(ActionEvent event){
        mainController.playButtonSFX();
        if (currentGame.getCurrentRoundIndex() != Game.getMaxRoundIndex()){
            // When not at last round
            mainController.switchToGameplay();
        } else {
            // Create a summary screen
            mainController.switchToGameSummary();
        }
    }
    @FXML
    private void buttonEndOnAction(ActionEvent event){
        mainController.playButtonSFX();
        // Continuously create game until last round (might be faster way, but this si not heavy anyway)
        while(currentGame.getCurrentRoundIndex()!=Game.getMaxRoundIndex()){
            currentGame.newRound();
        }
        mainController.switchToGameSummary();
    }

    @FXML
    private void toggleButtonRevealAnswersOnAction(ActionEvent event){
        mainController.playButtonSFX();
        // Show all the answers if reveal is selected
        if (toggleButtonRevealAnswers.isSelected()) {
            Map<String, Integer> answers = currentGame.getAllAnswers(currentGame.getCurrentRoundIndex());
            ObservableList<String> guesses = FXCollections.observableArrayList();
            for (String answer : answers.keySet()) {
                guesses.add(answer + "|" + answers.get(answer));  // Concatenating answer and its value
            }
            listViewAllAnswers.setItems(guesses);  // Display guesses in ListView
        } else {
            listViewAllAnswers.getItems().clear();  // Hide answers if toggle button is deselected
        }
    }




}
