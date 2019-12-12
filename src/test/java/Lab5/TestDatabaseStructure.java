package Lab5;

import Lab5.exceptions.PostgresConnectionException;
import Lab5.utils.DatabaseStructure;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TestDatabaseStructure {
    @Test
    public void testDropTables() throws PostgresConnectionException, SQLException {
        DatabaseStructure.dropTables();
    }

    @Test
    public void testCreateTable() throws PostgresConnectionException, SQLException {
        DatabaseStructure.createTables();
    }
}
