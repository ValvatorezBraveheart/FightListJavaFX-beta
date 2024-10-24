
module game.fightlistjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.jsoup;
    requires java.desktop;
    requires me.xdrop.fuzzywuzzy;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.media;
    requires io.github.cdimascio.dotenv.java;

    opens valb.qaWebScrapper to javafx.fxml;
    opens valb.game.launcher to javafx.fxml;

    opens valb.game.gui.listviews to javafx.fxml;
    opens valb.game.gui.scenes to javafx.fxml;
    exports valb.game.launcher;
    exports valb.game.logic.gameplay;
    opens valb.game.logic.gameplay to javafx.fxml;
    exports valb.game.logic.timer;
    opens valb.game.logic.timer to javafx.fxml;
    exports valb.game.logic.sound;
    opens valb.game.logic.sound to javafx.fxml;
}