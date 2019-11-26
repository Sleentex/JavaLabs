package Lab5;

import Lab5.connection.PostgresConnection;
import Lab5.exceptions.PostgresConnectionException;
import Lab5.utils.DatabaseStructure;

import java.sql.*;

public class Main {
    private static final String CREATE_WEAPONS = "CREATE TABLE weapons (id SERIAL NOT NULL PRIMARY KEY,name VARCHAR(100) NOT NULL,weaponType VARCHAR(15) NOT NULL,weight integer NOT NULL,damage integer NOT NULL,ammo integer NOT NULL,rateOfFire integer NOT NULL, maxRange integer NOT NULL)";
    private static final String INSERT_WEAPONS = "INSERT INTO weapons VALUES(?,?,?,?,?,?,?,?)";
    private static final String GET_ALL_WEAPONS = "SELECT * FROM weapons";
    private static final String DELETE_WEAPONS = "DELETE FROM weapons WHERE id=?";

    public static void main(String[] args) {
        try {
            Connection connection = PostgresConnection.getConnection();
            Statement statement = connection.createStatement();
            //statement.executeUpdate(DatabaseStructure.createTables());
            //statement.execute("INSERT INTO weapons VALUES(3, 'M4A1', 'RIFLE2', 5, 6, 7, 8, 9)");
            //statement.executeUpdate("DELETE FROM weapons ");


            /*INSERT_WEAPONS*/
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WEAPONS);
            preparedStatement.setLong(1, 2);
            preparedStatement.setString(2, "M4A1");
            preparedStatement.setString(3, "RIFLE");
            preparedStatement.setInt(4, 4);
            preparedStatement.setInt(5, 5);
            preparedStatement.setInt(6, 90);
            preparedStatement.setInt(7, 7);
            preparedStatement.setInt(8, 400);
            preparedStatement.execute();

            /*GET_ALL_WEAPONS*/
           /*PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_WEAPONS);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               Long id = resultSet.getLong("id");
               String name = resultSet.getString("name");
               String weaponType = resultSet.getString("weaponType");
               Integer weight = resultSet.getInt("weight");
               Integer damage = resultSet.getInt("damage");
               Integer ammo = resultSet.getInt("ammo");
               Integer rateoffire = resultSet.getInt("rateoffire");
               Integer maxrange = resultSet.getInt("maxrange");

               System.out.println( "Weapon{" +
                       "id=" + id +
                       ", name='" + name + '\'' +
                       ", weaponType=" + weaponType +
                       ", weight=" + weight + "kg" +
                       ", damage=" + damage +
                       ", ammo=" + ammo +
                       ", rateOfFire=" + rateoffire +
                       ", maxRange=" + maxrange +
                       '}');
           }*/


            /*DELETE_WEAPONS*/
            /*PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WEAPONS);
            preparedStatement.setInt(1, 2);
            preparedStatement.executeUpdate();*/

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
