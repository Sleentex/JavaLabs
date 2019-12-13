package Lab6;

import Lab5.connection.PostgresConnection;
import Lab5.dao.CharacterDao;
import Lab5.exceptions.CharacterDaoException;
import Lab5.exceptions.PostgresConnectionException;
import Lab5.model.Character;
import Lab5.model.Weapon;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Optional;

public class TestCharacterDao {
    private Connection connection;
    private CharacterDao characterDao;
    private Character character;
    private Weapon weapon;
    private Long id_character;
    private Long id_weapon;

    @BeforeClass
    public void init() throws PostgresConnectionException {
        connection = PostgresConnection.getConnection();
        characterDao = new CharacterDao();
    }

    @Test
    public void testInsert() throws CharacterDaoException {
        character = new Character("Naruto", 100);
        id_character = characterDao.save(character);
        character.setId(id_character);

    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindById() throws CharacterDaoException {
        Optional<Character> characterOptional = characterDao.findById(character.getId());
        Assert.assertTrue(characterOptional.isPresent());
        Assert.assertEquals(characterOptional.get(), character);
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindAll() throws CharacterDaoException {
        Object[] actual = characterDao.findAll().toArray();
        Object[] expected = { character };
        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testInsert")
    public void testUpdate() throws CharacterDaoException {
        Character expectedCharacter = new Character(id_character, "Sasuke", 90);

        characterDao.update(expectedCharacter);
        Optional<Character> actualCharacter = characterDao.findById(character.getId());

        Assert.assertTrue(actualCharacter.isPresent());
        Assert.assertEquals(actualCharacter.get(), expectedCharacter);
    }

    @Test(dependsOnMethods = {"testInsert", "testFindById"})
    public void testDelete() throws CharacterDaoException {
        characterDao.delete(character.getId());
    }


}
