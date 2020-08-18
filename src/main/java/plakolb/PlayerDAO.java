package plakolb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {

    Connection connection;

    public boolean loginPlayer(String username, String password) throws SQLException {
        try {
            connection = openConnection();
            //check if username is registered
            boolean isRegistered = false;
            PreparedStatement usernameStatement = connection.prepareStatement("SELECT * FROM player");
            ResultSet usernameResult = usernameStatement.executeQuery();

            while (usernameResult.next()) {
                if (usernameResult.getString("username").equals(username)) {
                    isRegistered = true;
                }
            }

            if (isRegistered) {
                PreparedStatement passwordStatement = connection.prepareStatement("SELECT password FROM player WHERE username = ?");
                passwordStatement.setString(1, username);
                ResultSet passwordResult = passwordStatement.executeQuery();

                while (passwordResult.next()) {
                    String passwordDatabase = passwordResult.getString("password");

                    if (passwordDatabase.equals(password)) {
                        System.out.println("You are now logged in.");
                        ArcadeGOOEY.LOGGED_IN = true;
                        return true;
                    }
                }
            } else {
                System.out.println("Username is not registered. Please create an account.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return false;
    }


    public boolean registerNewPlayer(Player player) throws SQLException {
        try {
            connection = openConnection();
            List<String> usernames = new ArrayList<>();
            PreparedStatement selectUsername = connection.prepareStatement("SELECT username FROM player");
            ResultSet rs = selectUsername.executeQuery();

            if (!rs.next()) {
                //insert new player into database
                return insertNewPlayerToDatabase(player);
            } else {
                while (rs.next()) {
                    usernames.add(rs.getString("username"));
                }

                //check if username already exists in database
                for (String username : usernames) {
                    if (username.equals(player.getUsername())) {
                        System.out.println("Username already exists. Please choose another name.");
                        return false;
                    } else {
                        //insert new player into database
                         return insertNewPlayerToDatabase(player);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
        return false;
    }

    public boolean insertNewPlayerToDatabase(Player player) throws SQLException {

        boolean isSuccessful = false;
        connection = openConnection();
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO player (username, password) VALUES (?, ?)");

        insertStatement.setString(1, player.getUsername());
        insertStatement.setString(2, player.getPassword());

        if (insertStatement.executeUpdate() != 0) {
            isSuccessful = true;
        }

        closeConnection();
        return isSuccessful;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        try {
            boolean isSuccessful = false;
            connection = openConnection();
            String passwordDatabase = "";
            //check if old password is correct
            PreparedStatement getOldPasswordQuery = connection.prepareStatement("SELECT password FROM player WHERE username = ?");
            getOldPasswordQuery.setString(1, username);
            ResultSet rs = getOldPasswordQuery.executeQuery();
            while (rs.next()) {
                passwordDatabase = rs.getString("password");
            }
            if (passwordDatabase.equals(oldPassword)) {
                System.out.println("Old password is not correct.");
                return false;
            } else {
                //update to new password in database
                PreparedStatement setNewPasswordQuery = connection.prepareStatement("UPDATE player SET password = ? WHERE username = ?");
                setNewPasswordQuery.setString(1, newPassword);
                setNewPasswordQuery.setString(2, username);

                if (setNewPasswordQuery.executeUpdate() != 0) {
                    isSuccessful = true;
                }
                return isSuccessful;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    private void closeConnection() throws SQLException {
        connection.close();
    }

}
