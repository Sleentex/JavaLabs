package Lab5.dao;

import Lab5.connection.PostgresConnection;
import Lab5.exceptions.CharacterDaoException;
import Lab5.model.Character;
import Lab5.entity.WeaponEntity;
import Lab5.model.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CharacterDao implements Dao<Character> {
    private static final String GET_BY_ID = "SELECT * FROM characters WHERE id=?";
    private static final String GET_ALL_CHARACTERS = "SELECT * FROM characters";
    private static final String INSERT_CHARACTERS = "INSERT INTO characters(name, health) VALUES(?,?)";
    private static final String UPDATE_CHARACTERS = "UPDATE characters SET name=?, health=? WHERE id=?";
    private static final String DELETE_CHARACTERS = "DELETE FROM characters WHERE id=?";
    private static final String INSERT_CHARACTER_WEAPONS = "INSERT INTO character_weapons VALUES(?,?,?)";
    private static final String GET_ALL_WEAPONS = "SELECT * FROM character_weapons";
    private static final String GET_CHARACTER_WEAPONS = "SELECT * FROM character_weapons cw JOIN weapons w ON w.id = cw.id_weapon WHERE id_character = ?";
    private static final String DELETE_CHARACTER_WEAPONS = "DELETE FROM character_weapons WHERE id_character=? AND id_weapon=?";

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
            //preparedStatement.setLong(1, character.getId());
            preparedStatement.setString(1, character.getName());
            preparedStatement.setInt(2, character.getHealth());
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
            preparedStatement.executeUpdate();
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

    public void deleteWeapons(Long id_character, Long id_weapon) throws CharacterDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_CHARACTER_WEAPONS);
            preparedStatement.setLong(1, id_character);
            preparedStatement.setLong(2, id_weapon);
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
            System.out.println("AddWeapon " + weapon.getId());
            preparedStatement.setInt(3, ammo);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new CharacterDaoException(e.getMessage());
        }
    }

    public List<WeaponEntity> getWeapons(Character character) throws CharacterDaoException {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(GET_CHARACTER_WEAPONS);
            preparedStatement.setLong(1, character.getId());
            ResultSet rs = preparedStatement.executeQuery();
            List<WeaponEntity> weaponEntities = new ArrayList<>();

            while (rs.next()) {
                WeaponEntity weaponEntity = new WeaponEntity();
                weaponEntity.setAmmo(rs.getInt("ammo"));

                Weapon weapon = new Weapon.Builder()
                        .setId(rs.getLong("id"))
                        .setName(rs.getString("name"))
                        .setWeaponType(Weapon.WeaponType.valueOf(rs.getString("weaponType")))
                        .setWeight(rs.getInt("weight"))
                        .setDamage(rs.getInt("damage"))
                        .setAmmo(rs.getInt("ammo"))
                        .setRateOfFire(rs.getInt("rateoffire"))
                        .setMaxRange(rs.getInt("maxrange"))
                        .build();

                weaponEntity.setWeapon(weapon);
                weaponEntities.add(weaponEntity);
            }

            return weaponEntities;
        } catch (Exception e) {
            throw new CharacterDaoException(e.getMessage());
        }

    }
}
