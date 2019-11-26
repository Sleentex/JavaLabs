package Lab5.connection;

import Lab5.exceptions.PostgresConnectionException;

import javax.management.PersistentMBean;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnection
{
    /*private final static String HOST = "jdbc:postgresql://localhost:5432/weapon";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "111231";*/

    private final static String PROPERTIES_PATH = "database.properties";
    private Properties properties = new Properties();

    private Connection createConnection() throws PostgresConnectionException {
        try {
            properties.load(new FileInputStream(PROPERTIES_PATH));
            return DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        } catch (Exception e) {
            throw new PostgresConnectionException(e.getMessage());
        }
    }

    public static Connection getConnection() throws PostgresConnectionException {
        return new PostgresConnection().createConnection();
    }

   /* public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(HOST, USERNAME, PASSWORD);
    }*/
}
