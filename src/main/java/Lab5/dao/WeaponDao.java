package Lab5.dao;

import Lab5.connection.PostgresConnection;
import Lab5.exceptions.WeaponDaoException;
import Lab5.model.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WeaponDao implements Dao<Weapon>{
    private static final String GET_BY_ID = "SELECT * FROM weapons WHERE id=?";
    private static final String GET_ALL_WEAPONS = "SELECT * FROM weapons";
    private static final String INSERT_WEAPONS = "INSERT INTO weapons(name, weaponType, weight, damage, ammo, rateoffire, maxrange) VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE_WEAPONS = "UPDATE weapons SET name=?, weaponType=?, weight=?, damage=?, ammo=?, rateoffire=?, maxrange=? WHERE id=?";
    private static final String DELETE_WEAPONS = "DELETE FROM weapons WHERE id=?";


    private Connection getConnection() throws WeaponDaoException {
        try{
            return PostgresConnection.getConnection();
        } catch (Exception e) {
            throw new WeaponDaoException(e.getMessage());
        }
    }
    @Override
    public Optional<Weapon> findById(Long id) throws WeaponDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Weapon weapon = new Weapon.Builder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setWeaponType(Weapon.WeaponType.valueOf(resultSet.getString("weaponType")))
                        .setWeight(resultSet.getInt("weight"))
                        .setDamage(resultSet.getInt("damage"))
                        .setAmmo(resultSet.getInt("ammo"))
                        .setRateOfFire(resultSet.getInt("rateoffire"))
                        .setMaxRange(resultSet.getInt("maxrange"))
                        .build();
                return Optional.of(weapon);
            }
        } catch (Exception e) {
            throw new WeaponDaoException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Weapon> findAll() throws WeaponDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL_WEAPONS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Weapon> weapons = new ArrayList<>();
            while (resultSet.next()) {
                Weapon weapon = new Weapon.Builder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("name"))
                        .setWeaponType(Weapon.WeaponType.valueOf(resultSet.getString("weaponType")))
                        .setWeight(resultSet.getInt("weight"))
                        .setDamage(resultSet.getInt("damage"))
                        .setAmmo(resultSet.getInt("ammo"))
                        .setRateOfFire(resultSet.getInt("rateoffire"))
                        .setMaxRange(resultSet.getInt("maxrange"))
                        .build();

                weapons.add(weapon);
            }
            return weapons;
        } catch (Exception e) {
            throw new WeaponDaoException(e.getMessage());
        }
    }

    @Override
    public Long save(Weapon weapon) throws WeaponDaoException {
        // set id in Weapon right here
        try {
            Long idResult = null;
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_WEAPONS, new String[]{"id"});
            //preparedStatement.setLong(1, weapon.getId());
            preparedStatement.setString(1, weapon.getName());
            preparedStatement.setString(2, weapon.getWeaponType().toString());
            preparedStatement.setInt(3, weapon.getWeight());
            preparedStatement.setInt(4, weapon.getDamage());
            preparedStatement.setInt(5, weapon.getAmmo());
            preparedStatement.setInt(6, weapon.getRateOfFire());
            preparedStatement.setInt(7, weapon.getMaxRange());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                idResult = rs.getLong("id");
            }
            rs.close();

            return idResult;
        } catch (Exception e) {
            throw new WeaponDaoException(e.getMessage());
        }
    }

    @Override
    public void update(Weapon weapon) throws WeaponDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_WEAPONS);
            preparedStatement.setString(1, weapon.getName());
            preparedStatement.setString(2, weapon.getWeaponType().toString());
            preparedStatement.setInt(3, weapon.getWeight());
            preparedStatement.setInt(4, weapon.getDamage());
            preparedStatement.setInt(5, weapon.getAmmo());
            preparedStatement.setInt(6, weapon.getRateOfFire());
            preparedStatement.setInt(7, weapon.getMaxRange());
            preparedStatement.setLong(8, weapon.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new WeaponDaoException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws WeaponDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_WEAPONS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new WeaponDaoException(e.getMessage());
        }
    }
}
