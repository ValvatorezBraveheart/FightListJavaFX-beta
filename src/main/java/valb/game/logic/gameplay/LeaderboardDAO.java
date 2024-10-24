package valb.game.logic.gameplay;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Local hosting cause I don't want to accidentally mess up something online and regret
// Class to interact with leaderboard
public class LeaderboardDAO {
    private static Dotenv dotenv = Dotenv.load();
    private static final String URL = "jdbc:mysql://localhost:3306/fightlist";
    private static final String USER = dotenv.get("DATABASE_USERNAME"); //singular user for all purpose
    private static final String PASSWORD = dotenv.get("DATABASE_PASSWORD"); //Nah, not like this is important/serious to have password stored in another file

    private Connection connection = null;

    public LeaderboardDAO(){
        this.connect();
    }

    private void connect(){
        try {
            // Establish the connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL+ "?user="+ USER +"&password="+ PASSWORD);

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Connection to database failed.");
            e.printStackTrace();
        }
    }


    public void addEntry(String username, int score){
        try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO leaderboard (username, score) VALUES (?, ?); ")){
            statement.setString(1, username);
            statement.setInt(2, score);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLeaderboard() {
        // Get the count of entries in the leaderboard
        String countSql = "SELECT COUNT(*) FROM leaderboard";

        try (PreparedStatement countStatement = connection.prepareStatement(countSql);
             ResultSet countResultSet = countStatement.executeQuery()) {

            if (countResultSet.next()) {
                int count = countResultSet.getInt(1);  // Get the count of entries

                // Check if the number of entries exceeds 10
                // Usually but just testing
                while (count > 10) {
                    // Get the lowest score
                    try (PreparedStatement deleteStatement = connection.prepareStatement("SELECT id FROM leaderboard ORDER BY score ASC LIMIT 1");
                         ResultSet deleteSet = deleteStatement.executeQuery()) {
                        if (deleteSet.next()) {
                            int id = deleteSet.getInt("id");  // Get the lowest score
                            // Delete the lowest score entry
                            try (PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM leaderboard WHERE id = ?")) {
                                removeStatement.setInt(1, id);
                                int rowsAffected = removeStatement.executeUpdate();
                                // Update the count
                                count--;
                            }
                        } else {
                            break;  // Exit if no more entries to delete
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Pair<List<String>,List<Integer>> getLeaderboard(){
        List<String> usernames = this.getUsernames();
        List<Integer> scores = this.getScores();
        assert usernames.size() == scores.size();
        return new Pair<>(usernames, scores);
    }




    public List<String> getUsernames(){
        List<String> usernames = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT username FROM leaderboard");
             ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                usernames.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usernames;
    }
    public List<Integer> getScores(){
        List<Integer> scores = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT score FROM leaderboard");
             ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                scores.add(resultSet.getInt("score"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return scores;
    }


}
