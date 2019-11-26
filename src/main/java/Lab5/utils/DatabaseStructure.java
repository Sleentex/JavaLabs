package Lab5.utils;

import Lab5.connection.PostgresConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStructure {
    private static final String CREATE_WEAPONS = "CREATE TABLE weapons (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(100) NOT NULL,weaponType VARCHAR(15) NOT NULL,weight integer NOT NULL,damage integer NOT NULL,ammo integer NOT NULL,rateOfFire integer NOT NULL, maxRange integer NOT NULL)";
    private static final String DROP_WEAPONS = "DROP TABLE weapons";

    private static final String CREATE_CHARACTERS = "CREATE TABLE characters (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(100) NOT NULL,health integer NOT NULL,weapon_id integer NOT NULL REFERENCES weapons(id))";
    private static final String DROP_CHARACTERS = "DROP TABLE characters";

    public static void createTables() throws SQLException {
        Connection connection = PostgresConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_WEAPONS);
        statement.executeUpdate(CREATE_CHARACTERS);
    }

    public static void dropTables() throws SQLException {
        Connection connection = PostgresConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(DROP_WEAPONS);
        statement.executeUpdate(DROP_CHARACTERS);
    }
}