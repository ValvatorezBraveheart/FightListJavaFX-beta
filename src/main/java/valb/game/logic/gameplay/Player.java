package valb.game.logic.gameplay;

//Arcade type saving (no account, just name)
//Learning database to save the high scores
//Should only be accessible within logic
// Later also add in icon imgae
// Later on can implement account base by sepeartign like this
class Player {
    private String name;
    //private int totalScore =0; // Will be use later to save account data

    Player () {
        this("Anonymous");
    }
    Player (String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }
    void setName(String name) {
        this.name = name;
    }
    /*
    int getTotalScore() {
        return this.totalScore;
    }
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    void addScore(int scoreToAdd){
        this.totalScore += scoreToAdd;
    }
    */
}
