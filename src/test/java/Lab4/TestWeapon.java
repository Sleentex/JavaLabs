package Lab4;

import Lab4.model.Weapon;
import org.testng.annotations.Test;

public class TestWeapon {
    @Test
    public void builderTest() {
        Weapon weapon = new Weapon.Builder()
                .setId(1)
                .setName("M4A1")
                .setWeaponType(Weapon.WeaponType.RIFLE)
                .setDamage(2)
                .setWeight(4)
                .setAmmo(90)
                .setMaxRange(150)
                .setRateOfFire(4)
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void builderNegative1Test() {
        Weapon weapon = new Weapon.Builder()
                .setName("Desert Eagle")
                .setWeaponType(Weapon.WeaponType.PISTOL)
                .setDamage(-8)
                .setWeight(1)
                .setAmmo(30)
                .setMaxRange(50)
                .setRateOfFire(4)
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void builderNegative2Test() {
        Weapon weapon = new Weapon.Builder()
                .setName("AK-47")
                .build();
    }

}
