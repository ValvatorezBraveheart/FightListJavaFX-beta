package valb.game.logic.gameplay;

import javafx.collections.ObservableList;

import java.util.Map;
import java.util.Vector;



// Only class accessible by controller
// Is also a controller for game state
public final class Game {
    private final Player currentPlayer;
    private int currentGameScore;
    private final Vector<Round> rounds = new Vector<>();
    private Round currentRound; // reduce amount of get()
    private final static int maxRoundIndex = 4; //Up to find round (remember to -1 when comparing to index)
    private int currentRoundIndex = -1; //!!Is the index (initialize -1 and add 1 for each newRound(), which also run in the first round so first round = 1) (can be clearer but i don't want conditional all the time)

    public Game(){
        this.currentPlayer = new Player();
    }
    public Game(String playerName){
        this.currentPlayer = new Player(playerName);
    }
    // Getter/Setter accessible by the controller
    public String getCurrentPlayerName(){
        return this.currentPlayer.getName();
    }
    public int getCurrentScore(){
        return this.currentGameScore;
    }
    //Make sure to disable next round button for if last round
    public int getCurrentRoundIndex() {
        return currentRoundIndex;
    }

    public String getQuestionTitle(int roundIndex){
        // Yes this has a lot of calling overhead, maybe but I want to focus on modularity just to learn the structure
        return this.rounds.get(roundIndex).getQuestionTitle();
    }
    public static int getMaxRoundIndex(){
        return Game.maxRoundIndex;
    }

    public ObservableList<String> getPlayerGuesses(int roundIndex){
        assert roundIndex >= 0;
        return this.rounds.get(currentRoundIndex).getPlayerGuesses();
    }
    // Make sure in the controller that it won't get round number higher than max round
    public Map<String, Integer> getAllAnswers(int roundIndex){
        assert roundIndex >= 0;
        return this.rounds.get(roundIndex).getAnswers();
    }

    // True if found and unique, false else (to determine if board should be updated
    public boolean checkAnswer(String answer){
        return this.currentRound.checkAnswer(answer);
    }

    public void newRound() {
        Round newRound = new Round();
        newRound.pickRandomQuestion();
        currentRound = newRound;
        rounds.add(newRound);
        currentRoundIndex++;
    }

    public int getRoundScore(int roundIndex){
        return rounds.get(roundIndex).getRoundScore();
    }

    //Should be run whenever round end (call by controller when observing countdown timer)
    public void finishRound(){
        currentGameScore += currentRound.getRoundScore();
    }

}
