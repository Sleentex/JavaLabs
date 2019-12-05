package Lab5.dao;

import Lab5.connection.PostgresConnection;
import Lab5.exceptions.CharacterDaoException;
import Lab5.exceptions.WeaponDaoException;
import Lab5.model.Character;
import Lab5.model.CharacterWeapon;
import Lab5.model.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CharacterDao implements Dao<Character> {
    private static final String GET_BY_ID = "SELECT * FROM characters WHERE id=?";
    private static final String GET_ALL_CHARACTERS = "SELECT * FROM characters";
    private static final String INSERT_CHARACTERS = "INSERT INTO characters(id, name, health) VALUES(?,?,?)";
    private static final String UPDATE_CHARACTERS = "UPDATE characters SET name=?, health=? WHERE id=?";
    private static final String DELETE_CHARACTERS = "DELETE FROM characters WHERE id=?";
    private static final String INSERT_CHARACTER_WEAPONS = "INSERT INTO character_weapons VALUES(?,?,?)";

    private Connection getConnection() throws CharacterDaoException {
        try {
            return PostgresConnection.getConnection();
        } catch (Exception e) {
            throw new CharacterDaoException(e.getMessage());
        }
    }

    @Override
    public Optional<Character> findById(Long id) throws CharacterDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Character character = new Character();
                character.setId(resultSet.getLong("id"));
                character.setName(resultSet.getString("name"));
                character.setHealth(resultSet.getInt("health"));

                return Optional.of(character);
            }

        } catch (Exception e) {
            throw new CharacterDaoException(e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Character> findAll() throws CharacterDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL_CHARACTERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Character> characters = new ArrayList<>();

            while (resultSet.next()) {
                Character character = new Character();
                character.setId(resultSet.getLong("id"));
                character.setName(resultSet.getString("name"));
                character.setHealth(resultSet.getInt("health"));
                characters.add(character);
            }

            return characters;

        } catch (Exception e) {
            throw new CharacterDaoException(e.getMessage());
        }
    }

    @Override
    public Long save(Character character) throws CharacterDaoException {
        try {
            Long idResult = null;
            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_CHARACTERS, new String[]{"id"});
            preparedStatement.setLong(1, character.getId());
            preparedStatement.setString(2, character.getName());
            preparedStatement.setInt(3, character.getHealth());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                idResult = resultSet.getLong("id");
            }
            resultSet.close();

            return idResult;

        } catch (Exception e) {
            throw new CharacterDaoException(e.getMessage());
        }
    }

    @Override
    public void update(Character character) throws CharacterDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_CHARACTERS);
            preparedStatement.setString(1, character.getName());
            preparedStatement.setInt(2, character.getHealth());
            preparedStatement.setLong(3, character.getId());
        } catch (Exception e) {
            throw new CharacterDaoException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws CharacterDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_CHARACTERS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new CharacterDaoException(e.getMessage());
        }
    }

    public void addWeapon(Character character, Weapon weapon, Integer ammo) throws CharacterDaoException {
        try {
            if (!character.addWeapon(weapon, ammo) || ammo > weapon.getAmmo()) return;

            PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_CHARACTER_WEAPONS);
            preparedStatement.setLong(1, character.getId());
            preparedStatement.setLong(2, weapon.getId());
            preparedStatement.setInt(3, ammo);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new CharacterDaoException(e.getMessage());
        }
    }

    public Set<CharacterWeapon> getWeapons() {
        return new HashSet<CharacterWeapon>();
    }
}
