package valb.game.launcher;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.application.Application;
import javafx.stage.Stage;
import valb.game.gui.scenes.MainController;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainController mainController = new MainController(stage);
        mainController.start();
    }
    public static void main(String[] args) {
        launch();
    }
}

