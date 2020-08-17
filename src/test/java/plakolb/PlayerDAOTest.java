package plakolb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import plakolb.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerDAOTest {

    @Test
    @DisplayName("Test that a new player can be inserted into database")
    public void testInsertNewPlayer() throws SQLException {

        PlayerDAO playerDAO = new PlayerDAO();
        Player testPlayer = new Player("testName", "testPassword");

        Assertions.assertTrue(playerDAO.insertNewPlayerToDatabase(testPlayer));

    }

    @Test
    @DisplayName("Test that a correct password grants access to profile page")
    public void testCorrectPassword() throws SQLException {

        PlayerDAO playerDAO = new PlayerDAO();
        Player testPlayer = new Player("testName", "testPassword");

        Assertions.assertTrue(playerDAO.loginPlayer(testPlayer.getUsername(), testPlayer.getPassword()));

    }

    @Test
    @DisplayName("Test that an incorrect password does not grant access to profile page")
    public void testIncorrectPassword() throws SQLException {

        PlayerDAO playerDAO = new PlayerDAO();
        Player testPlayer = new Player("testName", "password");

        Assertions.assertFalse(playerDAO.loginPlayer(testPlayer.getUsername(), testPlayer.getPassword()));
    }

}
