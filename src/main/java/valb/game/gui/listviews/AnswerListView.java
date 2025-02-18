package valb.game.gui.listviews;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

// Custom list view template
public class AnswerListView extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        Font dimboRegular = Font.loadFont(getClass().getResourceAsStream("src/main/resources/valb/game/gui/fonts/DimboRegular.ttf"), 16);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // Splitting the name and score into left and right
            String[] parts = item.split("\\|");
            Label answer = new Label(parts[0].trim());
            Label score = new Label(parts[1].trim());
            String textStyle = "-fx-font-size: 20; " +
                    "-fx-text-fill: white;";
            //Setting custom font cause the css don't work together with this apparently
            answer.setFont(dimboRegular);
            score.setFont(dimboRegular);
            //Set color and weight
            answer.setStyle(textStyle);
            score.setStyle(textStyle);
            //Create a bos with spacing
            HBox hbox = new HBox();
            Region spacing = new Region();
            HBox.setHgrow(spacing, Priority.ALWAYS);
            hbox.getChildren().addAll(answer, spacing, score);
            //Changing background based on answer
            switch (parts[1]) {
                case "0" -> setStyle("-fx-background-color: red;");
                case "1" -> setStyle("-fx-background-color: green;");
                case "2" -> setStyle("-fx-background-color: orange;");
                case "3" -> setStyle("-fx-background-color: purple;");
            }
            setGraphic(hbox);
        }
    }
}
