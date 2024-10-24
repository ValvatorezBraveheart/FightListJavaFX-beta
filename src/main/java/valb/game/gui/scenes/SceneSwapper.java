package valb.game.gui.scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// in charge of switching between main menu, tutorial, game scenes...
// Have this cus MainController might be too long if wanting to add other function
final class SceneSwapper {
    private final Stage stage;
    SceneSwapper(Stage stage) {
        this.stage = stage;
    }
    private Scene currentScene;

    // Lazy loader, only load with the next scene cus small scene to load
    private SubController loadScenes(String sceneName){
        String fxmlFilePath = "/valb/game/gui/scenes/";
        Scene scene;
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFilePath + sceneName));
            scene = new Scene(fxmlLoader.load());
            this.stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fxmlLoader.getController();
    }

    //Just making these to be uniform most don't have to use subcontroller/no need to set up
    void switchToMenu(){
        this.loadScenes("MainMenu.fxml");
    }
    void switchToPlayerCreation(){
        this.loadScenes("PlayerCreation.fxml");
    }
    void switchToSetting(){
        this.loadScenes("Setting.fxml");
    }
    void switchToTutorial(){
        this.loadScenes("Tutorial.fxml");
    }
    void switchToLeaderboard(){
        this.loadScenes("Leaderboard.fxml");
    }
    void switchToGameplay(){
        this.loadScenes("Gameplay.fxml");
    }
    void switchToGameMenu(){
        this.loadScenes("GameMenu.fxml");
    }
    void switchToGameSummary(){
        this.loadScenes("GameSummary.fxml");

    }

}
