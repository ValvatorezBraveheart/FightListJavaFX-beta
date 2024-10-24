package valb.game.logic.gameplay;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.AbstractMap;
import java.util.Map;

// This act like a controller for questions already
final class Round
{
    private int roundScore = 0;
    private final ObservableList<String> playerGuesses =  FXCollections.observableArrayList(); // List of player guesses including the roundScore seperated by "|"
    private final Question question = new Question();

    ObservableList<String> getPlayerGuesses() {
        return playerGuesses;
    }
    //Score of the current round
    int getRoundScore(){
        return this.roundScore;
    }
    String getQuestionTitle() {
        return question.getQuestionTitle();
    }
    public Map<String, Integer> getAnswers() {
        return question.getAnswers();
    }

    void pickRandomQuestion(){
        question.pickRandomQuestion();
    }

    //True if correct and non dupe, False else
    // Also update player guesses and score
    boolean checkAnswer(String answer){
        //Check if duplicate
        AbstractMap.SimpleEntry<String, Integer> result = question.checkAnswer(answer);
        String word = result.getKey();
        int scoreToAdd = result.getValue();
        String lWord = word +"|"+ scoreToAdd;
        if (!this.playerGuesses.contains(lWord)){ // Checked if guessed
            playerGuesses.add(lWord);
            this.roundScore+=scoreToAdd;
            return scoreToAdd != 0;
        }
        return false; //Duplicated
    }

}
