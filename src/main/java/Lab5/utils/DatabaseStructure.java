package Lab5.utils;

import Lab5.connection.PostgresConnection;
import Lab5.exceptions.PostgresConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseStructure {
    private static final String CREATE_WEAPONS = "CREATE TABLE weapons (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(100) NOT NULL,weaponType VARCHAR(15) NOT NULL,weight integer NOT NULL,damage integer NOT NULL,ammo integer NOT NULL,rateOfFire integer NOT NULL, maxRange integer NOT NULL)";
    private static final String DROP_WEAPONS = "DROP TABLE weapons";

    private static final String CREATE_CHARACTERS = "CREATE TABLE characters (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(100) NOT NULL,health integer NOT NULL)";
    private static final String DROP_CHARACTERS = "DROP TABLE characters";

    private static final String CREATE_CHARACTER_WEAPONS = "CREATE TABLE character_weapons (id_character integer NOT NULL REFERENCES characters(id), id_weapon integer NOT NULL REFERENCES weapons(id), ammo integer NOT NULL)";
    private static final String DROP_CHARACTERS_WEAPONS = "DROP TABLE character_weapons";

    public static void createTables() throws SQLException, PostgresConnectionException {
        Connection connection = PostgresConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(CREATE_WEAPONS);
        statement.executeUpdate(CREATE_CHARACTERS);
        statement.executeUpdate(CREATE_CHARACTER_WEAPONS);

    }

    public static void dropTables() throws SQLException, PostgresConnectionException {
        Connection connection = PostgresConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(DROP_WEAPONS);
        statement.executeUpdate(DROP_CHARACTERS);
        statement.executeUpdate(DROP_CHARACTERS_WEAPONS);

    }
}
