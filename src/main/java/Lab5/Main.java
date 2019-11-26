package Lab5;

import Lab5.connection.PostgresConnection;
import Lab5.exceptions.PostgresConnectionException;
import Lab5.utils.DatabaseStructure;

import java.sql.*;

public class Main {
    private static final String CREATE_WEAPONS = "CREATE TABLE weapons (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(100) NOT NULL,weaponType VARCHAR(15) NOT NULL,weight integer NOT NULL,damage integer NOT NULL,ammo integer NOT NULL,rateOfFire integer NOT NULL, maxRange integer NOT NULL)";
    private static final String INSERT_WEAPONS = "INSERT INTO weapons";

    public static void main(String[] args) {

        try {
            Connection connection = PostgresConnection.getConnection();
            Statement statement = connection.createStatement();
            //statement.executeUpdate(DatabaseStructure.createTables());
            //statement.execute("INSERT INTO weapons VALUES(2, 'M4A1', 'RIFLE1', 5, 6, 7, 8, 9)");

            if(!connection.isClosed()) {
                System.out.println("Соединение с БД установлено!");
            }
            connection.close();
            if(connection.isClosed()) {
                System.out.println("Соединение с БД закрыто!");
            }
        } catch (SQLException | PostgresConnectionException e) {
            System.err.println(e.getMessage());
            //"Неудалось загрузить БД!"
        }
    }
}
