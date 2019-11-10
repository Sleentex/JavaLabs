package Lab2;

import Lab2.exception.ConvertException;
import Lab2.model.Weapon;
import Lab2.service.TextWeaponConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestTextWeaponConverter {
    private TextWeaponConverter textWeaponConverter;
    private Weapon weapon;

    @BeforeTest
    public void BeforeTest() {
        textWeaponConverter = new TextWeaponConverter();
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
        String txtExpected = "M4A1||RIFLE||4||4||90||4||150";
        String txtActual = textWeaponConverter.serializeToString(weapon);
        Assert.assertEquals(txtActual, txtExpected);
    }

    @Test
    public void deserializeString() throws ConvertException {
        String txtString = "M4A1||RIFLE||4||4||90||4||150";
        Weapon weaponActual = textWeaponConverter.deserializeString(txtString);
        Assert.assertEquals(weapon, weaponActual);
    }

    @DataProvider
    public Object[][] negativeDeserializeStringDataProvider() {
        return new Object[][] {
                {"M4A1|RIFLE||4||4||90||4||150"},
                {"RIFLE||4||4||90||4||150"},
                {"M4A1||RIFLE||hereCan`tbeText||4||90||4||150"},
                {"M4A1||RIFLE||4||4||90||4||150.1"}};
    }

    @Test(expectedExceptions = ConvertException.class, dataProvider = "negativeDeserializeStringDataProvider")
    public void negativeDeserializeString(String txtString) throws ConvertException {
        textWeaponConverter.deserializeString(txtString);
    }
}
