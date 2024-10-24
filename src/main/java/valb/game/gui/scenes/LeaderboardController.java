package valb.game.gui.scenes;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.util.Pair;
import valb.game.gui.listviews.LeaderboardListView;
import valb.game.logic.gameplay.LeaderboardDAO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

// Not a concern for now
public final class LeaderboardController extends SubController implements Initializable {
    @FXML
    private Button buttonReturn;
    @FXML
    private ListView<String> listViewLeaderboard;
    LeaderboardDAO leaderboardDAO = new LeaderboardDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.updateLeaderboard();
        this.setUpGUI();
    }

    // Connect to leaderboard and display all top players
    private void setUpGUI(){
        Pair<List<String>, List<Integer>> leaderboardPair = leaderboardDAO.getLeaderboard();
        ObservableList<String> leaderboard = FXCollections.observableArrayList();
        List<String> usernames = leaderboardPair.getKey();
        List<Integer> scores = leaderboardPair.getValue();
        for (int i =0; i < usernames.size(); i++){
            leaderboard.add(usernames.get(i)+"|"+scores.get(i));
        }
        listViewLeaderboard.setItems(leaderboard);

        listViewLeaderboard.setCellFactory(_ -> new LeaderboardListView());
    }

    // Add the current game if exist and update leaderboard
    private void updateLeaderboard(){
        if (mainController.getGame()!=null){
            this.leaderboardDAO.addEntry(mainController.getGame().getCurrentPlayerName(),mainController.getGame().getCurrentScore());
        }
        this.leaderboardDAO.updateLeaderboard();
    }



    @FXML
    private void buttonReturnOnAction(ActionEvent actionEvent){
        // Basically delete
        mainController.playButtonSFX();
        mainController.deleteGame();
        mainController.switchToMenu();
    }

}
