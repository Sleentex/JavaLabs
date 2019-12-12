package Lab6;

import Lab5.connection.PostgresConnection;
import Lab5.dao.WeaponDao;
import Lab5.exceptions.PostgresConnectionException;
import Lab5.exceptions.WeaponDaoException;
import Lab5.model.Weapon;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.Optional;

public class TestWeaponDao {
    private Connection connection;
    private WeaponDao weaponDao;
    private Weapon weapon;
    private Long id;

    @BeforeClass
    public void init() throws PostgresConnectionException {
        connection = PostgresConnection.getConnection();
        weaponDao = new WeaponDao();
    }

    @Test
    public void testInsert() throws WeaponDaoException {
        weapon = new Weapon.Builder()
                .setName("M4A1")
                .setWeaponType(Weapon.WeaponType.RIFLE)
                .setDamage(2)
                .setWeight(4)
                .setAmmo(90)
                .setMaxRange(150)
                .setRateOfFire(4)
                .build();

        id = weaponDao.save(weapon);
        weapon.setId(id);
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindById() throws WeaponDaoException {
        Optional<Weapon> weaponOptional = weaponDao.findById(weapon.getId());
        Assert.assertTrue(weaponOptional.isPresent());
        Assert.assertEquals(weaponOptional.get(), weapon);
    }

    @Test(dependsOnMethods = "testInsert")
    public void testFindAll() throws WeaponDaoException {
        Object[] actual = weaponDao.findAll().toArray();
        Object[] expected = { weapon };
        Assert.assertEquals(actual, expected);
    }

    @Test(dependsOnMethods = "testInsert")
    public void testUpdate() throws WeaponDaoException {
        Weapon expectedWeapon = new Weapon.Builder()
                .setId(id)
                .setName("AK-47")
                .setWeaponType(Weapon.WeaponType.RIFLE)
                .setDamage(3)
                .setWeight(4)
                .setAmmo(90)
                .setMaxRange(200)
                .setRateOfFire(3)
                .build();

        weaponDao.update(expectedWeapon);
        Optional<Weapon> actualWeapon = weaponDao.findById(weapon.getId());
        System.out.println(actualWeapon);
        Assert.assertTrue(actualWeapon.isPresent());
        Assert.assertEquals(actualWeapon.get(), expectedWeapon);
    }

    @Test(dependsOnMethods = {"testInsert", "testFindById"})
    public void testDelete() throws WeaponDaoException {
        weaponDao.delete(weapon.getId());
    }
}
