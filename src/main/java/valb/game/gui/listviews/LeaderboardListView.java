package valb.game.gui.listviews;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

public class LeaderboardListView extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        Font dimboRegular = Font.loadFont(getClass().getResourceAsStream("src/main/resources/valb/game/gui/fonts/DimboRegular.ttf"), 16);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Split name and score
            String[] parts = item.split("\\|");
            Label username = new Label(parts[0].trim());
            Label score = new Label(parts[1].trim());
            String textStyle = "-fx-font-size: 20; " +
                    "-fx-text-fill: black;";
            //Set style
            username.setStyle(textStyle);
            score.setStyle(textStyle);
            //Set the custom font
            username.setFont(dimboRegular);
            score.setFont(dimboRegular);

            HBox hbox = new HBox();
            Region spacing = new Region();
            HBox.setHgrow(spacing, Priority.ALWAYS);
            hbox.getChildren().addAll(username, spacing, score);
            setGraphic(hbox);
        }
    }
}
