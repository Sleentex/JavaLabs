package Lab2;

import Lab2.exception.ConvertException;
import Lab2.model.Weapon;
import Lab2.service.JsonConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestJsonConverter {
    private JsonConverter<Weapon> weaponJsonConverter;
    private Weapon weapon;

    @BeforeTest
    public void BeforeTest() {
        weaponJsonConverter = new JsonConverter<>(Weapon.class);
    }

    @BeforeMethod
    public void BeforeMethod() {
        weapon = new Weapon.Builder()
                .setName("M4A1")
                .setWeaponType(Weapon.WeaponType.RIFLE)
                .setDamage(4)
                .setWeight(4)
                .setAmmo(90)
                .setMaxRange(150)
                .setRateOfFire(4)
                .build();
    }

    @Test
    public void serializeToString() throws ConvertException {
        String jsonString = "{\"name\":\"M4A1\",\"weaponType\":\"RIFLE\",\"weight\":4,\"damage\":4,\"ammo\":90,\"rateOfFire\":4,\"maxRange\":150}";
        String jsonExpected = weaponJsonConverter.serializeToString(weapon);
        Assert.assertEquals(jsonString, jsonExpected);
    }

    @Test
    public void deserializeString() throws ConvertException {
        String jsonString = "{\"name\":\"M4A1\",\"weaponType\":\"RIFLE\",\"weight\":4,\"damage\":4,\"ammo\":90,\"rateOfFire\":4,\"maxRange\":150}";
        Weapon weaponExpected = weaponJsonConverter.deserializeString(jsonString);
        Assert.assertEquals(weapon, weaponExpected);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeString() throws ConvertException {
        String jsonString = "{\"weaponType\":\"RIFLE\",\"weight\":4,\"damage\":4,\"ammo\":90,\"rateOfFire\":4,\"maxRange\":150}";
        Weapon weaponExpected = weaponJsonConverter.deserializeString(jsonString);
        Assert.assertEquals(weapon, weaponExpected);
    }
}
