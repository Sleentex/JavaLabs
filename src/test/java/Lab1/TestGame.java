package Lab1;

import Lab1.model.Character;
import Lab1.model.Game;
import Lab1.model.Weapon;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGame {
    private Game game;
    private Character character1;
    private Character character2;
    private Weapon weaponEagle;
    private Weapon weaponM4A1;
    private Weapon weaponAWP;
    private Weapon weaponAK47;

    {
        weaponEagle = new Weapon.Builder()
                .setName("Desert Eagle")
                .setWeaponType(Weapon.WeaponType.PISTOL)
                .setDamage(8)
                .setWeight(3)
                .setAmmo(30)
                .setMaxRange(200)
                .setRateOfFire(2)
                .build();

        weaponAK47 = new Weapon.Builder()
                .setName("AK-47")
                .setWeaponType(Weapon.WeaponType.RIFLE)
                .setDamage(5)
                .setWeight(4)
                .setAmmo(90)
                .setMaxRange(400)
                .setRateOfFire(1)
                .build();

        weaponM4A1 = new Weapon.Builder()
                .setName("M4A1")
                .setWeaponType(Weapon.WeaponType.RIFLE)
                .setDamage(4)
                .setWeight(4)
                .setAmmo(90)
                .setMaxRange(400)
                .setRateOfFire(1)
                .build();

        weaponAWP = new Weapon.Builder()
                .setName("AWP")
                .setWeaponType(Weapon.WeaponType.SNIPER_RIFLE)
                .setDamage(12)
                .setWeight(5)
                .setAmmo(30)
                .setMaxRange(600)
                .setRateOfFire(3)
                .build();
    }

    @BeforeMethod
    public void createCharacter() {
        character1 = new Character("Naruto");
        character1.addWeapon(weaponAWP);
        character1.addWeapon(weaponM4A1);
        character2 = new Character("Sasuke");
        character2.addWeapon(weaponEagle);
        character2.addWeapon(weaponM4A1);
        game = new Game(character1, character2);
    }

    @Test
    public void fightTest() {
        Character expectedCharacter;
        expectedCharacter = game.fight(300);
        Assert.assertEquals(expectedCharacter, character1);
    }
}
