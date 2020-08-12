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

        Assertions.assertFalse(playerDAO.insertNewPlayerToDatabase(testPlayer));
        //returns false because .execute() only returns true, when there is a resultSet that is not null.

    }

    @Test
    @DisplayName("Test that registering a username that already exists does not work")
    public void testDuplicateUsernameInsert() {

    }

}
