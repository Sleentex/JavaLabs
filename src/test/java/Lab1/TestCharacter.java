package Lab1;

import Lab1.model.Character;
import Lab1.model.Weapon;
import Lab1.service.CharacterService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCharacter {
    private Character character;
    private CharacterService characterService;
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
        character = new Character("Naruto");
    }
    @BeforeMethod
    public void createCharacterService() {
        characterService = new CharacterService(character);
    }

    @Test
    public void getBestWeaponTest() {
        character.addWeapon(weaponAWP);
        character.addWeapon(weaponM4A1);

        Weapon expectedWeapon = characterService.getEffectWeapon(450);
        Assert.assertEquals(expectedWeapon, weaponAWP);
    }

    @Test
    public void sortWeaponsByDamageTest() {
        character.addWeapon(weaponAWP);
        character.addWeapon(weaponEagle);

        Object[] sortedWeapons = characterService.sortWeaponsByDamage().toArray();
        Object[] expectedSortedWeapons = { weaponAWP, weaponEagle  };
        Assert.assertEquals(expectedSortedWeapons, sortedWeapons);
    }

    @Test
    public void countWeaponTypeTest() {
        character.addWeapon(weaponAK47);
        character.addWeapon(weaponM4A1);

        Integer expectedCountWeaponType = 2;
        Integer countWeaponType = characterService.countWeaponType(Weapon.WeaponType.RIFLE);
        Assert.assertEquals(expectedCountWeaponType, countWeaponType);
    }
}
