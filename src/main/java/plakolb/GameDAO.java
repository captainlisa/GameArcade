package plakolb;

import java.sql.*;

public class GameDAO {

    Connection connection;

    public int getGameId(Game game) throws SQLException {

        connection = openConnection();
        PreparedStatement getIdStmt = connection.prepareStatement("SELECT game_id FROM game WHERE game_name = ?");
        getIdStmt.setString(1, game.getGameName());
        ResultSet rs = getIdStmt.executeQuery();
        int gameId = -1;

        while (rs.next()) {
            gameId = rs.getInt("game_id");
        }

        connection.close();

        return gameId;
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
