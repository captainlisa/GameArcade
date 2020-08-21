package plakolb;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;

public class ScoreboardDAO {

    Connection connection;


    public void savePlayerScore(int score, Game game) throws SQLException {

        Player player = ArcadeGOOEY.NEW_PLAYER;
        connection = openConnection();

        PlayerDAO playerDAO = new PlayerDAO();
        int playerId = playerDAO.getPlayerId(player);
        GameDAO gameDAO = new GameDAO();
        int gameId = gameDAO.getGameId(game);

        PreparedStatement getScore = connection.prepareStatement("SELECT points_reached FROM scoreboard WHERE player_id = ? AND game_id = ?");
        getScore.setInt(1, playerId);
        getScore.setInt(2, gameId);
        ResultSet rs = getScore.executeQuery();

        if (!rs.next()) {
            PreparedStatement saveScore = connection.prepareStatement("INSERT INTO scoreboard (points_reached, player_id, game_id) VALUES (?, ?, ?)");
            saveScore.setInt(1, score);
            saveScore.setInt(2, playerId);
            saveScore.setInt(3, gameId);
            saveScore.executeQuery();
        } else {
            PreparedStatement updateScore = connection.prepareStatement("UPDATE scoreboard SET points_reached = ? WHERE player_id = ? AND game_id = ?");
            updateScore.setInt(1, score);
            updateScore.setInt(2, playerId);
            updateScore.setInt(3, gameId);
        }

        connection.close();

    }


    private Connection openConnection() throws SQLException {
        String database = "game_arcade";
        String username = "root";
        String password = "";
        String connectionUrl = "jdbc:mysql://localhost:3306/"
                + database + "?user="
                + username + "&password=" + password;

        return DriverManager.getConnection(connectionUrl);

    }


}
