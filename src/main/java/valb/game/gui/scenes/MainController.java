package valb.game.gui.scenes;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import valb.game.logic.sound.SoundController;
import valb.game.logic.gameplay.Game;


// Hold onto the Game state
// Manages everything that's common between scenes
public class MainController {
    private final Stage  stage;
    private Game game;
    private static SceneSwapper sceneSwapper;
    private static SoundController soundController;

    public MainController(Stage stage){
        new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                soundController = new SoundController();
                return null;
            }
        }).start();
        this.stage = stage;
        sceneSwapper = new SceneSwapper(stage);
        SubController.mainController  = this;
    }

    public void start(){
        stage.setTitle("BudgetFightList by ValB");
        stage.setResizable(false); // Make resizing in the setting instead to better control the font
        stage.getIcons().add(new Image("file:src/main/resources/valb/game/images/logo.png"));
        sceneSwapper.switchToMenu();
        stage.show();
    }

    void playButtonSFX(){
        soundController.playButtonSFX();
    }

    void playIncorrectSFX(){
        soundController.playIncorrectSFX();
    }
    void playCorrectSFX(){
        soundController.playCorrectSFX();
    }

    void createNewGame(String playerName){
        this.game = new Game(playerName);
    }

    Game getGame(){
        return this.game;
    }

    void deleteGame(){
        this.game = null;
    }

    void switchToGameplay() {
        sceneSwapper.switchToGameplay();
    }
    void switchToGameMenu(){
        sceneSwapper.switchToGameMenu();
    }

    void switchToMenu(){
        sceneSwapper.switchToMenu();
    }
    void switchToPlayerCreation(){
        sceneSwapper.switchToPlayerCreation();
    }
    void switchToSetting(){
        sceneSwapper.switchToSetting();
    }
    void switchToTutorial(){
        sceneSwapper.switchToTutorial();
    }
    void switchToLeaderboard(){
        sceneSwapper.switchToLeaderboard();
    }
    void exit(){
        stage.close();
    }

    void switchToGameSummary() {
        sceneSwapper.switchToGameSummary();
    }
}
