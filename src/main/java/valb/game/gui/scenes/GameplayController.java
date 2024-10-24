package valb.game.gui.scenes;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import valb.game.logic.timer.RoundTimer;
import valb.game.logic.timer.RoundTimerObserver;
import valb.game.logic.timer.StartCountObserver;
import valb.game.logic.timer.StartCountTimer;
import valb.game.gui.listviews.AnswerListView;
import valb.game.logic.gameplay.Game;

import java.net.URL;
import java.util.ResourceBundle;

// The game interface, display question have a box for answer, give up, and a countdown timer
// Perhaps later also add an overlay to go to setting or give
public final class GameplayController extends SubController implements RoundTimerObserver, StartCountObserver, Initializable {
    @FXML private AnchorPane anchorPaneTutorialOverlay;
    @FXML private Label labelQuestionTitle;
    @FXML private ListView<String> listViewGuesses;
    @FXML private ProgressBar progressBarTimer;
    @FXML private TextField textFieldAnswer;
    @FXML private Label labelTimer;
    @FXML private Label labelPlayerName;
    @FXML private ProgressBar progressRound;
    @FXML private Label labelRoundNumber;
    @FXML private Label labelMaxRoundNumber;
    @FXML private Label labelCountDownStart;
    @FXML private HBox hBoxCountDown;
    private RoundTimer roundTimer;
    private Game currentGame;
    private StartCountTimer startCountTimer;
    /*
    * Setting up the gameplay section
    */
    // Run in main controller when switching scene

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting up stuffs
        this.setUpLogic();
        // Starting the round
        if (currentGame.getCurrentRoundIndex()==-1){
            // First round (show tutorial) (timer doesn't start until confirm)
            hBoxCountDown.setVisible(false);
            anchorPaneTutorialOverlay.setVisible(true);
        } else {
            startRound();
        }
    }

    // Set up timers
    private void setUpLogic(){
        this.currentGame = mainController.getGame();
        //Timer Setup
        roundTimer = new RoundTimer();
        startCountTimer = new StartCountTimer();
        roundTimer.addObserver(this);
        startCountTimer.addObserver(this);
    }
    // Various action to setUpGameplay screen (not run until starting the round)
    private void setUpGameplayGUI(){
        assert currentGame != null;
        // Listing of names/rounds (question will not be displayed yet)
        labelRoundNumber.setText(Integer.toString(currentGame.getCurrentRoundIndex()+1));
        labelMaxRoundNumber.setText(Integer.toString(Game.getMaxRoundIndex()+1));
        labelPlayerName.setText(currentGame.getCurrentPlayerName());
        labelQuestionTitle.setText(currentGame.getQuestionTitle(currentGame.getCurrentRoundIndex()));
        //Progress bar
        progressRound.setProgress((double) (currentGame.getCurrentRoundIndex()+1)/(Game.getMaxRoundIndex()+1));
        //Guess table
        ObservableList<String> guesses = currentGame.getPlayerGuesses(currentGame.getCurrentRoundIndex());
        listViewGuesses.setItems(guesses);
        listViewGuesses.setCellFactory(_ -> new AnswerListView());
        // Set up guesses table and styling
    }

    //Setting up the question, labels, timer stuffs
    private void startRound(){
        // Doing in this order to hide??? not like it matter people ain't that fast in the eye or screen not even update
        hBoxCountDown.setVisible(true);
        labelCountDownStart.setText(Integer.toString(startCountTimer.getRemainingTime()));
        anchorPaneTutorialOverlay.setVisible(false);
        currentGame.newRound();
        setUpGameplayGUI();
        startCountTimer.start();
    }
    @Override
    public void startTimerCountDown()
    {
        labelCountDownStart.setText(Integer.toString(startCountTimer.getRemainingTime()));
    }
    // Should close the countdown and start the timer for the round
    @Override
    public void startTimerFinish() {
        hBoxCountDown.setVisible(false);
        roundTimer.start();
    }

    // Constantly updating roudn timer
    @Override
    public void roundTimerCountDown() {
        int timeLeft = roundTimer.getRemainingTime();
        labelTimer.setText(Integer.toString(timeLeft));
        progressBarTimer.setProgress((double) timeLeft /60); // Sets the progress to 75%
        // Change color of progress bar
    }
    // This should immediately switch scene
    @Override
    public void roundTimerFinish() {
        textFieldAnswer.setDisable(true);
        currentGame.finishRound();
        //Something popup and show go to next screen button
        mainController.switchToGameMenu();
    }

    /*
    * Gameplay section
    * */
    // This is called when press enter
    @FXML
    private void textFieldAnswerOnAction(ActionEvent event){
        String answer = textFieldAnswer.getText();
        boolean correct = currentGame.checkAnswer(answer);
        if (correct){
            mainController.playCorrectSFX();
        } else {
            mainController.playIncorrectSFX();
        }
        // Reset field
        textFieldAnswer.setText("");
    }

    @FXML
    private void buttonStartOnAction(ActionEvent event){
        mainController.playButtonSFX();
        startRound();
    }
    @FXML
    private void buttonGiveUpOnAction(ActionEvent event){
        mainController.playButtonSFX();
        roundTimer.skipTime();
    }


}
