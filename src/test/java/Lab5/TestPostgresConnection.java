package Lab5;

import Lab5.connection.PostgresConnection;
import Lab5.exceptions.PostgresConnectionException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;

public class TestPostgresConnection {
    @Test
    public void testConnection() throws PostgresConnectionException {
        Connection connection = PostgresConnection.getConnection();
        Assert.assertNotNull(connection);
    }
}
