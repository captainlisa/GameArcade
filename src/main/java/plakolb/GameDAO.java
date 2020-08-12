package plakolb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GameDAO {

    Connection connection;


    private Connection openConnection() throws SQLException {
        String database = "game_arcade";
        String username = "root";
        String password = "";
        String connectionUrl = "jdbc:mysql://localhost:3306/"
                + database + "?user="
                + username + "&password=" + password;

        return DriverManager.getConnection(connectionUrl);

    }

    private void closeConnection() throws SQLException {
        connection.close();
    }
}
