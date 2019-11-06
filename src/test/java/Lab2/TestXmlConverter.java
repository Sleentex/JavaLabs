package Lab2;

import Lab2.exception.ConvertException;
import Lab2.model.Weapon;
import Lab2.service.XmlConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestXmlConverter {
    private XmlConverter<Weapon> weaponXmlConverter;
    private Weapon weapon;

    @BeforeTest
    public void BeforeTest() {
        weaponXmlConverter = new XmlConverter<>(Weapon.class);
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
        String xmlString = "<NewWeapon><name>M4A1</name><weaponType>RIFLE</weaponType><weight>4</weight><damage>4</damage><ammo>90</ammo><rateOfFire>4</rateOfFire><maxRange>150</maxRange></NewWeapon>";
        String xmlExpected = weaponXmlConverter.serializeToString(weapon);
        Assert.assertEquals(xmlString, xmlExpected);
    }

    @Test
    public void deserializeString() throws ConvertException {
        String xmlString = "<NewWeapon><name>M4A1</name><weaponType>RIFLE</weaponType><weight>4</weight><damage>4</damage><ammo>90</ammo><rateOfFire>4</rateOfFire><maxRange>150</maxRange></NewWeapon>";
        Weapon weaponExpected = weaponXmlConverter.deserializeString(xmlString);
        Assert.assertEquals(weapon, weaponExpected);
    }

    @Test(expectedExceptions = ConvertException.class)
    public void negativeDeserializeString() throws ConvertException {
        String xmlString = "<NewWeapon><weaponType>RIFLE</weaponType><weight>4</weight><damage>4</damage><ammo>90</ammo><rateOfFire>4</rateOfFire><maxRange>150</maxRange></NewWeapon>";
        Weapon weaponExpected = weaponXmlConverter.deserializeString(xmlString);
    }


}
